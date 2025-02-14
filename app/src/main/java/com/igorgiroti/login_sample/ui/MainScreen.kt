package com.igorgiroti.login_sample.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igorgiroti.login_sample.R
import com.igorgiroti.login_sample.ui.components.LoginCheckbox
import com.igorgiroti.login_sample.ui.components.LoginInputField
import com.igorgiroti.login_sample.ui.theme.LoginSampleTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(R.string.welcome),
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
        },
        bottomBar = {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.login))
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            LoginInputField(
                input = email,
                onInputChange = { input ->
                    email = input
                },
                placeHolder = stringResource(R.string.email),
                modifier = Modifier.padding(top = 72.dp, start = 24.dp, end = 24.dp)
            )

            LoginInputField(
                input = password,
                onInputChange = { input ->
                    password = input
                },
                placeHolder = stringResource(R.string.password),
                modifier = Modifier.padding(top = 16.dp, start = 24.dp, end = 24.dp),
                isPassword = true
            )

            LoginCheckbox(
                description = stringResource(R.string.remember),
                isChecked = isChecked,
                onCheckedChange = { value ->
                    isChecked = value
                },
                modifier = Modifier.padding(top = 8.dp, start = 24.dp)
            )
        }
    }
}

@Preview(name = "MainScreenPreview", showBackground = true)
@Composable
fun MainScreenPreview() {
    LoginSampleTheme {
        MainScreen()
    }
}
