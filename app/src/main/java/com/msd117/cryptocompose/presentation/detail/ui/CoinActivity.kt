package com.msd117.cryptocompose.presentation.detail.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.msd117.cryptocompose.theme.CryptoComposeTheme
import com.msd117.cryptocompose.theme.ui.shared.SharedElement
import com.msd117.cryptocompose.theme.ui.shared.SharedElementRoot
import com.msd117.cryptocompose.theme.ui.shared.SharedElementType

class CoinActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var state by remember { mutableStateOf(false) }

    SharedElementRoot {
        Column(
            modifier = Modifier
                .fillMaxHeight(.5f)
                .fillMaxWidth()
                .background(color = Color.Gray)
        ) {
            if (!state) {
                SharedElement(
                    tag = "box", type = SharedElementType.From, modifier = Modifier
                        .size(40.dp)
                        .offset(x = 40.dp, y = 60.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Red)
                    )
                }
            }
            if (state) {
                SharedElement(
                    tag = "box", type = SharedElementType.To, modifier = Modifier
                        .size(60.dp)
                        .offset(x = 100.dp, y = 200.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Red)
                    )
                }
            }
        }
        Row(modifier = Modifier.fillMaxHeight(.5f)) {
            Column(modifier = Modifier.align(Alignment.Bottom)) {
                Button(
                    onClick = { state = !state },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    Text(text = "Click")
                }
            }
        }
    }
}