package com.d121211005.rickandmortywiki.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Characters(
    @SerialName(value = "info")
    val info: Info?,
    @SerialName(value = "results")
    val results: List<Result>
)