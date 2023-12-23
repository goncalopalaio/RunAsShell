package runner.managers.implementation

import android.os.IInterface
import java.lang.reflect.Method


/**
 * Exposes methods from PowerManager
 */
class CustomPowerManager(private val manager: IInterface) {
    private val methodIsInteractive: Method by lazy {
        manager.javaClass.getMethod("isInteractive");
    }

    fun isInteractive(): Boolean? = try {
        methodIsInteractive.invoke(manager) as? Boolean
    } catch (e: Exception) {
        println("getMetrics | Could not invoke method: $e")
        null
    }
}