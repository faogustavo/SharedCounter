package dev.valvassori.shared.counter.vm

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CounterViewModel : ViewModel() {
    private val _state = MutableStateFlow(0)
    private val mutex = Mutex()
    val state = _state.asStateFlow()

    fun inc(delayMillis: Long = 0) {
        viewModelScope.launch {
            if (delayMillis > 0) delay(delayMillis)
            mutex.withLock { _state.value = _state.value.inc() }
        }
    }

    fun dec(delayMillis: Long = 0) {
        viewModelScope.launch {
            if (delayMillis > 0) delay(delayMillis)
            mutex.withLock { _state.value = _state.value.dec() }
        }
    }
}
