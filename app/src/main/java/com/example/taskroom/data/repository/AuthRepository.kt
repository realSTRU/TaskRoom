package com.example.taskroom.data.repository

import com.example.taskroom.data.remote.api.authApi
import com.example.taskroom.data.remote.dto.CredentialDto
import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.data.remote.dto.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    val api : authApi
){
    suspend fun registrar(user : UserDto) : UserDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.register(user)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun login(credential : CredentialDto) : UserDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.login(credential)
                response.body()

            }
        }catch (e: Exception)
        {
            throw e
        }
    }
}