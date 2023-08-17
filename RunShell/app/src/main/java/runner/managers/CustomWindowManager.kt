package runner.managers

import android.annotation.SuppressLint
import android.os.IInterface
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowMetrics
import java.lang.reflect.Method

/**
 * Exposes some of the methods from [cs.android.com/android/platform/superproject/main/+/main:frameworks/layoutlib/bridge/src/android/view/WindowManagerImpl.java]
 */
class CustomWindowManager(private val manager: IInterface) {
    private val methodGetMetrics: Method by lazy {
        manager.javaClass.getMethod("getMetrics")
    }

    fun getMetrics(): DisplayMetrics? = try {
        methodGetMetrics.invoke(manager) as? DisplayMetrics
    } catch (e: Exception) {
        println("getMetrics | Could not invoke method: $e")
        null
    }
}