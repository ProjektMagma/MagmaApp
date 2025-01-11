package com.github.projektmagma.magmaapp.auth.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.projektmagma.magmaapp.R
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun LoginScreen() {
    val viewmodel = koinViewModel<AuthViewModel>()

    var email by viewmodel.email
    var password by viewmodel.password


    Scaffold { innerPadding ->
        val keyboardController = LocalSoftwareKeyboardController.current
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(32.dp))
            Text(
                modifier = Modifier.padding(4.dp),
                text = stringResource(id = R.string.login_banner),
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.email)
                    )
                })
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.password)
                    )
                })
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .padding(4.dp),
                onClick = {
                    keyboardController?.hide()
                    val isValid = viewmodel.isEmailAndPasswordValid()
                    if (isValid) {
                        Toast.makeText(context, R.string.login_success, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, R.string.login_failure, Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(
                    modifier = Modifier.padding(4.dp), text = stringResource(id = R.string.login),
                )
            }
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .padding(4.dp),
                onClick = {
                    keyboardController?.hide()
                }
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = stringResource(id = R.string.register_redirect_button),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}