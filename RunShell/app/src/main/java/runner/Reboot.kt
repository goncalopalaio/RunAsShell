package runner

import runner.managers.ServiceManager

object Reboot {
    const val NAME = "reboot"

    fun run(args: List<String>): Int {
        val reason = args.getOrElse(0) { null }
        ServiceManager.powerManager.reboot(reason)
        println("$NAME | reason=$reason")
        return 0
    }

    fun createHelp(): String = """
        $NAME [reason]
    """.trimIndent()
}