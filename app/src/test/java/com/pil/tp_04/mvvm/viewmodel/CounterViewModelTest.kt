package com.pil.tp_04.mvvm.viewmodel // ktlint-disable package-name

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pil.tp_04.mvvm.contract.CounterContract
import com.pil.tp_04.mvvm.model.CounterModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

// ktlint-disable package-name

class CounterViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CounterContract.ViewModel

    @Before
    fun setup() {
        viewModel = CounterViewModel(CounterModel())
    }

    @Test
    fun `on btn inc pressed on initial state test`() {
        viewModel.incrementValue(SEVENTEEN_INT)
        assertEquals(SEVENTEEN_INT, viewModel.getValue().value?.value)
        assertEquals(CounterViewModel.CounterState.INC, viewModel.getValue().value?.state)
    }

    @Test
    fun `on inc btn pressed after dec btn pressed test`() {
        viewModel.decrementValue(SEVENTEEN_INT)
        viewModel.incrementValue(THREE_INT)
        assertEquals(MINUS_FOURTEEN_INT, viewModel.getValue().value?.value)
        assertEquals(CounterViewModel.CounterState.INC, viewModel.getValue().value?.state)
    }

    @Test
    fun `on btn dec pressed on initial state test`() {
        viewModel.decrementValue(THREE_INT)
        assertEquals(MINUS_THREE_INT, viewModel.getValue().value?.value)
        assertEquals(CounterViewModel.CounterState.DEC, viewModel.getValue().value?.state)
    }

    @Test
    fun `on dec btn pressed after inc btn pressed test`() {
        viewModel.incrementValue(THREE_INT)
        viewModel.decrementValue(THREE_INT)
        assertEquals(ZERO_INT, viewModel.getValue().value?.value)
        assertEquals(CounterViewModel.CounterState.DEC, viewModel.getValue().value?.state)
    }

    @Test
    fun `on btn reset pressed test`() {
        viewModel.resetValue()
        assertEquals(ZERO_INT, viewModel.getValue().value?.value)
        assertEquals(CounterViewModel.CounterState.INITIAL, viewModel.getValue().value?.state)
    }

    companion object {
        const val SEVENTEEN_INT = 17
        const val ZERO_INT = 0
        const val THREE_INT = 3
        const val MINUS_THREE_INT = -3
        const val MINUS_FOURTEEN_INT = -14
    }
}
