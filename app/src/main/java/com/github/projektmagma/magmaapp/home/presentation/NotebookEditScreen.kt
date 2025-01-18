package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.home.domain.model.Note
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotebookEditScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    viewModel: HomeViewModel = koinViewModel()
) {
    val notebook = viewModel.getNotebook(viewModel.notebookId)
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
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = title,
                onValueChange = { title = it })

            LazyColumn {
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
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    notes.add(
                        Note(
                            id = 0,
                            title = mutableStateOf("New Note"),
                            content = mutableStateOf("New Content"),
                            date = "Today"
                        )
                    )
                }
            ) {
                Text("Add Note")
            }
        }
    }

}