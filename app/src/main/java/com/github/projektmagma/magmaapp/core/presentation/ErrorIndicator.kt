package com.github.projektmagma.magmaapp.core.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.projektmagma.magmaapp.R

@Composable
fun ErrorIndicator(
    errorMessage: String = "",
    onButtonClick: () -> Unit
) {
    var mess: String = errorMessage
    if (mess.isBlank()) mess =
        stringResource(R.string.error_indicator_default_message)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = mess,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.padding(vertical = 24.dp))
        Button(
            onClick = onButtonClick
        ) {
            Text(
                text = stringResource(R.string.error_indicator_retry_button),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ErrorIndicatorPreview() {
    ErrorIndicator {}
}