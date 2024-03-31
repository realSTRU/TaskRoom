package com.example.taskroom.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ParticipantDto (
    @Json(name = "id")
    var id : Int,
    @Json(name = "projectId")
    var projectId : Int,
    @Json(name = "userId")
    var userId : Int,
    @Json(name ="roleId")
    var roleId : Int
)