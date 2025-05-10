package com.github.projektmagma.magmaapp.home.presentation.shared.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.projektmagma.magmaapp.R

@Composable
fun PopupWindow(
    showPopup: MutableState<Boolean>,
    onRename: () -> Unit,
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(visible = showPopup.value) {
        Dialog(onDismissRequest = { showPopup.value = !showPopup.value }) {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                content()

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(MaterialTheme.shapes.small)
                            .clickable {
                                onRename()
                            }.background(MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Text(stringResource(R.string.rename_label))
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Rename"
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(MaterialTheme.shapes.small)
                            .clickable {
                                onDelete()
                            }.background(MaterialTheme.colorScheme.errorContainer)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Text(stringResource(R.string.delete_label))
                            Icon(
                                imageVector = Icons.Filled.DeleteOutline,
                                contentDescription = "Delete"
                            )
                        }
                    }
                }
            }
        }
    }
}