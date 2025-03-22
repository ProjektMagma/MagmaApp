package com.github.projektmagma.magmaapp.home.presentation.edit_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatAlignLeft
import androidx.compose.material.icons.automirrored.filled.FormatAlignRight
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatUnderlined
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.window.Dialog
import com.github.projektmagma.magmaapp.home.domain.model.Note
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor

@Composable
fun NoteTextEditor(note: Note) {

    val richTextState = rememberRichTextState()
    var showTextColorSelector by remember { mutableStateOf(false) }
    var textSelectedColor by remember { mutableStateOf(Color.White) }

    LaunchedEffect(true) {
        richTextState.setText(note.content.value)
    }

    LaunchedEffect(textSelectedColor) {
        richTextState.toggleSpanStyle(SpanStyle(color = textSelectedColor))
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
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
                    richTextState.toggleSpanStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                }
            ) {
                Icon(Icons.Filled.FormatUnderlined, contentDescription = "Underline")
            }
            IconButton(
                onClick = {
                    showTextColorSelector = true
                }
            ) {
                Icon(Icons.Filled.FormatColorText, contentDescription = "Color", tint = textSelectedColor)
            }
            AnimatedVisibility(visible = showTextColorSelector) {
                Dialog(onDismissRequest = { showTextColorSelector = false }) {
                    Row(
                        modifier = Modifier
                            .clip(shape = MaterialTheme.shapes.medium)
                            .background(color = MaterialTheme.colorScheme.surface)
                    ) {
                        IconButton(
                            onClick = {
                                textSelectedColor = Color.White
                                showTextColorSelector = false
                            }
                        ) {
                            Icon(
                                Icons.Filled.Circle,
                                contentDescription = "Color",
                                tint = Color.White
                            )
                        }
                        IconButton(
                            onClick = {
                                textSelectedColor = Color.Red
                                showTextColorSelector = false
                            }
                        ) {
                            Icon(
                                Icons.Filled.Circle,
                                contentDescription = "Color",
                                tint = Color.Red
                            )
                        }
                        IconButton(
                            onClick = {
                                textSelectedColor = Color.Green
                                showTextColorSelector = false
                            }
                        ) {
                            Icon(
                                Icons.Filled.Circle,
                                contentDescription = "Color",
                                tint = Color.Green
                            )
                        }
                        IconButton(
                            onClick = {
                                textSelectedColor = Color.Blue
                                showTextColorSelector = false
                            }
                        ) {
                            Icon(
                                Icons.Filled.Circle,
                                contentDescription = "Color",
                                tint = Color.Blue
                            )
                        }
                    }
                }
            }
            IconButton(
                onClick = {
                    richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Start))
                }
            ) {
                Icon(Icons.AutoMirrored.Filled.FormatAlignLeft, contentDescription = "AlignLeft")
            }
            IconButton(
                onClick = {
                    richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Center))
                }
            ) {
                Icon(Icons.Filled.FormatAlignCenter, contentDescription = "AlignCenter")
            }
            IconButton(
                onClick = {
                    richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Right))
                }
            ) {
                Icon(Icons.AutoMirrored.Filled.FormatAlignRight, contentDescription = "AlignRight")
            }
        }
    }
    RichTextEditor(
        modifier = Modifier.fillMaxSize(),
        state = richTextState
    )
}
