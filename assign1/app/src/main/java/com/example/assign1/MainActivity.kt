package com.example.assign1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assign1.ui.theme.Assign1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assign1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JetPackCompose(stringResource(id = R.string.title_text), stringResource(id = R.string.intro_text),
                        stringResource(id = R.string.description_text))
                }
            }
        }
    }
}

@Composable
fun JetPackCompose(title: String,intro:String,description:String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.bg)

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Image( image, null, contentScale = ContentScale.Fit, modifier = modifier.padding(bottom = 4.dp))
        Text(
            text = title,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 16.dp)
            )
        Text(
            text = intro,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true,
    showSystemUi = true,
    name = "jetPackCompose")
@Composable
fun GreetingPreview() {
    Assign1Theme {
        JetPackCompose(stringResource(id = R.string.title_text), stringResource(id = R.string.intro_text),
            stringResource(id = R.string.description_text))
    }
}
