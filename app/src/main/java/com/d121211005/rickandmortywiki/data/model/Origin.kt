package com.d121211005.rickandmortywiki.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    @SerialName(value = "name")
    val name: String?,
    @SerialName(value = "url")
    val url: String?
)