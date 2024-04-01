package com.example.taskroom.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoleDto(
    @Json(name = "id")
    var id : Int = 0,
    @Json(name = "description")
    var description : String = ""
)