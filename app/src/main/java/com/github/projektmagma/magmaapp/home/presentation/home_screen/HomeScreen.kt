package com.github.projektmagma.magmaapp.home.presentation.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.presentation.ErrorIndicator
import com.github.projektmagma.magmaapp.core.presentation.LoadingIndicator
import com.github.projektmagma.magmaapp.core.presentation.UIState
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import com.github.projektmagma.magmaapp.home.presentation.HomeModifiers
import com.github.projektmagma.magmaapp.home.presentation.home_screen.components.NewNotebookSelector
import com.github.projektmagma.magmaapp.home.presentation.home_screen.components.NotebookSelector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    snackbarCoroutine: CoroutineScope,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val user by homeViewModel.user.collectAsStateWithLifecycle()
    val notebooks by homeViewModel.notebooks.collectAsStateWithLifecycle()
    val deleteMode = remember { mutableStateOf(false) }
    val displayName by remember { homeViewModel.displayName }
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        if (user == null) {
            homeViewModel.logout()
            navController.navigate(Screen.AuthGraph) {
                popUpTo(Screen.AuthGraph) {
                    inclusive = true
                }
            }
        }
    }

    when (uiState) {
        UIState.Loading -> {
            LoadingIndicator()
        }

        UIState.Error -> {
            ErrorIndicator { /* TODO system ponownej próby */}
        }

        UIState.Success ->
            Scaffold(
                topBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.SettingsScreen)
                            }
                        ) {
                            Icon(
                                modifier = HomeModifiers.iconBigSize,
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = null
                            )
                        }
                    }
                    Row(modifier = Modifier.padding(top = 24.dp)) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "Hello, ${displayName}!",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .then(Modifier.padding(horizontal = 32.dp))
                        .fillMaxSize()
                ) {
                    itemsIndexed(notebooks) { index, notebook ->
                        NotebookSelector(
                            notebook = notebook,
                            onClick = {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar("${context.getString(R.string.notebook_selection_info)} ${notebook.title.value}")
                                }
                                navController.navigate(Screen.NoteSelectorScreen(notebooks[index].id))
                            },
                            deleteMode = deleteMode,
                            onNotebookDelete = {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar("Note was removed")
                                }
                                homeViewModel.removeNotebook(notebook)
                            }
                        )
                    }
                    item {
                        NewNotebookSelector(
                            onClick = {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(context.getString(R.string.new_notebook_creation_info))
                                }
                                homeViewModel.addNotebook(
                                    NotebookDto(
                                        title = context.getString(R.string.notebook_default_name),
                                    )
                                )
                                keyboardController?.hide()
                            }
                        )
                    }
                }
            }
    }
}
