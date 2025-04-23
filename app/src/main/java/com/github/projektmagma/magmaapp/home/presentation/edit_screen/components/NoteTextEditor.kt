package com.github.projektmagma.magmaapp.home.presentation.edit_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatAlignLeft
import androidx.compose.material.icons.automirrored.filled.FormatAlignRight
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatUnderlined
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor

@Composable
fun NoteTextEditor(note: Note, richTextState: RichTextState) {

    var showTextColorSelector = remember { mutableStateOf(false) }
    var textSelectedColor = remember { mutableStateOf(Color.White) }
    var showBackColorSelector = remember { mutableStateOf(false) }
    var backSelectedColor = remember { mutableStateOf(Color.Unspecified) }

    LaunchedEffect(true) {
        richTextState.setHtml(note.content.value)
    }

    LaunchedEffect(textSelectedColor.value) {
        richTextState.toggleSpanStyle(SpanStyle(color = textSelectedColor.value))
    }

    LaunchedEffect(backSelectedColor.value) {
        richTextState.toggleSpanStyle(SpanStyle(background = backSelectedColor.value))
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            StyleChangeButton(Icons.Filled.FormatBold, "Bold") {
                richTextState.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
            }

            StyleChangeButton(Icons.Filled.FormatItalic, "Italic") {
                richTextState.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Italic))
            }
            StyleChangeButton(Icons.Filled.FormatUnderlined, "Underline") {
                richTextState.toggleSpanStyle(SpanStyle(textDecoration = TextDecoration.Underline))
            }

            ColorPicker(Icons.Filled.FormatColorText, textSelectedColor, showTextColorSelector)
            ColorPicker(Icons.Filled.FormatColorFill, backSelectedColor, showBackColorSelector)

            StyleChangeButton(Icons.AutoMirrored.Filled.FormatAlignLeft, "AlignLeft") {
                richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Start))
            }

            StyleChangeButton(Icons.Filled.FormatAlignCenter, "AlignCenter") {
                richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Center))
            }

            StyleChangeButton(Icons.AutoMirrored.Filled.FormatAlignRight, "AlignRight") {
                richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Right))
            }


        }
    }
    RichTextEditor(
        modifier = Modifier.fillMaxSize(),
        state = richTextState
    )
}
