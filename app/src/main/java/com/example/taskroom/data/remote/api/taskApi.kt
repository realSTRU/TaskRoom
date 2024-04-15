package com.example.taskroom.data.remote.api

import com.example.taskroom.data.remote.dto.TaskDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface taskApi{
    @GET("api/Task")
    suspend fun getTask() : Response<List<TaskDto>>
    @POST
    suspend fun postTask(@Body task : TaskDto) : Response<TaskDto>
    @GET("api/Task/{taskId}")
    suspend fun getTaskById(@Path("taskId") taskId : Int) : Response<TaskDto>

    @DELETE("api/Task/{taskId}")
    suspend fun deleteTask(@Path("taskId") taskId: Int) : Response<TaskDto>
    @PUT("api/Task/update")
    suspend fun updateTask(@Body task : TaskDto) : Response<TaskDto>
}