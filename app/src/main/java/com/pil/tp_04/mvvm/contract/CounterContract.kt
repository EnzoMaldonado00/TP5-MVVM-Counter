package com.pil.tp_04.mvvm.contract // ktlint-disable package-name

import androidx.lifecycle.LiveData
import com.pil.tp_04.mvvm.viewmodel.CounterViewModel

interface CounterContract {
    interface Model {
        var counter: Int
        fun increment(inputValue: Int)
        fun decrement(inputValue: Int)
        fun reset()
    }
    interface ViewModel {
        fun getValue(): LiveData<CounterViewModel.CounterData>
        fun incrementValue(inputValue: Int)
        fun decrementValue(inputValue: Int)
        fun resetValue()
    }
}
