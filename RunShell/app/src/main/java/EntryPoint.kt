import cola.CustomServiceManager.clipboardManager

private const val ERROR_NO_PROGRAM = 1000
private const val ERROR_UNKNOWN_PROGRAM = 1001

fun start(args: Array<String>): Int {
    val program = args.firstOrNull()

    if (program == null) {
        println("No provided program")
        return ERROR_NO_PROGRAM
    }

    val arguments = args.drop(1)

    return when (program) {
        "cola" -> runCola(arguments)
        "help" -> runHelp()
        else -> {
            println("Unknown program -> $program")
            ERROR_UNKNOWN_PROGRAM
        }
    }
}

private fun runHelp(): Int {
    println("Runner v0.1")

    return 0
}

private fun runCola(args: List<String>): Int {
    val text = args.getOrElse(0) { "" }
    println("Clipboard -> $text")
    clipboardManager.setText(text)

    return 0
}
