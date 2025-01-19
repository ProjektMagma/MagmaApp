package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.data.model.NotebookDto
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    viewModel: HomeViewModel = koinViewModel()
) {
    val snackbarScope = rememberCoroutineScope()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val user by viewModel.user.collectAsStateWithLifecycle()
    val notebooks by viewModel.notebooks.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        snackbarScope.launch { snackbarHostState.showSnackbar(context.getString(R.string.not_implemented_error)) }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(128.dp),
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = context.getString(R.string.logout_button),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                    IconButton(
                        onClick = {
                            viewModel.logout()
                            navController.navigate(Screen.AuthGraph) {
                                popUpTo(Screen.AuthGraph) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 16.dp
                ),
                text = "Hello, ${user?.email}!",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            LazyColumn(
                modifier = Modifier
                    .padding(64.dp, 0.dp)
                    .fillMaxSize()
            ) {
                items(notebooks.size) { index ->
                    val notebook = viewModel.getNotebook(index)
                    NotebookSelector(
                        notebook = notebook,
                        onClick = {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarScope.launch {
                                snackbarHostState.showSnackbar("${context.getString(R.string.notebook_selection_info)} ${notebook.title.value}")
                            }
                            viewModel.setCurrentNotebookIndex(index)
                            navController.navigate(Screen.NotebookEditScreen)
                        }
                    )
                }
                item {
                    NewNotebookSelector(
                        onClick = {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarScope.launch {
                                snackbarHostState.showSnackbar(context.getString(R.string.new_notebook_creation_info))
                            }
                            viewModel.addNotebook(
                                NotebookDto(
                                    notebooks.size + 1,
                                    context.getString(R.string.notebook_default_name),
                                    SnapshotStateList(),
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