package com.github.projektmagma.magmaapp.home.presentation.edit_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.home.presentation.edit_screen.components.NoteTextEditor
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteEditorScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    snackbarCoroutine: CoroutineScope,
    id: String,
    viewModel: NotesViewModel = koinViewModel()
) {

    val note = remember { viewModel.getNoteById(id) }
    var showEditPopup by remember { mutableStateOf(false) }
    val richTextState = rememberRichTextState()

    AnimatedVisibility(visible = showEditPopup) {
        Dialog(onDismissRequest = { showEditPopup = !showEditPopup }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = note.title.value,
                    onValueChange = { note.title.value = it },
                    textStyle = MaterialTheme.typography.headlineSmall
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Save")
                    IconButton(
                        onClick = {
                            showEditPopup = !showEditPopup
                            viewModel.updateNote(note)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Rename note"
                        )
                    }
                    Text("Delete")
                    IconButton(
                        onClick = {
                            viewModel.removeNote(note)
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.DeleteOutline,
                            contentDescription = "Delete note"
                        )
                    }
                }
            }
        }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        note.content.value = richTextState.toHtml()
                        viewModel.updateNote(note)
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = note.title.value,
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(
                    onClick = { showEditPopup = !showEditPopup },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit or delete note"
                    )
                }
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NoteTextEditor(note, richTextState)
        }
    }
}