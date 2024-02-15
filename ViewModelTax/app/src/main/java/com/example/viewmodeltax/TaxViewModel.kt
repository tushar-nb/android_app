package com.example.viewmodeltax

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

const val fixedAmt6 = 15000
const val fixedAmt9= 45000
const val fixedAmt12 = 90000
const val fixedAmt16 = 150000

class TaxViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TaxUiState())
    val uiState: StateFlow<TaxUiState> = _uiState.asStateFlow()

     fun doCalculation(){
         val tax : Double
        val amount = _uiState.value.totalIncome
        val income=if(amount=="" || amount.isBlank()){ 0.0 }
        else { amount.toDouble() }
        when(income){
            in 0.0..300000.0 ->  tax = 0.0
            in 300001.0..600000.0 -> {   var amt :Double=0.00+ (income-300000)
                amt = (0.05*amt)
                tax = amt
            }
            in 600001.0..900000.0 -> {   var amt :Double=0.00+ (income-600000)
                amt = (0.1*amt)+ fixedAmt6
                tax = amt
            }
            in 900001.0..1200000.0 -> {   var amt :Double=0.00+ (income-900000)
                amt = (0.15*amt)+ fixedAmt9
                tax = amt
            }
            in 1200001.0..1500000.0 -> {   var amt :Double=0.00+ (income-1200000)
                amt = (0.2*amt)+ fixedAmt12
                tax = amt
            }
            else -> { var amt = 0.0 + (income-1500000)
                amt = (0.3*amt) + fixedAmt16
                tax= amt
            }
        }
         _uiState.value = uiState.value.copy(taxAmount = tax)

    }

}