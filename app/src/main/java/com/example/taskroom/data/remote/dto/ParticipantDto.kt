package com.example.taskroom.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ParticipantDto (
    @Json(name = "id")
    var id : Int = 0,
    @Json(name = "projectId")
    var projectId : Int = 0,
    @Json(name = "userId")
    var userId : Int = 0,
    @Json(name ="roleId")
    var roleId : Int = 0
)