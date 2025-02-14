package com.igorgiroti.login_sample.ui.state


sealed interface LoginState<out T> {
    data object Initial : LoginState<Nothing>
    data object Loading : LoginState<Nothing>
    data class Success<out R>(val data: R) : LoginState<R>
}
