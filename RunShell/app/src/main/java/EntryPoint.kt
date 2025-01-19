import runner.Broadcast
import runner.InjectKeyEvents
import runner.IsInteractive
import runner.Reboot
import runner.SetClipboard
import runner.TakeScreenshot
import runner.Toast

private const val ERROR_NO_PROGRAM = 1000
private const val ERROR_UNKNOWN_PROGRAM = 1001

fun start(args: Array<String>): Int {
    val program = args.firstOrNull()

    if (program == null) {
        println("No provided program")
        runHelp()
        return ERROR_NO_PROGRAM
    }

    val arguments = args.drop(1)
    println("program=$program, arguments=$arguments")

    // TODO - Implement interface for each command
    // TODO - Review logging on each command, add tag with the name of the command to each log
    // TODO - Add command class for help
    return when (program) {
        SetClipboard.NAME -> SetClipboard.run(arguments)
        InjectKeyEvents.NAME -> InjectKeyEvents.run(arguments)
        TakeScreenshot.NAME -> TakeScreenshot.run(arguments)
        IsInteractive.NAME -> IsInteractive.run()
        Reboot.NAME -> Reboot.run(arguments)
        Toast.NAME -> Toast.run(arguments)
        Broadcast.NAME -> Broadcast.run(arguments)
        "help" -> runHelp()
        else -> {
            println("Unknown program -> $program")
            runHelp()
            ERROR_UNKNOWN_PROGRAM
        }
    }
}

private fun runHelp(): Int {
    println("Runner 0.3")
    val helpInfo = listOf(
        SetClipboard.createHelp(),
        InjectKeyEvents.createHelp(),
        TakeScreenshot.createHelp(),
        IsInteractive.createHelp(),
        Reboot.createHelp(),
        Toast.createHelp(),
        Broadcast.createHelp(),
    )

    println(helpInfo.joinToString("\n\n--------\n", prefix = "\n", postfix = "\n"))
    return 0
}
