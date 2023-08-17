package cola.commands

import cola.CustomServiceManager

object SetClipboard {
    fun run(args: List<String>): Int {
        val text = args.getOrElse(0) { "" }
        println("Cola -> $text")
        CustomServiceManager.clipboardManager.setText(text)

        return 0
    }

    fun createHelp(): String = """
        # Setting text to the clipboard
        cola "https://google.com/"
    """.trimIndent()
}