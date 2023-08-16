package cola

import android.view.InputEvent
import android.view.KeyEvent
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Exposes some of the methods from [https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/hardware/input/InputManager.java]
 */
class CustomInputManager(private val manager: Any) {

    /**
     * [https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/hardware/input/InputManager.java;bpv=1;bpt=1;l=1152]
     */
    private val methodInjectInputEvent: Method by lazy {
        manager.javaClass.getMethod(
            "injectInputEvent",
            InputEvent::class.java,
            Int::class.javaPrimitiveType
        )
    }

    fun inject(keyEvent: KeyEvent, displayId: Int, injectMode: Int) = try {
        injectEvent(keyEvent, injectMode)
    } catch (e: Exception) {
        println("Could not invoke method | e=$e, keyEvent=$keyEvent, displayId=$displayId, injectMode=$injectMode")
        false
    }

    @Throws(InvocationTargetException::class, IllegalAccessException::class)
    private fun injectEvent(inputEvent: InputEvent, mode: Int): Boolean =
        methodInjectInputEvent.invoke(manager, inputEvent, mode) as Boolean
}