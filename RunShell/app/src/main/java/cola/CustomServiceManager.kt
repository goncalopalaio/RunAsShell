package cola

import android.annotation.SuppressLint
import android.os.IBinder
import android.os.IInterface
import java.lang.reflect.Method

@SuppressLint("DiscouragedPrivateApi", "PrivateApi")
object CustomServiceManager {
    private val methodGetService: Method by lazy {
        Class.forName("android.os.ServiceManager")
            .getDeclaredMethod("getService", String::class.java)
    }

    val clipboardManager: CustomClipboardManager by lazy {
        val clipboard = getService("clipboard", "android.content.IClipboard")
        CustomClipboardManager(clipboard)
    }

    private fun getService(service: String, type: String): IInterface {
        val binder = methodGetService.invoke(null, service) as IBinder
        val methodAsInterface =
            Class.forName("$type\$Stub").getMethod("asInterface", IBinder::class.java)
        return methodAsInterface.invoke(null, binder) as IInterface
    }
}