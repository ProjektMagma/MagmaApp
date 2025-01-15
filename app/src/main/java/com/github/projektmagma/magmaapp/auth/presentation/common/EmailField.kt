package com.github.projektmagma.magmaapp.auth.presentation.common

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.projektmagma.magmaapp.R

@Composable
fun EmailField(
    email: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {
    TextField(
        modifier = AuthModifiers.textFieldsModifier,
        value = email,
        onValueChange = { onValueChange(it.replace(" ", "")) },
        label = {
            Text(
                modifier = AuthModifiers.textPaddingModifier,
                text = stringResource(id = R.string.email)
            )
        },
        singleLine = true,
        isError = isError
    )
}