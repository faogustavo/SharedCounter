package dev.valvassori.shared.counter.vm

import org.koin.core.component.inject

class CounterCallbackViewModel : CallbackViewModel() {
    override val viewModel: CounterViewModel by inject()

    val state by lazy { viewModel.state.asCallbacks() }

    fun inc(delayMillis: Long = 0) { viewModel.inc(delayMillis) }
    fun dec(delayMillis: Long = 0) { viewModel.dec(delayMillis) }
}
