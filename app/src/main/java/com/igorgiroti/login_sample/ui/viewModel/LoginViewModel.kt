package com.igorgiroti.login_sample.ui.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorgiroti.login_sample.ui.model.LoginModel
import com.igorgiroti.login_sample.ui.state.LoginState
import com.igorgiroti.login_sample.utils.EMPTY
import com.igorgiroti.login_sample.utils.SAVED_EMAIL
import com.igorgiroti.login_sample.utils.SAVED_PASSWORD
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginState<LoginModel>> =
        MutableStateFlow(LoginState.Initial)
    val uiState = _uiState

    private val _showErrorBottomSheet: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showErrorBottomSheet = _showErrorBottomSheet

    private val correctLogin1 = LoginModel(
        email = "teste@login.com",
        password = "1234"
    )

    private val correctLogin2 = LoginModel(
        email = "teste2@login.com",
        password = "1234"
    )

    fun doLogin(loginModel: LoginModel, shouldRemember: Boolean = false) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { LoginState.Loading }
            delay(2000)
            if (loginModel == correctLogin1 || loginModel == correctLogin2) {
                _uiState.update { LoginState.Success(loginModel) }
                if (shouldRemember) {
                    rememberLogin(loginModel = loginModel)
                } else {
                    forgetLogin()
                }
            } else {
                _uiState.update { LoginState.Initial }
                _showErrorBottomSheet.update { true }
            }
        }
    }

    fun getSavedLogin(): LoginModel {
        return LoginModel(
            email = sharedPreferences.getString(SAVED_EMAIL, null) ?: EMPTY,
            password = sharedPreferences.getString(SAVED_PASSWORD, null) ?: EMPTY
        )
    }

    fun dismissBottomSheet() {
        _showErrorBottomSheet.update { false }
    }

    fun resetLogin() {
        _uiState.update { LoginState.Initial }
    }

    fun checkButtonEnabled(email: String, password: String) =
        (email.isNotEmpty() && password.isNotEmpty())

    private fun rememberLogin(loginModel: LoginModel) {
        sharedPreferences.edit().putString(SAVED_EMAIL, loginModel.email).apply()
        sharedPreferences.edit().putString(SAVED_PASSWORD, loginModel.password).apply()
    }

    private fun forgetLogin() {
        sharedPreferences.edit().putString(SAVED_EMAIL, EMPTY).apply()
        sharedPreferences.edit().putString(SAVED_PASSWORD, EMPTY).apply()
    }
}
