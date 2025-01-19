package runner

import runner.managers.ServiceManager

object IsInteractive {
    const val NAME = "interactive"

    fun run(): Int {
        val isInteractive = ServiceManager.powerManager.isInteractive()
        println("$NAME | isInteractive=$isInteractive")
        return if (isInteractive == true) 1 else 0
    }

    fun createHelp(): String = """
        $NAME
    """.trimIndent()
}