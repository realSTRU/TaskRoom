package com.example.taskroom.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaskDto (
    @Json(name = "id")
    var id : Int = 0,
    @Json(name = "projectId")
    var projectId : Int = 0,
    @Json(name = "userId")
    var userId : Int = 0,
    @Json(name = "title")
    var title : String = "",
    @Json(name = "description")
    var description : String = "",
    @Json(name = "status")
    var status : Int = 0,
    @Json(name = "startDate")
    var startDate : String = "",
    @Json(name = "endDate")
    var endDate : String = "",
    @Json(name = "note")
    var note : String = ""
)