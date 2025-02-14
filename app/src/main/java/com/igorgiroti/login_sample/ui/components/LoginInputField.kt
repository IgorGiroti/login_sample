package com.igorgiroti.login_sample.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igorgiroti.login_sample.ui.theme.LoginSampleTheme

@Composable
fun LoginInputField(
    input: String,
    onInputChange: (String) -> Unit,
    placeHolder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        TextField(
            value = input,
            onValueChange = onInputChange,
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = if (isPassword) PasswordVisualTransformation()
            else VisualTransformation.None,
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    color = LightGray,
                    text = placeHolder,
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            trailingIcon = {
                BuildIcon(isPassword = isPassword)
            },
            colors = TextFieldDefaults.colors().copy(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(8.dp),
        )
    }
}

@Composable
private fun BuildIcon(isPassword: Boolean) {
    if (isPassword) {
        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = null,
            tint = LightGray
        )
    } else {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = null,
            tint = LightGray
        )
    }
}

@Preview(name = "InputEmailPreview")
@Composable
fun InputEmailPreview() {
    LoginSampleTheme {
        LoginInputField(
            input = "",
            onInputChange = {},
            placeHolder = "Email"
        )
    }
}

@Preview(name = "InputPasswordPreview")
@Composable
fun InputPassPreview() {
    LoginSampleTheme {
        LoginInputField(
            input = "test",
            onInputChange = {},
            placeHolder = "Password",
            isPassword = true
        )
    }
}