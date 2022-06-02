package dev.valvassori.shared.counter.vm

import dev.valvassori.shared.counter.flow.FlowAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

actual abstract class ViewModel {
    actual val viewModelScope = MainScope()

    protected actual open fun onCleared() {}

    internal fun clear() {
        onCleared()
        viewModelScope.cancel()
    }
}

abstract class CallbackViewModel : KoinComponent {
    protected abstract val viewModel: ViewModel

    fun <T : Any> Flow<T>.asCallbacks() =
        FlowAdapter(viewModel.viewModelScope, this)

    @Suppress("Unused") // Called from Swift
    fun clear() = viewModel.clear()
}
