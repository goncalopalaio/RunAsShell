package runner

import runner.managers.ServiceManager

object SetClipboard {
    fun run(args: List<String>): Int {
        val text = args.getOrElse(0) { "" }
        println("Cola -> $text")
        ServiceManager.clipboardManager.setText(text)

        return 0
    }

    fun createHelp(): String = """
        # Setting text to the clipboard
        cola "https://google.com/"
    """.trimIndent()
}