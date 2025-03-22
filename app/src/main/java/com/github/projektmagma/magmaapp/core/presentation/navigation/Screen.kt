package com.github.projektmagma.magmaapp.core.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object AuthGraph : Screen()

    @Serializable
    data object MainGraph : Screen()


    @Serializable
    data object OnBoardingScreen : Screen()

    @Serializable
    data object LoginScreen : Screen()

    @Serializable
    data object RegisterScreen : Screen()

    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data object SettingsScreen : Screen()

    @Serializable
    data class NoteSelectorScreen(
        val id: String
    ) : Screen()
    
    @Serializable
    data class NoteEditorScreen(
        val id: String
    ) : Screen()
}