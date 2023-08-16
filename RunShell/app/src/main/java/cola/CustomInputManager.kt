package cola

import android.os.IInterface
import android.view.InputEvent
import android.view.KeyEvent
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class CustomInputManager(private val manager: Any) {
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