package com.github.projektmagma.magmaapp.home.presentation.edit_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.Dialog

@Composable
fun ColorPicker(
    icon: ImageVector,
    color: MutableState<Color>,
    showColorPicker: MutableState<Boolean>,
) {
    IconButton(
        onClick = {
            showColorPicker.value = true
        }
    ) {
        Icon(
            icon,
            contentDescription = "Color",
            tint = if (color.value == Color.Unspecified) Color.White else color.value
        )
    }
    AnimatedVisibility(visible = showColorPicker.value) {
        Dialog(onDismissRequest = { showColorPicker.value = false }) {
            Column(
                modifier = Modifier
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(color = MaterialTheme.colorScheme.surface)
            ) {
                Row {
                    IconButton(onClick = {
                        color.value = Color.Unspecified
                        showColorPicker.value = false
                    }) {
                        Icon(
                            Icons.Filled.ChangeCircle,
                            contentDescription = "Color",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = {
                            color.value = Color.Red
                            showColorPicker.value = false
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
                            color.value = Color.Green
                            showColorPicker.value = false
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
                            color.value = Color.Blue
                            showColorPicker.value = false
                        }
                    ) {
                        Icon(
                            Icons.Filled.Circle,
                            contentDescription = "Color",
                            tint = Color.Blue
                        )
                    }
                }
                Row {
                    IconButton(
                        onClick = {
                            color.value = Color.Yellow
                            showColorPicker.value = false
                        }
                    ) {
                        Icon(
                            Icons.Filled.Circle,
                            contentDescription = "Color",
                            tint = Color.Yellow
                        )
                    }
                    IconButton(
                        onClick = {
                            color.value = Color.Magenta
                            showColorPicker.value = false
                        }
                    ) {
                        Icon(
                            Icons.Filled.Circle,
                            contentDescription = "Color",
                            tint = Color.Magenta
                        )
                    }
                    IconButton(
                        onClick = {
                            color.value = Color.Cyan
                            showColorPicker.value = false
                        }
                    ) {
                        Icon(
                            Icons.Filled.Circle,
                            contentDescription = "Color",
                            tint = Color.Cyan
                        )
                    }
                    IconButton(
                        onClick = {
                            color.value = Color.Gray
                            showColorPicker.value = false
                        }
                    ) {
                        Icon(
                            Icons.Filled.Circle,
                            contentDescription = "Color",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}