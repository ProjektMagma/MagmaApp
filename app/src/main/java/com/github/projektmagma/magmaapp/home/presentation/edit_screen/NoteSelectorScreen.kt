package com.github.projektmagma.magmaapp.home.presentation.edit_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.github.projektmagma.magmaapp.home.presentation.HomeModifiers
import com.github.projektmagma.magmaapp.home.presentation.edit_screen.components.NewNoteSelector
import com.github.projektmagma.magmaapp.home.presentation.edit_screen.components.NoteSelector
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteSelectorScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    snackbarCoroutine: CoroutineScope,
    id: String,
    viewModel: NotesViewModel = koinViewModel()
) {
    val notebook by viewModel.notebook.collectAsStateWithLifecycle()
    val notes by viewModel.notes.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getNotebookById(id)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
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
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                // TODO: Tutaj zmiana Text na TextField po naciśnięciu
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = notebook.title.value,
                    onValueChange = { notebook.title.value = it }
                )
            }
        }
    ) { innerPadding ->
        val deleteMode = remember { mutableStateOf(false) }
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
        ) {
            items(notes) { note ->
                NoteSelector(
                    note,
                    onClick = {
                        navController.navigate(Screen.NoteEditorScreen(note.id))
                        viewModel.setCurrentNote(note)
                    },
                    deleteMode = deleteMode,
                    onNoteDelete = {
                        viewModel.removeNote(note)
                    }
                )
            }
            item {
                NewNoteSelector {
                    viewModel.addNote(
                        Note(
                            id = "",
                            title = mutableStateOf("Title"),
                            content = mutableStateOf("Content"),
                            date = "2021-10-10"
                        )
                    )
                }
            }
        }
    }
}
