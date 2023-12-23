package runner.managers

import android.annotation.SuppressLint
import android.hardware.input.InputManager
import android.os.IBinder
import android.os.IInterface
import runner.managers.implementation.CustomClipboardManager
import runner.managers.implementation.CustomInputManager
import runner.managers.implementation.CustomPowerManager
import runner.managers.implementation.CustomWindowManager
import java.lang.reflect.Method

@SuppressLint("DiscouragedPrivateApi", "PrivateApi")
object ServiceManager {
    private val methodGetService: Method by lazy {
        Class.forName("android.os.ServiceManager")
            .getDeclaredMethod("getService", String::class.java)
    }

    val clipboardManager: CustomClipboardManager by lazy {
        val manager = getService("clipboard", "android.content.IClipboard")
        CustomClipboardManager(manager)
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

    val windowManager: CustomWindowManager by lazy {
        val manager = getService("window", "android.view.IWindowManager")
        CustomWindowManager(manager)
    }

    val powerManager: CustomPowerManager by lazy {
        val manager = getService("power", "android.os.IPowerManager")
        CustomPowerManager(manager)
    }

    private fun getService(service: String, type: String): IInterface {
        val binder = methodGetService.invoke(null, service) as IBinder
        val methodAsInterface =
            Class.forName("$type\$Stub").getMethod("asInterface", IBinder::class.java)
        return methodAsInterface.invoke(null, binder) as IInterface
    }
}