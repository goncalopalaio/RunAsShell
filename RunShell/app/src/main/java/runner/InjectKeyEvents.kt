package runner

import android.os.SystemClock
import android.view.InputDevice
import android.view.KeyCharacterMap
import android.view.KeyEvent
import runner.data.InjectedKeyEvent
import runner.managers.ServiceManager

private const val DEFAULT_DISPLAY_ID = 0
private const val DEFAULT_INJECT_MODE = 0 // android.os.InputEventInjectionSync.NONE

object InjectKeyEvents {
    const val NAME = "input"

    fun run(args: List<String>): Int {
        val input = args.getOrElse(0) { "" }

        val items = input.split("-").mapNotNull {
            if (it.startsWith("SLEEP")) {
                val time = it.replace("SLEEP", "").toLongOrNull() ?: return@mapNotNull null
                InjectedKeyEvent.Timeout(time)
            } else {
                val keyCode = it.toIntOrNull() ?: return@mapNotNull null
                InjectedKeyEvent.KeyCode(keyCode)
            }
        }
        println("$NAME -> $items")

        for (item in items) {
            println("$item")
            when (item) {
                is InjectedKeyEvent.KeyCode -> {
                    val success = ServiceManager.inputManager.inject(
                        createDefaultKeyEvent(item.keyCode, KeyEvent.ACTION_DOWN),
                        DEFAULT_DISPLAY_ID,
                        DEFAULT_INJECT_MODE
                    )
                    if (success) {
                        ServiceManager.inputManager.inject(
                            createDefaultKeyEvent(item.keyCode, KeyEvent.ACTION_UP),
                            DEFAULT_DISPLAY_ID,
                            DEFAULT_INJECT_MODE
                        )
                    }
                }

                is InjectedKeyEvent.Timeout -> Thread.sleep(item.timeoutMs)
            }
        }

        return 0
    }

    fun createHelp(): String = """
        # Writing KEYCODE_1, KEYCODE_2, KEYCODE_3
        $NAME "8-9-10"
        # Writing KEYCODE_1, Sleep for 100ms, KEYCODE_2, KEYCODE_3
        $NAME "8-SLEEP111-9-10"
    """.trimIndent()

    private fun createDefaultKeyEvent(keyCode: Int, action: Int): KeyEvent {
        val now = SystemClock.uptimeMillis()
        return KeyEvent(
            now,
            now,
            action,
            keyCode,
            0,
            0,
            KeyCharacterMap.VIRTUAL_KEYBOARD,
            0,
            0,
            InputDevice.SOURCE_KEYBOARD
        )
    }
}