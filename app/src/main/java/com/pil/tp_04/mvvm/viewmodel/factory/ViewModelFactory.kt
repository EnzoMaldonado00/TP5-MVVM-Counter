package com.pil.tp_04.mvvm.viewmodel.factory // ktlint-disable package-name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pil.tp_04.mvvm.contract.CounterContract
import com.pil.tp_04.mvvm.viewmodel.CounterViewModel

// If any dependency injector will be used, this class is not necessary
class ViewModelFactory(private val params: Array<Any>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CounterViewModel::class.java -> CounterViewModel(params[0] as CounterContract.Model) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
