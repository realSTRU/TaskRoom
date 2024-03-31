package com.example.taskroom.data.remote.api

import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.data.remote.dto.TaskDto
import com.example.taskroom.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path

interface userApi {
    @GET("api/User/{userId}")
    suspend fun getUserById(@Path("userId")userId : Int) : Response<UserDto>
    @PUT("api/User/{userId}")
    suspend fun updateUser(@Path("userId")userId: Int, @Body user : UserDto) : Response<UserDto>
    @DELETE("api/User/{userId}")
    suspend fun deleteUser(@Path("userId")user: Int) : Response<UserDto>
    @GET("api/User")
    suspend fun getUsers() : Response<List<UserDto>>
    @GET("api/User/{userId}/projects")
    suspend fun getProjectsByUser(@Path("userId")userId: Int) : Response<List<ProjectDto>>
    @GET("api/User/{userId}/tasks")
    suspend fun getTasksByUser(@Path("userId")userId: Int) : Response<List<TaskDto>>
    @PATCH("api/User/{userId}/addproject/{projectId}")
    suspend fun addProjectToAUser(@Path("userId")userId: Int, @Path("projectId")projectId : Int) : Response<ProjectDto>
    @PATCH("api/User/{userId}/removeproject/{projectId}")
    suspend fun removeProjectToAUser(@Path("userId")userId: Int, @Path("projectId")projectId : Int) : Response<ProjectDto>
    @PATCH("api/User/{userId}/addtask/{taskId}")
    suspend fun addTaskToAUser(@Path("userId")userId: Int, @Path("taskId")taskId:Int) : Response<TaskDto>
    @PATCH("api/User/{userId}/removetask/{taskId}")
    suspend fun removeTaskToAUser(@Path("userId")userId: Int,@Path("taskId") taskId: Int) : Response<TaskDto>


}