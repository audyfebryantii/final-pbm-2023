package com.d121211005.rickandmortywiki.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName(value = "count")
    val count: Int,
    @SerialName(value = "next")
    val next: String,
    @SerialName(value = "pages")
    val pages: Int?,
    @SerialName(value = "prev")
    val prev: String?
)