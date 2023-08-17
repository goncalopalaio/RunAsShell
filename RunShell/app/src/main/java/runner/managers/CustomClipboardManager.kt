package runner.managers

import android.content.ClipData
import android.os.IInterface
import runner.data.PACKAGE_NAME
import runner.data.ROOT_UID
import java.lang.Exception
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class CustomClipboardManager(private val manager: IInterface) {
    private val methodSetPrimaryClip: Method by lazy {
        manager.javaClass.getMethod(
            "setPrimaryClip",
            ClipData::class.java,
            String::class.java,
            Int::class.javaPrimitiveType
        )
    }

    fun setText(text: CharSequence) = try {
        val clipData = ClipData.newPlainText(null, text)
        setPrimaryClip(clipData)
        true
    } catch (e: Exception) {
        println("Could not invoke method: $e")
        false
    }

    @Throws(InvocationTargetException::class, IllegalAccessException::class)
    private fun setPrimaryClip(clipData: ClipData) =
        methodSetPrimaryClip.invoke(manager, clipData, PACKAGE_NAME, ROOT_UID)
}