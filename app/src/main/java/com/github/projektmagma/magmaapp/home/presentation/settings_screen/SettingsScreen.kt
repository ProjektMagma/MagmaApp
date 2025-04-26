package com.github.projektmagma.magmaapp.home.presentation.settings_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.presentation.settings_screen.components.SettingRow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    snackbarCoroutine: CoroutineScope,
    isAppInDarkMode: MutableState<Boolean>,
    viewModel: SettingsViewModel = koinViewModel()
) {

    var logoutTimesClicked by remember { mutableIntStateOf(0) }
    val clicksToLogout = 3
    val appThemeState = viewModel.appThemeValue.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                        viewModel.getAppTheme()
                        isAppInDarkMode.value = appThemeState.value
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
            Row(modifier = Modifier.padding(top = 24.dp)) {
                Text(
                    "Settings", modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        viewModel.saveSettings()
                        navController.popBackStack()
                        snackbarCoroutine.launch {
                            snackbarHostState.showSnackbar("Settings saved!")
                        }
                    }
                ) {
                    Text("Save Settings")
                }
            }
        }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SettingRow {
                Text("Display name")
                TextField(
                    value = viewModel.displayNameTextbox,
                    onValueChange = { viewModel.displayNameTextbox = it.trim().take(25) },
                    label = { Text("Max 25 characters, spaces disallowed (requires restart)") }
                )
            }
            SettingRow {
                Text("Stay logged in")
                Checkbox(
                    checked = viewModel.autoLogInCheckbox,
                    onCheckedChange = { viewModel.autoLogInCheckbox = it }
                )
            }
            SettingRow {
                Text("Dark mode")
                Switch(
                    checked = viewModel.darkModeSwitch,
                    onCheckedChange = {
                        viewModel.darkModeSwitch = it
                        isAppInDarkMode.value = it
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        if (logoutTimesClicked == clicksToLogout) {
                            viewModel.logout()
                            navController.navigate(Screen.AuthGraph) {
                                popUpTo(Screen.AuthGraph) {
                                    inclusive = true
                                }
                            }
                        } else {
                            snackbarCoroutine.launch {
                                snackbarHostState.showSnackbar("Click ${clicksToLogout - logoutTimesClicked} more times to logout")
                            }
                            logoutTimesClicked++
                        }
                    }
                ) {
                    Text("Logout")
                }
            }
        }
    }
}
