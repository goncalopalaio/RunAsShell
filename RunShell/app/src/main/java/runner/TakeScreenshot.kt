package runner

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.os.Environment
import android.view.PixelCopy
import java.io.File
import kotlin.system.measureTimeMillis


object TakeScreenshot {

    @Suppress("UNUSED_PARAMETER")
    fun run(args: List<String>): Int {
        val startScreenshotTimeMs = System.currentTimeMillis()
        val bitmap = screenshot()
        val screenshotTimeMs = System.currentTimeMillis() - startScreenshotTimeMs
        println("bitmap=$bitmap, width=${bitmap?.width}, height=${bitmap?.height}, screenshotTimeMs=$screenshotTimeMs")
        if (bitmap == null) return 1

        val file =
            File(Environment.getExternalStorageDirectory(), "${System.currentTimeMillis()}.jpg")

        val writeTimeMs = measureTimeMillis {
            writeBitmap(bitmap, Bitmap.CompressFormat.JPEG, 50, file)
        }

        val recycleTimeMs = measureTimeMillis {
            bitmap.recycle()
        }

        println("file=$file, screenshotTimeMs=$screenshotTimeMs, writeTimeMs=$writeTimeMs, recycleTimeMs=$recycleTimeMs")
        return 0
    }

    fun createHelp(): String = """""".trimIndent()

    @Throws(Exception::class)
    fun screenshot(): Bitmap? {
        val size: Point = getDisplaySize() // TODO This is returning zeros.
        println("size=$size")

        // https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/view/SurfaceControl.java;l=859;drc=ebd11defff68784e4c049e8ef7668f4dc007e2ce;bpv=1;bpt=1?q=SurfaceControl

        /**
         * From: https://android.googlesource.com/platform/frameworks/base/+/bcf48ed/core/java/android/view/SurfaceControl.java
         * CAVEAT: Versions of screenshot that return a {@link Bitmap} can
         * be extremely slow; avoid use unless absolutely necessary; prefer
         * the versions that use a {@link Surface} instead, such as
         * {@link SurfaceControl#screenshot(IBinder, Surface)}.
         */
        // TODO replace screenshot call?
        // TODO try PixelCopy.request(surfaceView, bitmap, listener, Handler())

        val clazz = Class.forName("android.view.SurfaceControl")
        println("class=$clazz")


        /*
        val methods = clazz.methods
        for (m in methods) {
            println("m=$m")
        }
        */

        val method = clazz.getMethod(
            "screenshot",
            Rect::class.java,
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
        )
        println("method=$method")

        // VERSION.SDK_INT <= 17 android.view.Surface.screenshot(size.x, size.y)
        // VERSION.SDK_INT < 28  SurfaceControl.screenshot(size.x, size.y)

        val rotation = 0
        return method.invoke(null, *arrayOf(Rect(), size.x, size.y, rotation)) as? Bitmap
    }

    private fun getDisplaySize(): Point {
        val displayMetrics = Resources.getSystem().displayMetrics
        return Point(displayMetrics.widthPixels, displayMetrics.heightPixels)
    }

    private fun writeBitmap(
        bitmap: Bitmap,
        format: Bitmap.CompressFormat,
        quality: Int,
        file: File
    ) {
        file.outputStream().use { out ->
            bitmap.compress(format, quality, out)
            out.flush()
        }
    }
}