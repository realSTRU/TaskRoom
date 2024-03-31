package com.example.taskroom.data.repository

import com.example.taskroom.data.remote.api.projectApi
import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.data.remote.dto.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProjectRepository @Inject constructor(
    val api : projectApi
) {

    suspend fun getProjectById(id : Int) : ProjectDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.getProjectById(id)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun deleteProject(id : Int) : ProjectDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.deleteProject(id)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun getProjects() : List<ProjectDto>?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.getProjects()
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun addParticipant(projectId : Int , userId : Int, roleId : Int) : ProjectDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.addParticipant(projectId,userId,roleId)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun removeParticipant(projectId : Int, userId: Int) : ProjectDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.removeParticipant(projectId, userId)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }


}