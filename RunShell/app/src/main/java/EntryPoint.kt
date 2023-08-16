import cola.commands.Cola
import cola.commands.Escreve

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
        "cola" -> Cola.run(arguments)
        "escreve" -> Escreve.run(arguments)
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
    println(Cola.createHelp())
    println()
    println(Escreve.createHelp())
    println()
    return 0
}
