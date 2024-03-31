package com.example.taskroom.data.repository

import com.example.taskroom.data.remote.api.taskApi
import com.example.taskroom.data.remote.dto.TaskDto
import com.example.taskroom.data.remote.dto.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(
    val api : taskApi
) {
    suspend fun getTasks() : List<TaskDto>?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.getTask()
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }

    suspend fun postTask(task : TaskDto) : TaskDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.postTask(task)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }

    suspend fun getTaskById(id : Int) : TaskDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.getTaskById(id)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun deleteTask(id : Int) : TaskDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.deleteTask(id)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
    suspend fun updateTask(task : TaskDto) : TaskDto?{
        try{
            return withContext(Dispatchers.IO)
            {
                val response = api.updateTask(task)
                response.body()
            }
        }catch (e: Exception)
        {
            throw e
        }
    }
}