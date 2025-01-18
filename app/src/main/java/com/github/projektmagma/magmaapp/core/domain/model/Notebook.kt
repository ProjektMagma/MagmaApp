package com.github.projektmagma.magmaapp.core.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Notebook(
    val id: Int,
    val title: MutableState<String>,
    val notes: SnapshotStateList<Note>
)