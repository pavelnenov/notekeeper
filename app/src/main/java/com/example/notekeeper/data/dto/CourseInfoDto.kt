package com.example.notekeeper.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CourseInfoDto(
    @JsonProperty("courseInfoId")
    val courseInfoId: Int,
    @JsonProperty("courseId")
    val courseId: String,
    @JsonProperty("title")
    val title: String
)
