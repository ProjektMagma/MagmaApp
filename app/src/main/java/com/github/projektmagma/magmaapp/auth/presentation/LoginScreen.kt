package com.github.projektmagma.magmaapp.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen() {
    val viewmodel = koinViewModel<AuthViewModel>()

    var password by viewmodel.password
    val encryptedPassword by viewmodel.encryptedPassword.collectAsStateWithLifecycle()
    val users by viewmodel.users.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewmodel.getUsers()
    }

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
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        modifier = Modifier.padding(4.dp), text = "Password to compare hash"
                    )
                })
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    keyboardController?.hide()
                    viewmodel.encryptPassword(password)
                }
            ) {
                Text(
                    modifier = Modifier.padding(4.dp), text = "Check password hash"
                )
            }
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                text = "Current value: $encryptedPassword",
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
            Text("CZYSTY TEST")
            LazyColumn {
                items(users) { user ->
                    Column(
                        Modifier
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = user.group.group_sign
                        )
                        Text(
                            text = user.user.first_name
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }

        }
    }
}