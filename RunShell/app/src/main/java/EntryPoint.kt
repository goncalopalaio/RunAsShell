import android.os.Build
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

    return when (program) {
        "clipboard" -> SetClipboard.run(arguments)
        "input" -> InjectKeyEvents.run(arguments)
        "screenshot" -> TakeScreenshot.run(arguments)
        "interactive" -> IsInteractive.run()
        "reboot" -> Reboot.run(arguments)
        "toast" -> Toast.run(arguments)
        "broadcast" -> Broadcast.run(arguments)
        "help" -> runHelp()
        else -> {
            println("Unknown program -> $program")
            runHelp()
            ERROR_UNKNOWN_PROGRAM
        }
    }
}

private fun runHelp(): Int {
    println("Runner 2.0")
    val helpInfo = listOf(
        SetClipboard.createHelp(),
        InjectKeyEvents.createHelp(),
        TakeScreenshot.createHelp(),
        IsInteractive.createHelp(),
        Reboot.createHelp(),
        Toast.createHelp(),
        Broadcast.createHelp(),
    )

    println(helpInfo.joinToString("\n", postfix = "\n"))
    return 0
}
