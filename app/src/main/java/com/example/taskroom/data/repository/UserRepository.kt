package com.example.taskroom.data.repository

import com.example.taskroom.data.remote.api.userApi
import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.data.remote.dto.TaskDto
import com.example.taskroom.data.remote.dto.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(
    val api : userApi
) {
    suspend fun getUserById(id : Int) : UserDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.getUserById(id)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun getUsers() : List<UserDto>?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.getUsers()
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun updateUser(id : Int, user : UserDto) : UserDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.updateUser(id, user)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun deleteUser(id : Int) : UserDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.deleteUser(id)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }

    suspend fun getProjectByUser(id : Int) : MutableList<ProjectDto>?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.getProjectsByUser(id)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun getTaskByUser(id : Int) : MutableList<TaskDto>?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.getTasksByUser(id)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }

    suspend fun addProjetToAUser(userId : Int, projectId : Int) : ProjectDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.addProjectToAUser(userId, projectId)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun removeProjectToAUser(userId : Int, projectId: Int) :ProjectDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.removeProjectToAUser(userId, projectId)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun addTaskToAUser(userId : Int, taskId : Int) : TaskDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.addTaskToAUser(userId,  taskId)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun removeTaskToAUser(userId : Int, taskId : Int) : TaskDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.removeTaskToAUser(userId,taskId)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }

}