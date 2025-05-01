package com.github.projektmagma.magmaapp.home.presentation.edit_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatAlignLeft
import androidx.compose.material.icons.automirrored.filled.FormatAlignRight
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatAlignJustify
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatUnderlined
import androidx.compose.material.icons.filled.TextDecrease
import androidx.compose.material.icons.filled.TextIncrease
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor

@Composable
fun NoteTextEditor(note: Note, richTextState: RichTextState, appTheme: State<Boolean>) {

    var showTextColorSelector = remember { mutableStateOf(false) }
    var textSelectedColor = remember { mutableStateOf(Color.Unspecified) }
    var showBackColorSelector = remember { mutableStateOf(false) }
    var backSelectedColor = remember { mutableStateOf(Color.Unspecified) }
    var baseFontSize = remember { mutableStateOf(16.sp) }

    LaunchedEffect(true) {
        richTextState.setHtml(note.content.value)
        richTextState.addSpanStyle(SpanStyle(fontSize = baseFontSize.value, fontFamily = FontFamily.SansSerif))
    }

    LaunchedEffect(textSelectedColor.value) {
        richTextState.toggleSpanStyle(SpanStyle(color = textSelectedColor.value))
    }

    LaunchedEffect(backSelectedColor.value) {
        richTextState.toggleSpanStyle(SpanStyle(background = backSelectedColor.value))
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.Center
        ) {
            Row {
                StyleChangeButton(Icons.Filled.FormatBold, "Bold") {
                    richTextState.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
                }

                StyleChangeButton(Icons.Filled.FormatItalic, "Italic") {
                    richTextState.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Italic))
                }
                StyleChangeButton(Icons.Filled.FormatUnderlined, "Underline") {
                    richTextState.toggleSpanStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                }
            }

            Row {

                ColorPicker(
                    Icons.Filled.FormatColorText,
                    textSelectedColor,
                    showTextColorSelector,
                    appTheme
                )
                ColorPicker(
                    Icons.Filled.FormatColorFill,
                    backSelectedColor,
                    showBackColorSelector,
                    appTheme
                )
            }
            Row {

                StyleChangeButton(Icons.AutoMirrored.Filled.FormatAlignLeft, "AlignLeft") {
                    richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Start))
                }

                StyleChangeButton(Icons.Filled.FormatAlignCenter, "AlignCenter") {
                    richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Center))
                }

                StyleChangeButton(Icons.AutoMirrored.Filled.FormatAlignRight, "AlignRight") {
                    richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Right))
                }

                StyleChangeButton(Icons.Filled.FormatAlignJustify, "AlignJustify") {
                    richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Justify))
                }
            }
            Row {
                StyleChangeButton(Icons.Filled.TextIncrease, "IncreaseTextSize") {
                    var newFontSize = (richTextState.getSpanStyle(
                        richTextState.selection
                    ).fontSize.value + 2).sp

                    if (newFontSize.value.isNaN()) newFontSize = baseFontSize.value

                    richTextState.addSpanStyle(SpanStyle(fontSize = newFontSize))
                }

                StyleChangeButton(Icons.Filled.TextDecrease, "DecreaseTextSize") {
                    var newFontSize = (richTextState.getSpanStyle(
                        richTextState.selection
                    ).fontSize.value - 2).sp

                    if (newFontSize.value.isNaN()) newFontSize = baseFontSize.value

                    richTextState.addSpanStyle(SpanStyle(fontSize = newFontSize))
                }
            }

        }
    }
    RichTextEditor(
        modifier = Modifier.fillMaxSize(),
        state = richTextState
    )
}
