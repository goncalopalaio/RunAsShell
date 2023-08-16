package cola

sealed class InjectedKeyEvent {
    data class KeyCode(val keyCode: Int) : InjectedKeyEvent()
    data class Timeout(val timeoutMs: Long) : InjectedKeyEvent()
}
