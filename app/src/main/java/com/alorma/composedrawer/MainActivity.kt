package com.alorma.composedrawer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
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