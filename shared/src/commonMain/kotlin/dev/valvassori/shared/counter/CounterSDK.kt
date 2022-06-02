package dev.valvassori.shared.counter

import dev.valvassori.shared.counter.vm.CounterViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object CounterSDK {
    fun initialize() {
        startKoin {
            modules(
                module {
                    factory { CounterViewModel() }
                }
            )
        }
    }
}
