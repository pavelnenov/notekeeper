package com.example.notekeeper.data.dto

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

data class NoteInfoDto (
    @JsonProperty("noteInfoId")
    val noteInfoId : Int,
    @JsonProperty("courseTitle")
    val courseTitle: String,
    @JsonProperty("noteTitle")
    val noteTitle: String,
    @JsonProperty("text")
    val text: String
)