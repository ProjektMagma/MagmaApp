package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.projektmagma.magmaapp.home.domain.model.Notebook

@Composable
fun NotebookSelector(
    modifier: Modifier = Modifier,
    notebook: Notebook,
    onClick: () -> Unit
) {
    val notebookTitle = notebook.title.value
    val notes = notebook.notes
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(0.dp, 16.dp)
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.large,
            )
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.secondary)
            .clickable { onClick() },
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp, 32.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = notebookTitle,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondary
            )
            notes.forEach { note ->
                val noteTitle = note.title.value
                Text(
                    modifier = Modifier.padding(32.dp, 0.dp),
                    text = "- $noteTitle",
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}