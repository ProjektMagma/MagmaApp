package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.home.HomeViewModel
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
    val user = viewModel.user!!
    var snackbarMessage by remember { mutableStateOf(context.getString(R.string.login_success)) }

    val notebooks = remember {
        mutableStateListOf(
            "Math",
            "English",
            "My top secret notes",
            "Shopping list",
            "Ideas"
        )
    }


    LaunchedEffect(snackbarMessage) {
        snackbarScope.launch {
            snackbarHostState.showSnackbar(snackbarMessage)
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = 16.dp
                ),
                text = "Hello, ${user.email}!",
                style = MaterialTheme.typography.titleLarge,
            )
            LazyColumn(
                modifier = Modifier
                    .width(300.dp)
            ) {
                items(notebooks.size) { index ->
                    NotebookSelector(
                        title = notebooks[index],
                        notesTitles = listOf("Note 1", "Note 2", "Note 3"),
                        onClick = {
                            snackbarMessage =
                                "${context.getString(R.string.notebook_selection_info)} ${notebooks[index]}"
                        }
                    )
                }
                item {
                    NewNotebookSelector(
                        onClick = {
                            snackbarMessage = context.getString(R.string.new_notebook_creation_info)
                            notebooks.add(context.getString(R.string.notebook_default_name))
                            keyboardController?.hide()
                        }
                    )
                }
            }
        }

    }
}