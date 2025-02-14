package com.igorgiroti.login_sample.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.igorgiroti.login_sample.R
import com.igorgiroti.login_sample.ui.components.LoginCheckbox
import com.igorgiroti.login_sample.ui.components.LoginInputField
import com.igorgiroti.login_sample.ui.components.LoginLoadingIndicator
import com.igorgiroti.login_sample.ui.model.LoginModel
import com.igorgiroti.login_sample.ui.state.LoginState
import com.igorgiroti.login_sample.ui.viewModel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier,
) {
    val savedModel = viewModel.getSavedLogin()
    Log.d("teste", "savedModel = $savedModel")
    var email by remember { mutableStateOf(savedModel.email) }
    var password by remember { mutableStateOf(savedModel.password) }
    var isChecked by remember {
        mutableStateOf((savedModel.email.isNotEmpty() && savedModel.password.isNotEmpty()))
    }

    val uiState by viewModel.uiState.collectAsState()
    val showError by viewModel.showErrorBottomSheet.collectAsState()
    val sheetState = rememberModalBottomSheetState()

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
            val isInitial = uiState is LoginState.Initial
            Button(
                enabled = viewModel.checkButtonEnabled(email = email, password = password),
                onClick = {
                    if (isInitial) {
                        viewModel.doLogin(
                            LoginModel(
                                email = email,
                                password = password
                            ),
                            shouldRemember = isChecked
                        )
                    } else {
                        viewModel.resetLogin()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = if (isInitial) stringResource(R.string.login)
                    else stringResource(R.string.reset)
                )
            }
        }
    ) { padding ->

        if (showError) {
            ErrorBottomSheet(
                sheetState = sheetState,
                onDismiss = {
                    viewModel.dismissBottomSheet()
                }
            )
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            when (uiState) {
                is LoginState.Initial -> {
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

                is LoginState.Loading -> {
                    LoginLoadingIndicator()
                }

                is LoginState.Success -> {
                    val data = (uiState as LoginState.Success<LoginModel?>).data

                    Text(
                        modifier = Modifier.padding(top = 64.dp),
                        text = stringResource(R.string.logged) + data?.email,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        content = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.error_description),
                    style = MaterialTheme.typography.bodyMedium
                )
                Button(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = onDismiss
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.close),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}