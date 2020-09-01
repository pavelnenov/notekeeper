package com.example.notekeeper.data.api

import com.example.notekeeper.data.dto.CourseInfoDto
import com.example.notekeeper.db.entity.CourseInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface CoursesApi {

    @Headers(
        "Accept: application/json"
    )
    @GET(PATH)
    suspend fun getCourses(
    ): Call<List<CourseInfoDto>>

    companion object {
        const val PATH = "/courses"
    }
}
