package com.github.projektmagma.magmaapp.home.presentation.home_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.presentation.HomeModifiers

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotebookSelector(
    notebook: Notebook,
    onClick: () -> Unit,
    deleteMode: MutableState<Boolean>,
    onNotebookDelete: () -> Unit
) {
    val notebookTitle by notebook.title
    val notes = notebook.notes

    Box(
        modifier = HomeModifiers.notebookButtonBox
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.large,
            )
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.secondary)
            .combinedClickable(
                onClick = { onClick() },
                onLongClick = { deleteMode.value = !deleteMode.value })
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            AnimatedVisibility(deleteMode.value) {
                IconButton(
                    modifier = Modifier.width(128.dp),
                    onClick = onNotebookDelete
                ) {
                    Row {
                        Icon(
                            modifier = HomeModifiers.iconSmallSize,
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                            tint = Color.Red
                        )
                        Text(
                            text = stringResource(R.string.notebook_deletion_label),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Red
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(
                    text = notebookTitle,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Icon(
                    modifier = HomeModifiers.iconSmallSize,
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }

            notes.forEach { note ->
                val noteTitle by note.title
                Text(
                    modifier = HomeModifiers.notebookSelectorTextPadding,
                    text = "- $noteTitle",
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            Text(
                modifier = HomeModifiers.notebookSelectorTextPadding,
                text = "${stringResource(id = R.string.notebook_creation_date)} " +
                        notebook.date,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Start
            )
        }
    }
}