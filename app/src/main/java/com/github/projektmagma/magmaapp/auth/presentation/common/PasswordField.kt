package com.github.projektmagma.magmaapp.auth.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.projektmagma.magmaapp.R

@Composable
fun PasswordField(
    modifier: Modifier,
    labelString: String,
    passwordStateString: String,
    onValueChange: (String) -> Unit,
    passwordVisible: MutableState<Boolean>,
    showPasswordVisibilityIcon: Boolean,
    isError: Boolean = false
) {
    TextField(
        modifier = modifier,
        value = passwordStateString,
        isError = isError,
        singleLine = true,
        onValueChange = { onValueChange(it.replace(" ", "")) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        label = {
            Text(
                modifier = Modifier.padding(4.dp),
                text = labelString
            )
        },
        trailingIcon = {
            if (showPasswordVisibilityIcon) {
                val image = if (passwordVisible.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description =
                    if (passwordVisible.value) stringResource(R.string.hide_password) else stringResource(
                        R.string.show_password
                    )

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = image, description)
                }

            }
        })
}