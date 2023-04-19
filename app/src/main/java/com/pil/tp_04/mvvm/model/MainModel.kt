package com.pil.tp_04.mvvm.model // ktlint-disable package-name

import com.pil.tp_04.mvvm.contract.MainContract

class MainModel : MainContract.Model {
    override var counter: Int = ZERO_INT

    override fun increment(inputValue: Int) {
        counter += inputValue
    }

    override fun decrement(inputValue: Int) {
        counter -= inputValue
    }

    override fun reset() {
        counter = ZERO_INT
    }

    companion object {
        private const val ZERO_INT = 0
    }
}
