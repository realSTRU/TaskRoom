package com.example.taskroom.data.repository

import com.example.taskroom.data.remote.api.participantApi
import com.example.taskroom.data.remote.dto.ParticipantDto
import com.example.taskroom.data.remote.dto.ProjectDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ParticipantRepository @Inject constructor(
    val api : participantApi
){
    suspend fun getParticipant() : List<ParticipantDto>?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.getParticipants()
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }

    suspend fun postParticipant(participant : ParticipantDto) : ParticipantDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.postParticipant(participant)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }

}