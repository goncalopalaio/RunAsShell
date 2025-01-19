package runner

import android.widget.Toast
import runner.context.FakeContext

object Toast {
    const val NAME = "toast"

    fun run(args: List<String>): Int {
        val text = args.joinToString(" ")

        println("$NAME | text=$text")
        Toast.makeText(FakeContext.fakeContext, text, Toast.LENGTH_SHORT).show()

        return 0
    }

    fun createHelp(): String = """
        # Displays a Toast on the screen
        $NAME "This is a short toast"
    """.trimIndent()
}