package com.example.taskroom.data.remote.api

import com.example.taskroom.data.remote.dto.ProjectDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface projectApi {

    @GET("api/Project/{projectId}")
    suspend fun getProjectById(@Path("projectId") projectId : Int) : Response<ProjectDto>
    @DELETE("api/Project/{projectId}")
    suspend fun deleteProject(@Path("projectId") projectId: Int) : Response<ProjectDto>
    @GET("api/Project")
    suspend fun getProjects() : Response<List<ProjectDto>>
    @POST("api/Project")
    suspend fun postProject(@Body project : ProjectDto) : Response<ProjectDto>
    @POST("api/Project/{projectId}/addparticipant/{userId}/{roleId}")
    suspend fun addParticipant (@Path("projectId") projectId: Int, @Path("userId") userId : Int, @Path("roleId") roleId : Int) : Response<ProjectDto>
    @DELETE("api/Project/{projectId}/removeParticipant/{userId}")
    suspend fun removeParticipant(@Path("projectId") projectId: Int, @Path("userId") userId : Int)
}