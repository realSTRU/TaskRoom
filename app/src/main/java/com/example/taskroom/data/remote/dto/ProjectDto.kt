package com.example.taskroom.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectDto(
    @Json(name = "id")
    var id : Int =0,
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
    var note : String = "",
    @Json(name = "participants")
    var participants : MutableList<ParticipantDto> = mutableListOf(),
    @Json(name = "tasks")
    var tasks : MutableList<TaskDto> = mutableListOf()
)