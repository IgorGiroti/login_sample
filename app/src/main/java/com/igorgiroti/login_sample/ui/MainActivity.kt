package com.igorgiroti.login_sample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.igorgiroti.login_sample.ui.theme.LoginSampleTheme
import com.igorgiroti.login_sample.ui.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSampleTheme {
                MainScreen(loginViewModel)
            }
        }
    }
}