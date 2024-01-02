package com.example.realmlessons.domain.models


data class DoorDomain(
    val id: Int,
    val name: String,
    val snapshot: String,
    val room: String,
    val favorites: Boolean
): java.io.Serializable

