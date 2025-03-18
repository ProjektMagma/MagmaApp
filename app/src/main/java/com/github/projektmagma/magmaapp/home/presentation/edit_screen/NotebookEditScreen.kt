package com.github.projektmagma.magmaapp.home.presentation.edit_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.presentation.HomeModifiers
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotebookEditScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    snackbarCoroutine: CoroutineScope,
    id: String,
    viewModel: EditNoteViewModel = koinViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val richTextState = rememberRichTextState()
    val notebook by viewModel.notebook.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getNotebookById(id)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                Row {
                    IconButton(
                        onClick = {
                            richTextState.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        }
                    ) {
                        Icon(Icons.Filled.FormatBold, contentDescription = "Bold")
                    }
                    IconButton(
                        onClick = {
                            richTextState.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Italic))
                        }
                    ) {
                        Icon(Icons.Filled.FormatItalic, contentDescription = "Italic")
                    }
                    IconButton(
                        onClick = {
                            richTextState.toggleSpanStyle(SpanStyle(Color.Red))
                        }
                    ) {
                        Icon(Icons.Filled.Circle, contentDescription = "Color", tint = Color.Red)
                    }
                    IconButton(
                        onClick = {
                            richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Center))
                        }
                    ) {
                        Icon(Icons.Filled.FormatAlignCenter, contentDescription = "AlignCenter")
                    }
                }

            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = notebook.title.value,
                onValueChange = { notebook.title.value = it }
            )
            RichTextEditor(
                modifier = Modifier.fillMaxSize(),
                state = richTextState
            )
        }
    }
}
