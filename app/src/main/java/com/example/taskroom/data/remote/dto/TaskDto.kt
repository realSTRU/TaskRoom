package com.example.taskroom.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaskDto (
    @Json(name = "id")
    var id : Int,
    @Json(name = "projectId")
    var projectId : Int,
    @Json(name = "userId")
    var userId : Int,
    @Json(name = "title")
    var title : String,
    @Json(name = "description")
    var description : String,
    @Json(name = "status")
    var status : String,
    @Json(name = "startDate")
    var startDate : String,
    @Json(name = "endDate")
    var endDate : String,
    @Json(name = "note")
    var note : String
)