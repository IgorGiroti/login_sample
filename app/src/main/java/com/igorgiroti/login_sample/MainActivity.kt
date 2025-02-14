package com.igorgiroti.login_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.igorgiroti.login_sample.ui.MainScreen
import com.igorgiroti.login_sample.ui.theme.LoginSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSampleTheme {
                MainScreen()
            }
        }
    }
}