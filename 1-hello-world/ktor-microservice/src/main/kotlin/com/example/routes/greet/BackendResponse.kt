package com.example.routes.greet

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BackendResponse(
    @SerialName(value = "greeting") val greeting: String,
    @SerialName(value = "time") val time: String,
    @SerialName(value = "ip") val ip: String
)
