package com.github.projektmagma.magmaapp.home.presentation.edit_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.presentation.HomeModifiers
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotebookEditScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    id: String,
    viewModel: EditNoteViewModel = koinViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val notebook by viewModel.notebook.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getNotebookById(id)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    modifier = HomeModifiers.notebookEditScreenButtonWidth,
                    onClick = {
                        // TODO logika firebase do dodawania notatki
//                        notes.add(
//                            Note(
//                                id = 0,
//                                title = mutableStateOf(context.getString(R.string.note_default_name)),
//                                content = mutableStateOf(context.getString(R.string.note_default_content)),
//                                date = "Today"
//                            )
//                        )
                    }
                ) {
                    Text(stringResource(id = R.string.add_note_button))
                }
                Button(
                    modifier = HomeModifiers.notebookEditScreenButtonWidth,
                    onClick = {
                        viewModel.updateNotebook(
                            notebook
                        )
                        navController.navigate(Screen.HomeScreen) {
                            popUpTo<Screen.HomeScreen> { inclusive = true }
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.exit_notebook_button))
                }
            }

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = notebook.title.value,
                onValueChange = { notebook.title.value = it }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        keyboardController?.hide()
                    }) {
                items(notebook.notes) { note ->
                    var noteTitle by remember { note.title }
                    var noteContent by remember { note.content }
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = noteTitle,
                        onValueChange = { noteTitle = it }
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = noteContent,
                        onValueChange = { noteContent = it }
                    )
                }
            }
        }
    }
}
