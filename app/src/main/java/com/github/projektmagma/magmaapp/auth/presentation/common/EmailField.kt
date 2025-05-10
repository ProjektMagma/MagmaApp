package com.github.projektmagma.magmaapp.auth.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.projektmagma.magmaapp.R

@Composable
fun EmailField(
    email: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        value = email,
        onValueChange = { onValueChange(it.replace(" ", "")) },
        label = {
            Text(
                modifier = Modifier.padding(4.dp),
                text = stringResource(id = R.string.email)
            )
        },
        singleLine = true,
        isError = isError
    )
}