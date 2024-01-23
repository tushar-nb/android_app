package com.example.birthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.birthday.ui.theme.BirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(message = getString(R.string.happy_birthday_text), from = getString(
                        R.string.signature
                    ) )
                }
            }
        }
    }
}

@Composable
fun GreetingText(message: String, from:String, modifier: Modifier = Modifier){
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier
        ) {
        Text(                                //birthday wish message
            text = message,
            modifier = Modifier.padding(top = 50.dp, start=4.dp, bottom = 12.dp),
            fontSize = 80.sp,
            lineHeight = 85.sp,
            textAlign = TextAlign.Center
        )
        Text(                               // from message
            text = from,
            fontSize = 30.sp,
            color = Color.Red,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
                .align(alignment = Alignment.End)
//                .align(alignment = Alignment.CenterHorizontally)
                .background(color = Color.LightGray)
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier= Modifier) {
    val image = painterResource(R.drawable.androidparty)
    val img = painterResource(R.drawable.cake)
    Box {
        Image(image, null, contentScale = ContentScale.Crop, alpha = 0.4F)
        GreetingText(message = message, from = from,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp))

    }

}


@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "birthday"
)

@Composable
fun BirthdayPreview() {
    BirthdayTheme {
        GreetingImage(stringResource(id = R.string.happy_birthday_text), stringResource(id = R.string.signature))
    }
}