package com.pil.tp_04.activity // ktlint-disable package-name

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pil.tp_04.R
import com.pil.tp_04.databinding.ActivityMainBinding
import com.pil.tp_04.mvvm.model.CounterModel
import com.pil.tp_04.mvvm.viewmodel.CounterViewModel
import com.pil.tp_04.mvvm.viewmodel.CounterViewModel.MainData
import com.pil.tp_04.mvvm.viewmodel.CounterViewModel.MainState

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val counterViewModel: CounterViewModel = CounterViewModel(CounterModel())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonListener()
        counterViewModel.initUI()
        counterViewModel.data.observe({ lifecycle }, ::updateUI)
    }

    private fun updateUI(it: MainData) {
        when (it.state) {
            MainState.INITIAL -> {
                binding.counter.text = it.value.toString()
                clearEditText()
                showToast(getString(R.string.reset_button_text))
            }
            MainState.INC -> {
                binding.counter.text = it.value.toString()
            }
            MainState.DEC -> {
                binding.counter.text = it.value.toString()
            }
        }
    }

    private fun buttonListener() {
        binding.incrementBtn.setOnClickListener { counterViewModel.incrementValue(getInput()) }
        binding.decrementBtn.setOnClickListener { counterViewModel.decrementValue(getInput()) }
        binding.resetBtn.setOnClickListener { counterViewModel.resetValue() }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun getInput(): Int {
        val input = ZERO_INT
        if (binding.inputCount.text.toString().isEmpty()) {
            showToast(getString(R.string.empty_input))
        } else {
            return Integer.parseInt(binding.inputCount.text.toString())
        }
        return input
    }

    private fun clearEditText() {
        binding.inputCount.text.clear()
    }

    companion object {
        const val ZERO_INT = 0
    }
}
