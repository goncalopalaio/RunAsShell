package runner

import runner.managers.ServiceManager

object IsInteractive {

    fun run(): Int {
        val isInteractive = ServiceManager.powerManager.isInteractive()
        println("isInteractive=$isInteractive")
        return if (isInteractive == true) 1 else 0
    }

    fun createHelp(): String = """
        interactive
    """.trimIndent()
}