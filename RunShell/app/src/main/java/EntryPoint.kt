import runner.InjectKeyEvents
import runner.SetClipboard
import runner.TakeScreenshot

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

    return when (program) {
        "clipboard" -> SetClipboard.run(arguments)
        "input" -> InjectKeyEvents.run(arguments)
        "screenshot" -> TakeScreenshot.run(arguments)
        "help" -> runHelp()
        else -> {
            println("Unknown program -> $program")
            runHelp()
            ERROR_UNKNOWN_PROGRAM
        }
    }
}

private fun runHelp(): Int {
    println("Runner v0.2")
    println()
    println(SetClipboard.createHelp())
    println()
    println(InjectKeyEvents.createHelp())
    println()
    return 0
}
