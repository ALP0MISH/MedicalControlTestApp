package com.example.realmlessons.presentation.models

import com.example.realmlessons.domain.models.CameraDomain
import javax.annotation.concurrent.Immutable

@Immutable
data class Camera(
    val id: Int,
    val name: String,
    val snapshot: String,
    val room: String,
    val favorites: Boolean,
    val rec: Boolean,
) {
    companion object {
        val unknown = Camera(
            id = 0,
            name = String(),
            snapshot = String(),
            room = String(),
            favorites = false,
            rec = false
        )
    }
}

fun Camera.toDomain(): CameraDomain = CameraDomain(
    id = id,
    name = name,
    snapshot = snapshot,
    favorites = favorites,
    rec = rec,
    room = room,
)
