package com.github.projektmagma.magmaapp.home.presentation.edit_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.home.presentation.edit_screen.components.NoteTextEditor
import com.github.projektmagma.magmaapp.home.presentation.shared.components.PopupWindow
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteEditorScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    id: String,
    viewModel: NotesViewModel = koinViewModel()
) {

    val note = remember { viewModel.getNoteById(id) }
    val showEditPopup = remember { mutableStateOf(false) }
    val richTextState = rememberRichTextState()
    val appTheme = viewModel.appTheme.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getAppTheme()
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
            viewModel.updateNote(note)
            viewModel.changeModDateNotebook()
        },
        onDelete = {
            viewModel.removeNote(note)
            viewModel.changeModDateNotebook()
            navController.popBackStack()
        }
    ) {
        TextField(
            value = note.title.value,
            onValueChange = { note.title.value = it.take(20) },
            singleLine = true,
            textStyle = MaterialTheme.typography.headlineSmall
        )
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
                    onClick = { showEditPopup.value = !showEditPopup.value },
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
            NoteTextEditor(note, richTextState, appTheme)
        }
    }
}
