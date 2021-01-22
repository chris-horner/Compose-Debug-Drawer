package com.alorma.composedrawer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.alorma.composedrawer.ui.ComposeDrawerTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDrawerTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun DefaultPreview() {
    ComposeDrawerTheme {
        HomeScreen()
    }
}