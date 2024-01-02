package com.example.realmlessons.data.mapper

import com.example.realmlessons.data.cashe.models.CameraCash
import com.example.realmlessons.data.cloud.models.camera.CameraCloud
import com.example.realmlessons.data.cloud.models.door.DoorCloud
import com.example.realmlessons.domain.models.CameraDomain
import com.example.realmlessons.domain.models.DoorDomain

fun CameraDomain.toDomain(): CameraCash = this.run {
    CameraCash(
        id = id,
        name = name,
        snapshot = snapshot,
        room = room ?: String(),
        favorites = favorites,
        rec = rec,
    )
}

fun CameraCloud.toDomain(): CameraDomain = this.run {
    CameraDomain(
        id = id,
        name = name,
        snapshot = snapshot,
        favorites = favorites,
        rec = rec,
        room = room,
    )
}

fun DoorCloud.toDomain(): DoorDomain = this.run {
    DoorDomain(
        id = id,
        name = name,
        snapshot = snapshot ?: String(),
        favorites = favorites,
        room = room ?: String(),
    )
}