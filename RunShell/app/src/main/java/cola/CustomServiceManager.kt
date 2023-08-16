package cola

import android.annotation.SuppressLint
import android.hardware.input.InputManager
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

    val inputManager: CustomInputManager by lazy {
        val clazz = try {
            Class.forName("android.hardware.input.InputManagerGlobal")
        } catch (e: ClassNotFoundException) {
            InputManager::class.java
        }

        val getInstanceMethod = clazz.getDeclaredMethod("getInstance")
        val instance = getInstanceMethod.invoke(null) as InputManager
        CustomInputManager(instance)
    }

    private fun getService(service: String, type: String): IInterface {
        val binder = methodGetService.invoke(null, service) as IBinder
        val methodAsInterface =
            Class.forName("$type\$Stub").getMethod("asInterface", IBinder::class.java)
        return methodAsInterface.invoke(null, binder) as IInterface
    }
}