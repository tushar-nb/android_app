package com.example.taxcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taxcalculator.ui.theme.TaxCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaxCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculateTax(stringResource(id = R.string.message), stringResource(id = R.string.title))
                }
            }
        }
    }
}

@Composable
fun editNumberField( onValueChange: (String)->Unit,modifier: Modifier=Modifier,amountInput:String,leadingIcon:Int){

    Column {
        var text by remember { mutableStateOf("") }
        val maxChar = 9
        TextField(
            label = { Text(text = "Total Income Amount:")},
            leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null,modifier = Modifier.size(24.dp))},
//            value = amountInput,
            value = text,
//            onValueChange = onValueChange,
            onValueChange = {
                if(it.length<=maxChar) {
                    text = it
                    onValueChange(text)
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
        )   
    }
}

private fun computeTax(amount:String):Double{
    var income:Double
    if(amount==" "){
         income = 0.0
    }
    else {
        income = amount.toDouble()
    }
    when( income){
        in 0.0..300000.0 -> return 0.0
        in 300001.0..600000.0 -> {   var amt :Double=0.00+ (income-300000)
                                            amt = (0.05*amt)
                                            return amt
                                       }
        in 600001.0..900000.0 -> {   var amt :Double=0.00+ (income-600000)
                                            amt = (0.1*amt)+15000
                                            return amt
                                        }
        in 900001.0..1200000.0 -> {   var amt :Double=0.00+ (income-900000)
                                             amt = (0.15*amt)+45000
                                             return amt
                                         }
        in 1200001.0..1500000.0 -> {   var amt :Double=0.00+ (income-1200000)
                                             amt = (0.2*amt)+90000
                                             return amt
                                          }
        else -> { var amt = 0.0 + (income-1500000)
                  amt = (0.3*amt) + 150000
                  return amt
                }
    }
}

@Composable
private fun displayTax(message:String, tax:Double) {
    Text(
        modifier = Modifier
            .padding(16.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        text = message+ tax.toString()
    )
}

@Composable
fun  CalculateTax(message: String,title:String, modifier: Modifier = Modifier) {

    var amountInput by remember {
        mutableStateOf(" ")
    }
    if(amountInput=="") {
        amountInput=" "
    }
    val tax = computeTax(amountInput)

    Column {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp)
                .fillMaxHeight(0.06f)
                .background(color = Color.LightGray)
                .verticalScroll(rememberScrollState())
                .wrapContentHeight(align = Alignment.CenterVertically),
            textAlign = TextAlign.Center,

            fontSize = 20.sp,
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.height(50.dp))
        editNumberField(
            onValueChange = {amountInput = it},
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            amountInput,
            leadingIcon = R.drawable.income)
        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick = {/*todo*/},
            colors = ButtonDefaults.buttonColors( Color.Blue),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        ) {
            Text(text = "Calculate")
        }

        if(amountInput!=" " ) {
            displayTax(message = message, tax =tax )
        }

    }


}
@Composable
fun someFunction() {
    TODO("Not yet implemented")
}



@Preview(showBackground = true, showSystemUi = true, name="calculate tax")
@Composable
fun GreetingPreview() {
    TaxCalculatorTheme {
        CalculateTax(stringResource(id = R.string.message), stringResource(id = R.string.title))
    }
}