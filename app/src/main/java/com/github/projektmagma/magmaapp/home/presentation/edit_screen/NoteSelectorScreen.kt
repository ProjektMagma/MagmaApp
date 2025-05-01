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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.data.model.NoteDto
import com.github.projektmagma.magmaapp.home.presentation.edit_screen.components.NewNoteSelector
import com.github.projektmagma.magmaapp.home.presentation.edit_screen.components.NoteSelector
import com.github.projektmagma.magmaapp.home.presentation.shared.components.PopupWindow
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteSelectorScreen(
    navController: NavController,
    id: String,
    viewModel: NotesViewModel = koinViewModel()
) {
    val notebook by viewModel.notebook.collectAsStateWithLifecycle()
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    var showEditPopup = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.selectNotebookId(id)

        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.errorFlow.collect { stringId ->
                snackbarHostState.currentSnackbarData?.dismiss()
                snackbarHostState.showSnackbar(context.getString(stringId))
            }
        }
    }

    PopupWindow(
        showEditPopup,
        onRename = {
            showEditPopup.value = !showEditPopup.value
            viewModel.updateNotebook(notebook)
        },
        onDelete = {
            viewModel.removeNotebook(notebook)
            navController.popBackStack()
        }) {
        TextField(
            value = notebook.title.value,
            onValueChange = { notebook.title.value = it.take(20) },
            singleLine = true,
            textStyle = MaterialTheme.typography.headlineSmall
        )
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
                        viewModel.updateNotebook(notebook)
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

                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = notebook.title.value,
                    style = MaterialTheme.typography.titleMedium
                )

                IconButton(
                    onClick = { showEditPopup.value = !showEditPopup.value }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "EditTitle"
                    )
                }
            }
        }
    ) { innerPadding ->
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
                    }
                )
            }
            item {
                NewNoteSelector {
                    viewModel.addNote(
                        NoteDto(
                            title = context.getString(R.string.note_default_name),
                        )
                    )
                }
            }
        }
    }
}

