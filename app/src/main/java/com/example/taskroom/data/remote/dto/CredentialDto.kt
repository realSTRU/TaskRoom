package com.example.taskroom.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CredentialDto (
    @Json(name="username")
    var username : String = "",
    @Json(name = "password")
    var password : String = ""
)