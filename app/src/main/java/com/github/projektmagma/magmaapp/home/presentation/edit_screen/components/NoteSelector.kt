package com.github.projektmagma.magmaapp.home.presentation.edit_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.projektmagma.magmaapp.home.domain.model.Note

@Composable
fun NoteSelector(
    note: Note,
    onClick: () -> Unit,
    deleteMode: MutableState<Boolean>,
    onNoteDelete: () -> Unit
) {
    var renameMode by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .drawBehind {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (renameMode) {
                TextField(
                    value = note.title.value,
                    onValueChange = { note.title.value = it },
                    textStyle = MaterialTheme.typography.titleMedium
                )
            } else {
                Text(
                    text = "- ${note.title.value}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Surface(
                modifier = Modifier.combinedClickable(
                    onClick = {
                        if (deleteMode.value) {
                            onNoteDelete()
                        } else {
                            renameMode = !renameMode
                        }
                    },
                    onLongClick = { deleteMode.value = !deleteMode.value }
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = if (deleteMode.value) {
                        Icons.Filled.DeleteOutline
                    } else if (renameMode) {
                        Icons.Filled.Check
                    } else {
                        Icons.Filled.Edit
                    },
                    contentDescription = "Edit or delete note"
                )
            }
        }
    }
}

@Preview(name = "NoteSelector", showBackground = true)
@Composable
fun NoteSelectorPreview() {
    NoteSelector(
        note = Note(
            id = "1234",
            title = remember { mutableStateOf("Title") },
            content = remember { mutableStateOf("Content") },
            date = "2021-10-10"
        ),
        onClick = {},
        deleteMode = remember { mutableStateOf(false) },
        onNoteDelete = {}
    )
}