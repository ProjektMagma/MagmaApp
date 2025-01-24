package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.projektmagma.magmaapp.R

@Composable
fun NewNotebookSelector(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = HomeModifiers.notebookButtonBox
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.large,
            )
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.secondary)
            .clickable { onClick() },
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = stringResource(id = R.string.new_notebook_create_button),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}
