package com.example.notekeeper.db.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class NoteInfo(
    @PrimaryKey(autoGenerate = true) val noteInfoId : Int,
    val courseTitle: String,
    val noteTitle: String,
    val text: String)