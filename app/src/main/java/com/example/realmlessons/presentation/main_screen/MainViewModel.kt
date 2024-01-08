package com.example.realmlessons.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realmlessons.data.common.model.Status
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.usecase.CameraSaveOrDeleteUseCase
import com.example.realmlessons.domain.usecase.FetchAllCloud
import com.example.realmlessons.presentation.manager.CameraMarkableManager
import com.example.realmlessons.presentation.models.CameraMark
import com.example.realmlessons.presentation.models.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchAllCloud: FetchAllCloud,
    private val isCameraSavedUseCase: CameraSaveOrDeleteUseCase,
    private val cameraMarkableManager: CameraMarkableManager,
) : ViewModel(
) {
    private val _uiStateFlow = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiStateFlow: StateFlow<MainUiState> = _uiStateFlow.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _uiStateFlow.tryEmit(MainUiState.Error(throwable.localizedMessage ?: ""))
    }

    init {
        viewModelScope.launch {
            fetchAndUpdateUIDoors()

            cameraMarkableManager.observeCameraMarks(fetchCameras())
                .onEach(::handleReceivedCameraMarks)
                .launchIn(viewModelScope)
        }
    }

    private suspend fun fetchCameras(): List<CameraDomain> {
        val fetchCloudCameraResponse = coroutineScope {
            async { fetchAllCloud.fetchCloudCamera() }
        }
        val cameraResponse = fetchCloudCameraResponse.await()
        return cameraResponse.data ?: emptyList()
    }

    private suspend fun fetchAndUpdateUIDoors() {
        val fetchCloudDoorResponse = coroutineScope {
            async { fetchAllCloud.fetchCloudDoor() }
        }
        val doorResponse = fetchCloudDoorResponse.await()
        val state = if (doorResponse.status == Status.SUCCESS) MainUiState.LoadedScreen(
            door = doorResponse.data ?: emptyList()
        )
        else MainUiState.Error(doorResponse.errorThrowable?.localizedMessage ?: "")
        _uiStateFlow.tryEmit(state)
    }

    private fun handleReceivedCameraMarks(cameraMarks: List<CameraMark>) = with(_uiStateFlow) {
        val currentState = value as? MainUiState.LoadedScreen ?: MainUiState.LoadedScreen()
        tryEmit(currentState.copy(cameraMarks = cameraMarks))
    }

    fun addOrDeleteCamera(cameraMark: CameraMark) {
        viewModelScope.launch(handler + Dispatchers.IO) {
            if (!cameraMark.isSaved) {
                isCameraSavedUseCase.saveCamera(cameraMark.camera.toDomain())
                    handleCameraSavedStateChange(cameraMark.camera.id, isSaved = true)
            } else {
                isCameraSavedUseCase.deleteCameraById(cameraMark.camera.id)
                    handleCameraSavedStateChange(cameraMark.camera.id, isSaved = false)
            }
        }
    }

    private fun handleCameraSavedStateChange(cameraId: Int, isSaved: Boolean) {
        val currentState = _uiStateFlow.value as? MainUiState.LoadedScreen ?: MainUiState.LoadedScreen()
        val updatedCameraMarks = currentState.cameraMarks.map { cameraMark ->
            if (cameraMark.camera.id == cameraId) {
                cameraMark.copy(isSaved = isSaved)
            } else {
                cameraMark
            }
        }
        _uiStateFlow.tryEmit(currentState.copy(cameraMarks = updatedCameraMarks))
    }
}