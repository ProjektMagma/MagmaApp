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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.data.model.NoteDto
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
    var titleEditMode by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.selectNotebookById(id)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                IconButton(
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
                if (titleEditMode) {
                    TextField(
                        value = notebook.title.value,
                        onValueChange = { notebook.title.value = it.take(20) },
                        textStyle = MaterialTheme.typography.titleMedium,
                    )
                } else {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp),
                        text = notebook.title.value,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                IconButton(
                    onClick = { titleEditMode = !titleEditMode }
                ) {
                    Icon(
                        imageVector = if (titleEditMode) Icons.Filled.Check else Icons.Filled.Edit,
                        contentDescription = "EditTitle"
                    )
                }
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
                        NoteDto(
                            id = "",
                            title = "New note",
                            content = "",
                            timestamp = System.currentTimeMillis()
                        )
                    )
                }
            }
        }
    }
}
