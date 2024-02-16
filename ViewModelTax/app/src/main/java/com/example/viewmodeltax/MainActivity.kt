package com.example.viewmodeltax

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
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodeltax.ui.theme.ViewModelTaxTheme
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            ViewModelTaxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    TaxCalculate()
                }
            }
        }
    }
}

@Composable
fun TaxCalculate(modifier: Modifier = Modifier,taxViewModel: TaxViewModel = viewModel()) {

    val taxUiState by taxViewModel.uiState.collectAsState()

    var show by rememberSaveable { mutableStateOf(false) }

    Column (
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text(
            text = stringResource(id = R.string.title),
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

        var text by rememberSaveable { mutableStateOf("") }
        TextField(
            label = { Text(text = "Total Income Amount: ")},
            supportingText = {
                if(show && taxUiState.totalIncome=="") {
                    Text(text = "Enter a valid Income", color = colorScheme.error)
                }
                else if ( taxUiState.totalIncome.startsWith("-")) {
                    Text(text = "Income cannot be Negative", color = colorScheme.error)
                }
            },
            value = text,
            onValueChange = {
                if(it.length<=8) {
                    text = it
                    taxUiState.totalIncome=text
                    show = false
                }
                if(text.isEmpty()) show = false

            },
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.income), contentDescription = null,modifier = Modifier.size(24.dp)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(50.dp))

        ShowButton(show, onMutableValueChange = {show = it},taxAmount = taxUiState.taxAmount,/*calculateFun = {taxViewModel.doCalculation()},*/incomeAmount = taxUiState.totalIncome,taxViewModel)
    }
}

@Composable
fun ShowButton(
    show: Boolean,
    onMutableValueChange: (Boolean) -> Unit,
    taxAmount: Double,
//    calculateFun: () -> Unit,
    incomeAmount: String,
    taxViewModel: TaxViewModel,
) {
    Column (modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        if(show && incomeAmount!="" && !incomeAmount.startsWith("-")) {
//            calculateFun()
            taxViewModel.doCalculation()
            Text(
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = stringResource(id = R.string.message) + NumberFormat.getCurrencyInstance(Locale("en","IN")).format(taxAmount) //try to get in INR not US
            )
        }

        Button(
            onClick = {
                if(!show) {
                    onMutableValueChange(true)
                }
            },
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally))
        {
            Text(text = stringResource(id = R.string.click))
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ViewModelTaxTheme {
        TaxCalculate()
    }
}