package com.example.realmlessons.presentation.models

import com.example.realmlessons.domain.models.CameraDomain
import javax.annotation.concurrent.Immutable

@Immutable
data class CameraMark(
    val camera: Camera,
    val isSaved: Boolean,
) {
    companion object {
        val unknown = CameraMark(
            camera = Camera.unknown,
            isSaved = false
        )
    }
}