package runner

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.widget.Toast
import runner.context.FakeContext

object Broadcast {

    /**
     * Experimental - Not working.
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun run(args: List<String>): Int {
        val actionToUse = args.getOrElse(0) { null }

        if (actionToUse == null) {
            println("No action for broadcast | args=$args")
            return 1
        }
        val intent = Intent().apply {
            action = actionToUse
        }

        FakeContext.fakeContext.sendBroadcast(intent)
        println("broadcast requested | intent=$intent")

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.action
                println("action=$action")
            }
        }
        val filter = IntentFilter().apply {
            addAction(actionToUse)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            FakeContext.fakeContext.registerReceiver(receiver, filter, RECEIVER_EXPORTED)
        } else {
            FakeContext.fakeContext.registerReceiver(receiver, filter, null, null)
        }
        println("Sleeping")
        Thread.sleep(10_000)
        return 0
    }

    fun createHelp(): String = """
        broadcast
    """.trimIndent()
}