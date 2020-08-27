package com.example.notekeeper.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.notekeeper.db.entity.CourseWithNotes
//import com.example.notekeeper.db.entity.CourseWithNotes
import com.example.notekeeper.db.entity.NoteInfo

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(vararg notes: NoteInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: List<NoteInfo>)

    @Query("SELECT * FROM noteinfo")
    fun getAllNotes() : List<NoteInfo>

    @Query("SELECT * FROM noteinfo WHERE courseTitle = :courseTitle")
    fun getNoteByCourseTitle(courseTitle : String) : List<NoteInfo>

    @Query("SELECT * FROM noteinfo WHERE noteTitle = :noteTitle")
    fun getNoteByNoteTitle(noteTitle : String) : NoteInfo

    @Transaction
    @Query("SELECT * FROM courseinfo WHERE title = :courseTitle")
    fun getNotesForCourses(courseTitle: String) : List<CourseWithNotes>
}