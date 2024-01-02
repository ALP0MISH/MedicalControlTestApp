package com.example.realmlessons.domain.models

data class CameraDomain(
    val id: Int,
    val name: String,
    val snapshot: String,
    val room: String?,
    val favorites: Boolean,
    val rec: Boolean,
) {
    companion object {
        val unknown = CameraDomain(
            id = 0,
            name = String(),
            snapshot = String(),
            room = String(),
            favorites = false,
            rec = false
        )
    }
}