package com.github.projektmagma.magmaapp.auth.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun OnBoardingScreen(
    navHostController: NavHostController
) {
    Scaffold { innerPadding ->
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp // Nie zmieniać bo to nie działa tak jak Android Studio mówi
        
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
                        .clip(MaterialTheme.shapes.large).size(screenHeight / 3.5f),
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
                Button(
                    onClick = {
                        navHostController.navigate(Screen.RegisterScreen)
                    }
                ) {
                    Text(text = stringResource(id = R.string.register))
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