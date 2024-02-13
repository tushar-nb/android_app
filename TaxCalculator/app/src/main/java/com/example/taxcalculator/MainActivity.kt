package com.example.taxcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.taxcalculator.ui.theme.TaxCalculatorTheme
import java.text.NumberFormat
import java.util.Locale


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
fun EditNumberField(onValueChange: (String)->Unit,
                    modifier: Modifier=Modifier,
                    amountInput:String,
                    leadingIcon:Int,
                    show:Boolean, onMutableValueChange: (Boolean) -> Unit,
){
    Column {
        var text by remember { mutableStateOf("") }
        val maxChar = 8
        TextField(
            label = { Text(text = "Total Income Amount:")},
            supportingText = {
                             if(show && amountInput== " "){
                                 Text(text = "Enter a valid Income",color = MaterialTheme.colorScheme.error)
                             }
            },
            leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null,modifier = Modifier.size(24.dp))},
            value = text,
            onValueChange = {
                if(it.length<=maxChar) {
                    text = it
                    onValueChange(text)
                    onMutableValueChange(false)
                }
                if(text.isEmpty()) onMutableValueChange(false)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
        )   
    }
}

//additional surcharge and c ess charges
    const val fixedAmt6 = 15000
    const val fixedAmt9= 45000
    const val fixedAmt12 = 90000
    const val fixedAmt16 = 150000
fun computeTax(amount:String):Double{               //kept public for testing purpose

    val income=if(amount==" "){
         0.0
    }
    else {
        amount.toDouble()
    }
    when( income){
        in 0.0..300000.0 -> return 0.0
        in 300001.0..600000.0 -> {   var amt :Double=0.00+ (income-300000)
                                            amt = (0.05*amt)
                                            return amt
                                       }
        in 600001.0..900000.0 -> {   var amt :Double=0.00+ (income-600000)
                                            amt = (0.1*amt)+ fixedAmt6
                                            return amt
                                        }
        in 900001.0..1200000.0 -> {   var amt :Double=0.00+ (income-900000)
                                             amt = (0.15*amt)+ fixedAmt9
                                             return amt
                                         }
        in 1200001.0..1500000.0 -> {   var amt :Double=0.00+ (income-1200000)
                                             amt = (0.2*amt)+ fixedAmt12
                                             return amt
                                          }
        else -> { var amt = 0.0 + (income-1500000)
                  amt = (0.3*amt) + fixedAmt16
                  return amt
                }
    }
}



@Composable
fun  CalculateTax(message: String,title:String) {

    var show by remember { mutableStateOf(false) }
    var amountInput by remember {
        mutableStateOf(" ")
    }
    if(amountInput=="") {
        amountInput=" "
    }
    val tax = computeTax(amountInput)

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
        EditNumberField(
            onValueChange = {amountInput = it},
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            amountInput,
            leadingIcon = R.drawable.income,show,onMutableValueChange = {show = it})
        Spacer(modifier = Modifier.height(50.dp))
        ShowButton(message,tax,amountInput, show, onMutableValueChange = {show = it})

    }
}

@Composable
fun ShowButton(message: String, tax: Double,amountInput: String,show:Boolean, onMutableValueChange:(Boolean)->Unit) {

//    var show by remember { mutableStateOf(false) }

    Column (

        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(show && amountInput!=" " ) {
            Text(
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
//                text = message+ tax.toString()
                text = message + NumberFormat.getCurrencyInstance(Locale.US).format(tax) //try to get in INR not US
            )
        }
//        if(amountInput.length ==len-1) show = false
        Button(
            onClick = {
                if(!show) {
//                    show.value = true
                    onMutableValueChange(true)
                }
            },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally))
        {
            Text(text = "Calculate")
        }
    }

}


@Preview(showBackground = true, showSystemUi = true, name="calculate tax")
@Composable
fun GreetingPreview() {
    TaxCalculatorTheme {
        CalculateTax(stringResource(id = R.string.message), stringResource(id = R.string.title))
    }
}