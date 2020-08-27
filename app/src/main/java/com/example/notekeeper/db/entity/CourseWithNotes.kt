package com.example.notekeeper.db.entity

import androidx.room.Embedded
import androidx.room.Relation

class CourseWithNotes() {
    @Embedded
    lateinit var courseInfo: CourseInfo

    @Relation(
        parentColumn = "courseInfoId",
        entityColumn = "noteInfoId"
    )
    lateinit var notes: List<NoteInfo>
}
