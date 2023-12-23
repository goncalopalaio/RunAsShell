package runner.managers.implementation

import android.os.IInterface
import java.lang.reflect.Method


/**
 * Exposes methods from PowerManager
 *
 * [https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/os/IPowerManager.aidl]
 */
class CustomPowerManager(private val manager: IInterface) {
    private val methodIsInteractive: Method by lazy {
        manager.javaClass.getMethod("isInteractive")
    }

    /**
     * [https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/os/IPowerManager.aidl;l=1?q=IPowerManager&sq=&ss=android%2Fplatform%2Fsuperproject%2Fmain:frameworks%2Fbase%2Fcore%2Fjava%2Fandroid%2F]
     */
    private val methodReboot: Method by lazy {
        manager.javaClass.getMethod(
            "reboot",
            Boolean::class.javaPrimitiveType,
            String::class.java,
            Boolean::class.javaPrimitiveType,
        )
    }

    fun isInteractive(): Boolean? = try {
        methodIsInteractive.invoke(manager) as? Boolean
    } catch (e: Exception) {
        println("isInteractive | Could not invoke method: $e")
        null
    }

    fun reboot(reason: String?) = try {
        val confirm = false
        val wait = false
        println("reboot | confirm=$confirm, reason=$reason, wait=$wait")
        methodReboot.invoke(manager, confirm, reason, wait)
    } catch (e: Exception) {
        println("reboot | Could not invoke method: $e")
        null
    }
}