package com.github.projektmagma.magmaapp.core.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    titleMedium = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default,
    ),
    titleLarge = TextStyle(
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default,
        letterSpacing = 3.sp,
    )
)
