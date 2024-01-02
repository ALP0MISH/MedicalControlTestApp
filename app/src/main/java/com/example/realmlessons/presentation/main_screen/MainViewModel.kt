package com.example.realmlessons.presentation.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realmlessons.data.common.model.Status
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.usecase.FetchAllCloud
import com.example.realmlessons.domain.usecase.FetchSavedCameraUseCase
import com.example.realmlessons.domain.usecase.IsCameraSaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

const val DEFAULT_ERROR_MESSAGE = "default_error_message"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchAllCloud: FetchAllCloud,
    private val isCameraSavedUseCase: IsCameraSaveUseCase,
    private val fetchSavedCameraUseCase: FetchSavedCameraUseCase
) : ViewModel(
) {
    private val _uiStateFlow = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiStateFlow: StateFlow<MainUiState> = _uiStateFlow.asStateFlow()


    private val handler = CoroutineExceptionHandler { _, throwable ->
        _uiStateFlow.tryEmit(MainUiState.Error(throwable.localizedMessage ?: ""))
    }

    init {
        fetchCameraAndDoor()
    }

    private fun fetchCameraAndDoor() {
        viewModelScope.launch(handler + Dispatchers.IO) {
            val fetchCloudCameraResponse = async {
                fetchAllCloud.fetchCloudCamera()
            }
            val fetchCloudDoorResponse = async {
                fetchAllCloud.fetchCloudDoor()
            }
            val awaitedCameraResponse = fetchCloudCameraResponse.await()
            val awaitedDoorResponse = fetchCloudDoorResponse.await()
            if (awaitedCameraResponse.status == Status.SUCCESS && awaitedDoorResponse.status == Status.SUCCESS) {
                _uiStateFlow.tryEmit(
                    MainUiState.LoadedScreen(
                        camera = awaitedCameraResponse.data ?: emptyList(),
                        door = awaitedDoorResponse.data ?: emptyList(),
                    )
                )
            } else {
                _uiStateFlow.tryEmit(
                    MainUiState.Error(awaitedCameraResponse.errorThrowable?.localizedMessage ?: "")
                )
            }
        }
    }

    fun addOrDeleteCamera(cameraDomain: CameraDomain) {
        viewModelScope.launch(handler + Dispatchers.IO) {
            if (checkIsCameraSaved(cameraDomain.id)) isCameraSavedUseCase.saveCamera(cameraDomain)
            else isCameraSavedUseCase.deleteCameraById(cameraDomain.id)
        }
    }

    private suspend fun checkIsCameraSaved(id: Int): Boolean {
        var isCameraSaved: Boolean = false
        fetchSavedCameraUseCase.invoke(id).onEach { isSaved: Boolean ->
            isCameraSaved = isSaved
        }.launchIn(viewModelScope)
        return isCameraSaved
    }
}