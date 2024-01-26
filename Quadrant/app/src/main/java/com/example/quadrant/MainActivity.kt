package com.example.quadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quadrant.ui.theme.QuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Quadrant(stringResource(id = R.string.title1), stringResource(id = R.string.title2), stringResource(id = R.string.title3),stringResource(id = R.string.title4),stringResource(id = R.string.desc1), stringResource(id = R.string.desc2), stringResource(id = R.string.desc3),stringResource(id = R.string.desc4))
                }
            }
        }
    }
}

@Composable
fun Quadrant(text1:String, text2:String, text3:String, text4:String, desc1:String, desc2:String, desc3:String, desc4:String, modifier:Modifier=Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box {                  //top box
            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {              //top-left
                Box(
                    modifier = modifier
                        .background(Color(0xFFEADDFF))
                        .fillMaxSize(0.5f)
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)

                ){
                    //top left
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                        Text(
                            text = text1,
                            modifier = modifier.padding(16.dp),
                            textAlign = TextAlign.Justify,
                            fontWeight = FontWeight.Bold,

                        )
                        Text(text = desc1)
                    }

                }
                Box(
                    modifier = modifier
                        .background(Color(0xFFD0BCFF))
                        .fillMaxHeight(0.5f)
                        .fillMaxWidth(1.0f)
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                ){
                    //top right
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = text2,
                            modifier = modifier.padding(16.dp),
                            textAlign = TextAlign.Justify,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = desc2)
                    }
                }
            }
        }
        Box {                  //bottom box
            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){             //bottom-left
                Box(
                    modifier = modifier
                        .background(Color(0xFFB69DF8))
                        .fillMaxHeight(1.0f)
                        .fillMaxWidth(0.5f)
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                ){
                        //bottom-left
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = text3,
                            modifier = modifier.padding(16.dp),
                            textAlign = TextAlign.Justify,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = desc3)
                    }

                }
                Box(
                    modifier = modifier
                        .background(color = Color(0xFFF6EDFF))
                        .fillMaxHeight(1.0f)
                        .fillMaxWidth(1.0f)
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                ){
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = text4,
                            modifier = modifier.padding(16.dp),
                            textAlign = TextAlign.Justify,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = desc4)
                    }

                }
            }
        }
    }
}

@Preview(
        showBackground = true,
        showSystemUi = true,
        name = "quadrant"
    )
@Composable
fun GreetingPreview() {
    QuadrantTheme {
        Quadrant(stringResource(id = R.string.title1), stringResource(id = R.string.title2), stringResource(id = R.string.title3),stringResource(id = R.string.title4),stringResource(id = R.string.desc1), stringResource(id = R.string.desc2), stringResource(id = R.string.desc3),stringResource(id = R.string.desc4))
    }
}