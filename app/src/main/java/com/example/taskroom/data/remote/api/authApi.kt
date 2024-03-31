package com.example.taskroom.data.remote.api

import com.example.taskroom.data.remote.dto.CredentialDto
import com.example.taskroom.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface authApi {
    @POST("api/Auth/register")
    suspend fun register(@Body user : UserDto) : Response<UserDto>
    @POST("api/Auth/login")
    suspend fun login(@Body credential: CredentialDto) : Response<UserDto>
}