package com.igorgiroti.login_sample.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igorgiroti.login_sample.ui.theme.LoginSampleTheme

@Composable
fun LoginCheckbox(
    description: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 0.dp) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange
            )
        }
        Text(
            text = description,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(name = "CheckBoxPreview")
@Composable
fun CheckBoxPreview() {
    LoginSampleTheme {
        LoginCheckbox(
            description = "Remember me",
            isChecked = false,
            onCheckedChange = {}
        )
    }
}
