package com.github.projektmagma.magmaapp.home.presentation.settings_screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.presentation.settings_screen.components.SettingRow
import com.github.projektmagma.magmaapp.home.presentation.shared.components.PopupWindow
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

    val displayName = viewModel.displayNameTextbox.collectAsStateWithLifecycle()
    val autoLogIn = viewModel.autoLogInCheckbox.collectAsStateWithLifecycle()
    val darkMode = viewModel.darkModeSwitch.collectAsStateWithLifecycle()
    val showEditPopup = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val clicksToLogout = 3
    var logoutTimesClicked by remember { mutableIntStateOf(0) }

    PopupWindow(
        showEditPopup,
        onRename = {
            showEditPopup.value = false
        },
        onDelete = {
            showEditPopup.value = false
            viewModel.resetDisplayNameTextbox()
        }
    ) {
        TextField(
            value = displayName.value,
            onValueChange = { viewModel.setDisplayNameTextbox(it.trim().take(25)) },
            label = { Text(stringResource(R.string.display_name_limit_info)) }
        )

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(modifier = Modifier.padding(24.dp)) {
                Text(
                    stringResource(R.string.settings),
                    modifier = Modifier.fillMaxWidth(),
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
                            snackbarHostState.showSnackbar(context.getString(R.string.settings_saved_info))
                        }
                    }
                ) {
                    Text(stringResource(R.string.settings_save_button))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        "Copyright (c) 2025 Projekt Magma",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            SettingRow {
                Text(stringResource(R.string.display_name_label))
                Button(onClick = {
                    showEditPopup.value = true
                })
                {
                    Text(stringResource(R.string.change_button))
                }
            }
            SettingRow {
                Text(stringResource(R.string.stay_logged_in_label))
                Checkbox(
                    checked = autoLogIn.value,
                    onCheckedChange = { viewModel.setAutoLogInCheckbox(it) }
                )
            }
            SettingRow {
                Text(stringResource(R.string.app_theme_switch_label))
                Switch(
                    checked = darkMode.value,
                    onCheckedChange = {
                        viewModel.setDarkModeSwitch(it)
                        isAppInDarkMode.value = it
                    }
                )
            }
            SettingRow(true) {
                Button(
                    onClick = {
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.Builder().scheme("https")
                                .authority("github.com")
                                .path("ProjektMagma/MagmaApp/issues")
                                .build()
                        ).also { context.startActivity(it) }
                    }
                ) {
                    Text(stringResource(id = R.string.report_bug))
                }
            }
            SettingRow(true) {
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
                                snackbarHostState.showSnackbar("${context.getString(R.string.clicks_left_to_logout)} ${clicksToLogout - logoutTimesClicked}")
                            }
                            logoutTimesClicked++
                        }
                    }
                ) {
                    Text(stringResource(R.string.logout_button))
                }
            }
        }
    }
}
