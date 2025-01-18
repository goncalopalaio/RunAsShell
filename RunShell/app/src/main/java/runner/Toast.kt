package runner

import android.widget.Toast
import runner.context.FakeContext

object Toast {

    fun run(args: List<String>): Int {
        val text = args.joinToString(" ")

        println("Displaying Toast | text=$text")
        Toast.makeText(FakeContext.fakeContext, text, Toast.LENGTH_SHORT).show()

        return 0
    }

    fun createHelp(): String = """
        Displays a Toast on the screen
        toast "This is a long toast" 1
        toast "This is a short toast"
    """.trimIndent()
}