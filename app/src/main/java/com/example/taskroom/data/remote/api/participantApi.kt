package com.example.taskroom.data.remote.api

import com.example.taskroom.data.remote.dto.ParticipantDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface participantApi {
    @GET("api/Participant")
    suspend fun getParticipants() : Response<List<ParticipantDto>>
    @POST("api/Participant")
    suspend fun postParticipant(@Body participantDto: ParticipantDto) : Response<ParticipantDto>
}