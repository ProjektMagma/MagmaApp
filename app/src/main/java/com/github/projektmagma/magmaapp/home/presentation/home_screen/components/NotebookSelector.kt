package com.github.projektmagma.magmaapp.home.presentation.home_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.home.domain.model.Notebook
import com.github.projektmagma.magmaapp.home.domain.model.toUiDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotebookSelector(
    notebook: Notebook,
    onClick: () -> Unit
) {
    val notebookTitle by notebook.title
    val notes = notebook.notes.sortedBy { it.createdAt }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(0.dp, 16.dp)
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.large,
            )
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.secondary)
            .clickable
            { onClick() },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = notebookTitle,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                // TODO: WPROWADZIĆ UDOSTĘPNIANIE I TU IKONKA UZERA CO UDOSTĘPNIA
//                Icon(
//                    modifier = HomeModifiers.iconSmallSize,
//                    imageVector = Icons.Filled.AccountCircle,
//                    contentDescription = null,
//                    tint = MaterialTheme.colorScheme.onSecondary
//                )
            }

            notes.forEach { note ->
                val noteTitle by note.title
                Text(
                    modifier = Modifier.padding(32.dp, 0.dp),
                    text = "- $noteTitle",
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            Text(
                modifier = Modifier.padding(32.dp, 0.dp),
                text = "${stringResource(id = R.string.notebook_creation_date)} " +
                        notebook.createdAt.longValue.toUiDate(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier.padding(32.dp, 0.dp),
                text = "${stringResource(id = R.string.notebook_last_modification_date)} " +
                        notebook.lastModified.longValue.toUiDate(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Start
            )
            
        }
    }
}