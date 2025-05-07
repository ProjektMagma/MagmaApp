package com.github.projektmagma.magmaapp.auth.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.auth.presentation.model.RegistrationFormState

@Composable
fun ErrorText(
    state: RegistrationFormState
) {
    val errorMsgId = when {
        state.emailError != null -> state.emailError.messageId
        state.passwordError != null -> state.passwordError.messageId
        state.repeatedPasswordError != null -> state.repeatedPasswordError.messageId
        else -> R.string.error_unknown
    }

    if (state.hasError) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(errorMsgId),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.error
        )
    }
}