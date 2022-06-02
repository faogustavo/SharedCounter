package dev.valvassori.shared.counter.android

import android.app.Application
import dev.valvassori.shared.counter.CounterSDK

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        CounterSDK.initialize()
    }
}
