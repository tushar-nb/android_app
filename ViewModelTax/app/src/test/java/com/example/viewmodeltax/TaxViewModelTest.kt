package com.example.viewmodeltax

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TaxViewModelTest {
    private lateinit var viewModel: TaxViewModel
    @Before
    fun setUp() {
        viewModel = TaxViewModel()
    }

    @Test
    fun doCalculation_NullValue_returnZero() {
        viewModel.uiState.value.totalIncome = ""
        viewModel.doCalculation()
        assertEquals(0.0, viewModel.uiState.value.taxAmount,0.0)
    }
    @Test
    fun doCalculation_FirstSlab_returnZero() {
        viewModel.uiState.value.totalIncome = "10000"
        viewModel.doCalculation()
        assertEquals(0.0, viewModel.uiState.value.taxAmount,0.0)
    }
    @Test
    fun doCalculation_SecondSlab_returnValue() {
        viewModel.uiState.value.totalIncome = "500000"
        viewModel.doCalculation()
        assertEquals(10000.0, viewModel.uiState.value.taxAmount,0.0)
    }
    @Test
    fun doCalculation_ThirdSlab_returnValue() {
        viewModel.uiState.value.totalIncome = "700000"
        viewModel.doCalculation()
        assertEquals(25000.0, viewModel.uiState.value.taxAmount,0.0)
    }
    @Test
    fun doCalculation_FourthSlab_returnValue() {
        viewModel.uiState.value.totalIncome = "1000000"
        viewModel.doCalculation()
        assertEquals(60000.0, viewModel.uiState.value.taxAmount,0.0)
    }
    @Test
    fun doCalculation_FifthSlab_returnValue() {
        viewModel.uiState.value.totalIncome = "1300000"
        viewModel.doCalculation()
        assertEquals(110000.0, viewModel.uiState.value.taxAmount,0.0)
    }
    @Test
    fun doCalculation_SixthSlab_returnValue() {
        viewModel.uiState.value.totalIncome = "1800000"
        viewModel.doCalculation()
        assertEquals(240000.0, viewModel.uiState.value.taxAmount,0.0)
    }
    @Test
    fun doCalculation_SlightMoreThanSlab3_returnValueOfSlab4() {
        val income = 900000+0.1
        viewModel.uiState.value.totalIncome = income.toString()
        viewModel.doCalculation()
        assertEquals(45000.015, viewModel.uiState.value.taxAmount,0.0)
    }
    @Test
    fun doCalculation_SlightLessThanSlab4_returnValueOfSlab3() {
        val income = 900000.10-1.10
        viewModel.uiState.value.totalIncome = income.toString()
        viewModel.doCalculation()
        assertEquals(44999.9, viewModel.uiState.value.taxAmount,0.0)
    }
    @Test(expected = InvalidIncomeException::class)
    fun doCalculation_NegativeIncomeTest_Error() {
        viewModel.uiState.value.totalIncome = "-500000"
        viewModel.doCalculation()
        AssertionError()
    }
}
