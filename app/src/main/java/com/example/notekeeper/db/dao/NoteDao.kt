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
    suspend fun insertNotes(vararg notes: NoteInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: List<NoteInfo>)

    @Query("SELECT * FROM noteinfo")
    suspend fun getAllNotes() : List<NoteInfo>

    @Query("SELECT * FROM noteinfo WHERE courseTitle = :courseTitle")
    suspend fun getNoteByCourseTitle(courseTitle : String) : List<NoteInfo>

    @Query("SELECT * FROM noteinfo WHERE noteTitle = :noteTitle")
    suspend fun getNoteByNoteTitle(noteTitle : String) : NoteInfo

    @Transaction
    @Query("SELECT * FROM courseinfo WHERE title = :courseTitle")
    suspend fun getNotesForCourses(courseTitle: String) : List<CourseWithNotes>
}