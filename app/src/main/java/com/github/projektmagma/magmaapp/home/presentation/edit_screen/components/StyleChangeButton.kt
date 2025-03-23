package com.github.projektmagma.magmaapp.home.presentation.edit_screen.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun StyleChangeButton(
    icon: ImageVector,
    description: String?,
    onClick: () -> Unit
) {
    IconButton(
        onClick = {
            onClick()
        }
    ) {
        Icon(icon, contentDescription = description)
    }
}