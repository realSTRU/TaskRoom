package com.example.taskroom.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "id")
    var id : Int =0,
    @Json(name = "username")
    var username : String = "",
    @Json(name = "password")
    var password : String = "",
    @Json(name = "email")
    var email : String = "",
    @Json(name = "name")
    var name : String = "",
    @Json(name = "surname")
    var surname : String = "",
    @Json(name = "projects")
    var projects : List<ProjectDto> = listOf(),
    @Json(name = "tasks")
    var tasks : List<TaskDto> = listOf()

) {
}


