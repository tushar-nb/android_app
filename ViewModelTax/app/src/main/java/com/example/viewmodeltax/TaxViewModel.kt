package com.example.viewmodeltax

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class TaxViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TaxUiState())
    val uiState: StateFlow<TaxUiState> = _uiState.asStateFlow()
    private val TAG = "TaxViewModel"

    fun doCalculation(){
         val tax : Double
        val amount = _uiState.value.totalIncome
        if(amount.startsWith("-")) {
            _uiState.value.totalIncome=""
            throw InvalidIncomeException("Income cannot be negative")
        }
        val income=if(amount=="" || amount.isBlank()){ ZERO }
        else { amount.toDouble() }
        when(income){
            in START_1..END_1 ->  tax = ZERO
            in START_2..END_2 -> {   var amt :Double= ZERO + (income - LIMIT_1 )
                amt = ( SLAB_1_PERCENT *amt)
                tax = amt
            }
            in START_3..END_3 -> {   var amt :Double= ZERO + (income - LIMIT_2)
                amt = ( SLAB_2_PERCENT *amt)+ FIXED_AMOUNT_6
                tax = amt
            }
            in START_4..END_4 -> {   var amt :Double= ZERO + (income - LIMIT_3)
                amt = ( SLAB_3_PERCENT *amt)+ FIXED_AMOUNT_9
                tax = amt
            }
            in START_5..END_5 -> {   var amt :Double= ZERO + (income - LIMIT_4)
                amt = ( SLAB_4_PERCENT *amt)+ FIXED_AMOUNT_12
                tax = amt
            }
            else -> { var amt = ZERO + (income - LIMIT_5)
                amt = ( SLAB_5_PERCENT *amt) + FIXED_AMOUNT_16
                tax= amt
            }
        }
         _uiState.value = uiState.value.copy(taxAmount = tax)

    }

}