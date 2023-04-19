package com.pil.tp_04.mvvm.viewmodel // ktlint-disable package-name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pil.tp_04.mvvm.contract.MainContract

class MainViewModel(private val model: MainContract.Model) : ViewModel(), MainContract.ViewModel {

    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()
    val data: LiveData<MainData> = mutableLiveData

    fun initUI() {
        mutableLiveData.postValue(MainData(MainState.INITIAL))
    }

    override fun getValue(): LiveData<MainData> {
        return mutableLiveData
    }

    override fun incrementValue(inputValue: Int) {
        model.increment(inputValue)
        mutableLiveData.value = MainData(MainState.INC, model.counter)
    }

    override fun decrementValue(inputValue: Int) {
        model.decrement(inputValue)
        mutableLiveData.value = MainData(MainState.DEC, model.counter)
    }

    override fun resetValue() {
        model.reset()
        mutableLiveData.value = MainData(MainState.INITIAL, model.counter)
    }

    enum class MainState {
        INITIAL,
        INC,
        DEC,
    }

    data class MainData(
        val state: MainState = MainState.INITIAL,
        val value: Int = ZERO_INT,
    )

    companion object {
        private const val ZERO_INT: Int = 0
    }
}
