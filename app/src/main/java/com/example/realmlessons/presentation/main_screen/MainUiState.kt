package com.example.realmlessons.presentation.main_screen

import androidx.compose.runtime.Stable
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.models.DoorDomain

sealed class MainUiState {

    data object Loading : MainUiState()

    data class Loaded(
        val isSaved: Boolean,
        val saveCamera: CameraDomain,
    ) : MainUiState()

    @Stable
    data class LoadedScreen(
        val camera: List<CameraDomain>,
        val door: List<DoorDomain>,
    ) : MainUiState()

    data class Error(
        val message: String
    ) : MainUiState()
}