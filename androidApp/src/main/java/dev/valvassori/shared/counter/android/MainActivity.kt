package dev.valvassori.shared.counter.android

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dev.valvassori.shared.counter.vm.CounterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val DELAY: Long = 1500
class MainActivity : AppCompatActivity() {
    private val counterViewModel: CounterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupCounterDisplay()
        setupButtons()
    }

    private fun setupCounterDisplay() {
        val counter = findViewById<TextView>(R.id.counter)
        lifecycleScope.launchWhenCreated {
            counterViewModel.state.collect { counter.text = it.toString() }
        }
    }

    private fun setupButtons() {
        findViewById<Button>(R.id.inc).setOnClickListener {
            counterViewModel.inc()
        }
        findViewById<Button>(R.id.inc_delay).setOnClickListener {
            counterViewModel.inc(DELAY)
        }
        findViewById<Button>(R.id.dec).setOnClickListener {
            counterViewModel.dec()
        }
        findViewById<Button>(R.id.dec_delay).setOnClickListener {
            counterViewModel.dec(DELAY)
        }
    }
}
