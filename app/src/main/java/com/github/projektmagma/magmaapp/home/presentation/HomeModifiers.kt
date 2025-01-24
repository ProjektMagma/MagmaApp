package com.github.projektmagma.magmaapp.home.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object HomeModifiers {
    val iconBigSize: Modifier = Modifier.size(128.dp)
    val iconSmallSize: Modifier = Modifier.size(32.dp)
    val notebookButtonBox: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(0.dp, 16.dp)
    val notebookSelectorTextPadding = Modifier.padding(32.dp, 0.dp)
    val notebookEditScreenButtonWidth = Modifier.width(128.dp)
}