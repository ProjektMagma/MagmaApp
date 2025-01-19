package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.domain.model.Note
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotebookEditScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    viewModel: HomeViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val notebook = viewModel.getNotebook(viewModel.getCurrentNotebookIndex())
    var title by remember { notebook.title }
    val notes = remember { notebook.notes }

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
                    modifier = Modifier.width(128.dp),
                    onClick = {
                        notes.add(
                            Note(
                                id = 0,
                                title = mutableStateOf(context.getString(R.string.note_default_name)),
                                content = mutableStateOf(context.getString(R.string.note_default_content)),
                                date = "Today"
                            )
                        )
                    }
                ) {
                    Text(stringResource(id = R.string.add_note_button))
                }
                Button(
                    modifier = Modifier.width(128.dp),
                    onClick = {
                        navController.navigate(Screen.HomeScreen)
                    }
                ) {
                    Text(stringResource(id = R.string.exit_notebook_button))
                }
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = title,
                onValueChange = { title = it })

            LazyColumn(modifier = Modifier.clickable {
                keyboardController?.hide()
            }) {
                items(notes.size) { index ->
                    var noteTitle by remember { notes[index].title }
                    var noteContent by remember { notes[index].content }
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