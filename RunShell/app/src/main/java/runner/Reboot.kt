package runner

import runner.managers.ServiceManager

object Reboot {

    fun run(args: List<String>): Int {
        val reason = args.getOrElse(0) { null }
        ServiceManager.powerManager.reboot(reason)
        println("Requested reboot")
        return 0
    }

    fun createHelp(): String = """
        reboot [reason]
    """.trimIndent()
}