package runner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import runner.context.FakeContext

object Broadcast {

    fun run(args: List<String>): Int {
        val actionToUse = args.getOrElse(0) { null }
        val packageName = args.getOrElse(1) { null }

        if (actionToUse == null) {
            println("No action for broadcast | args=$args")
            return 1
        }
        val intent = Intent().apply {
            action = actionToUse
            packageName?.let { setPackage(it) }
        }
        FakeContext.fakeContext.sendBroadcast(intent)
        println("broadcast requested | intent=$intent")

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.action
            }
        }
        val filter = IntentFilter().apply {
            addAction("action-back")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            FakeContext.fakeContext.registerReceiver(receiver, filter, RECEIVER_EXPORTED)
        } else {
            FakeContext.fakeContext.registerReceiver(receiver, filter)
        }
        println("Sleeping")
        Thread.sleep(10_000)
        return 0
    }

    fun createHelp(): String = """
        broadcast
    """.trimIndent()
}