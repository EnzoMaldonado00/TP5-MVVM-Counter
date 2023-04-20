package com.pil.tp_04.mvvm.viewmodel // ktlint-disable package-name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pil.tp_04.mvvm.contract.CounterContract

class CounterViewModel(private val model: CounterContract.Model) : ViewModel(), CounterContract.ViewModel {

    private val mutableLiveData: MutableLiveData<CounterData> = MutableLiveData()
    val data: LiveData<CounterData> = mutableLiveData

    fun initUI() {
        mutableLiveData.postValue(CounterData(CounterState.INITIAL))
    }

    override fun getValue(): LiveData<CounterData> {
        return mutableLiveData
    }

    override fun incrementValue(inputValue: Int) {
        model.increment(inputValue)
        mutableLiveData.value = CounterData(CounterState.INC, model.counter)
    }

    override fun decrementValue(inputValue: Int) {
        model.decrement(inputValue)
        mutableLiveData.value = CounterData(CounterState.DEC, model.counter)
    }

    override fun resetValue() {
        model.reset()
        mutableLiveData.value = CounterData(CounterState.INITIAL, model.counter)
    }

    enum class CounterState {
        INITIAL,
        INC,
        DEC,
    }

    data class CounterData(
        val state: CounterState = CounterState.INITIAL,
        val value: Int = ZERO_INT,
    )

    companion object {
        private const val ZERO_INT: Int = 0
    }
}
