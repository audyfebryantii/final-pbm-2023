package com.d121211005.rickandmortywiki.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName(value = "created")
    val created: String?,
    @SerialName(value = "episode")
    val episode: List<String>?,
    @SerialName(value = "gender")
    val gender: String?,
    @SerialName(value = "id")
    val id: Int?,
    @SerialName(value = "image")
    val image: String?,
    @SerialName(value = "location")
    val location: Location?,
    @SerialName(value = "name")
    val name: String?,
    @SerialName(value = "origin")
    val origin: Origin?,
    @SerialName(value = "species")
    val species: String?,
    @SerialName(value = "status")
    val status: String?,
    @SerialName(value = "type")
    val type: String?,
    @SerialName(value = "url")
    val url: String?
)