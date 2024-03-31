package com.example.taskroom.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "id")
    var id : Int,
    @Json(name = "username")
    var username : String,
    @Json(name = "password")
    var password : String,
    @Json(name = "email")
    var name : String,
    @Json(name = "surname")
    var surname : String,
    @Json(name = "projects")
    var projects : List<ProjectDto>,
    @Json(name = "tasks")
    var tasks : List<TaskDto>

)