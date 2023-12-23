package runner

import runner.managers.ServiceManager

private const val DEFAULT_DISPLAY_ID = 0
private const val DEFAULT_INJECT_MODE = 0 // android.os.InputEventInjectionSync.NONE

object IsInteractive {

    fun run(args: List<String>): Int {
        val isInteractive = ServiceManager.powerManager.isInteractive()
        println("isInteractive=$isInteractive")
        return if (isInteractive == true) 1 else 0
    }

    fun createHelp(): String = """
        interactive
    """.trimIndent()
}