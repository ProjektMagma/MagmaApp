package com.github.projektmagma.magmaapp.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.projektmagma.magmaapp.core.encryption.PasswordEncryptor

@Composable
@Preview
fun LoginScreen() {
    var currentValue by remember { mutableStateOf("") }
    var previousValue by remember { mutableStateOf("nothing") }
    var message by remember { mutableStateOf("Write something") }

    Scaffold { innerPadding ->
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "WARNING - This is a testing screen, it will be deleted in the future",
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            TextField(modifier = Modifier.fillMaxWidth(),
                value = currentValue,
                onValueChange = { currentValue = it },
                label = {
                    Text(
                        modifier = Modifier.padding(4.dp), text = "Password to compare hash"
                    )
                })
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                keyboardController?.hide()
                val valHash = PasswordEncryptor.encrypt(currentValue)
                val prevValHash = PasswordEncryptor.encrypt(previousValue)
                message =
                    "Current value: $currentValue\n Previous value: $previousValue\n Current hash: $valHash\nPrevious hash: $prevValHash"
                previousValue = currentValue
                message += if (valHash == prevValHash) "\nTHEY ARE THE SAME" else "\nTHEY ARE DIFFERENT"

            }) {
                Text(
                    modifier = Modifier.padding(4.dp), text = "Check password hash"
                )
            }
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                text = message,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )


        }
    }
}