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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorIndicator(
    errorMessage: String = "An error occurred...",
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.padding(vertical = 24.dp))
        Button(
            onClick = onButtonClick
        ) {
            Text(
                text = "Retry",
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