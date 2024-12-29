package com.github.projektmagma.magmaapp.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.navigation.Screen

@Composable
fun OnBoardingScreen(
    navHostController: NavHostController
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .shadow(
                        elevation = 4.dp,
                        shape = MaterialTheme.shapes.large,
                    )
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(MaterialTheme.colorScheme.surface),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleLarge,
                )
                Image(
                    modifier = Modifier
                        .padding(32.dp)
                        .clip(MaterialTheme.shapes.large),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.app_name)
                )
                Text(
                    text = stringResource(id = R.string.school_in_fingertips),
                    style = MaterialTheme.typography.titleMedium,
                )
                Button(
                    onClick = {
                        navHostController.navigate(Screen.LoginScreen)
                    }
                ) {
                    Text(text = stringResource(id = R.string.login))
                }
                TextButton(
                    onClick = {
                        navHostController.navigate(Screen.RegisterScreen)
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stringResource(id = R.string.create_account))
                        Icon(
                            Icons.AutoMirrored.Filled.NavigateNext,
                            contentDescription = stringResource(id = R.string.create_account)
                        )
                    }
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = stringResource(id = R.string.report_bug),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.outline,
                textAlign = TextAlign.Center,
            )
        }
    }
}