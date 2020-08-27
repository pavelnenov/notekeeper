package com.example.notekeeper.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CourseInfo(
    @PrimaryKey(autoGenerate = true) val courseInfoId: Int,
    val courseId: String,
    val title: String) {
    override fun toString(): String {
        return title
    }
}