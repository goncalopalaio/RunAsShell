package runner

import runner.managers.ServiceManager

object SetClipboard {
    const val NAME = "clipboard"

    fun run(args: List<String>): Int {
        val text = args.getOrElse(0) { "" }
        println("$NAME -> $text")
        ServiceManager.clipboardManager.setText(text)

        return 0
    }

    fun createHelp(): String = """
        # Setting text to the clipboard
        $NAME "https://google.com/"
    """.trimIndent()
}