package com.example.taxcalculator

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TaxValidatorTest {
    @Test
    fun testWrongTax() {
        assertEquals(0.0, computeTax("600000"),0.0)
    }

    @Test
    fun testCorrectTax() {
        assertEquals(0.0, computeTax("0"),0.0)
    }
}