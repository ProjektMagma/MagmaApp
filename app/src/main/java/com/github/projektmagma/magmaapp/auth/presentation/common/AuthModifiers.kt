package com.github.projektmagma.magmaapp.auth.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object AuthModifiers {
    val textFieldsModifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)

    val buttonsModifier: Modifier = Modifier
        .width(200.dp)
        .padding(4.dp)

    val topSpacerModifier: Modifier = Modifier
        .padding(32.dp)

    val textPaddingModifier: Modifier = Modifier
        .padding(4.dp)
}
