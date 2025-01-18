package runner.context

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.AttributionSource
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Looper
import android.os.Process.SHELL_UID
import runner.data.PACKAGE_NAME
import java.lang.reflect.Field


class FakeContext : ContextWrapper(getSystemContext()) {

    override fun getPackageName() = PACKAGE_NAME
    override fun getOpPackageName() = PACKAGE_NAME

    @TargetApi(Build.VERSION_CODES.S)
    override fun getAttributionSource(): AttributionSource {
        val builder: AttributionSource.Builder = AttributionSource.Builder(SHELL_UID)
        builder.setPackageName(PACKAGE_NAME)
        return builder.build()
    }

    override fun getApplicationContext(): Context {
        return this
    }

    @SuppressLint("DiscouragedPrivateApi")
    companion object {
        val fakeContext by lazy { FakeContext() }

        private val ACTIVITY_THREAD_CLASS: Class<*> = Class.forName("android.app.ActivityThread")
        private val ACTIVITY_THREAD: Any by lazy {
            @Suppress("DEPRECATION")
            Looper.prepareMainLooper() // Note: activityThreadConstructor.newInstance fails without this.

            val activityThreadConstructor = ACTIVITY_THREAD_CLASS.getDeclaredConstructor()
            activityThreadConstructor.isAccessible = true
            val activityThread = activityThreadConstructor.newInstance()

            val sCurrentActivityThreadField: Field =
                ACTIVITY_THREAD_CLASS.getDeclaredField("sCurrentActivityThread")
            sCurrentActivityThreadField.isAccessible = true
            sCurrentActivityThreadField.set(null, activityThread)

            activityThread
        }

        private fun getSystemContext(): Context? {
            return try {
                val getSystemContextMethod =
                    ACTIVITY_THREAD_CLASS.getDeclaredMethod("getSystemContext")
                getSystemContextMethod.invoke(ACTIVITY_THREAD) as Context
            } catch (exception: Exception) {
                println("getSystemContext failed | exception=$exception")
                null
            }
        }
    }
}