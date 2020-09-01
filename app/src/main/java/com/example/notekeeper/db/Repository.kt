package com.example.notekeeper.db

import android.util.Log
import com.example.notekeeper.db.dao.CourseDao
import com.example.notekeeper.db.dao.NoteDao
import com.example.notekeeper.db.entity.CourseInfo
import com.example.notekeeper.db.entity.NoteInfo
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class Repository @Inject constructor(val courseDao: CourseDao, val notesDao: NoteDao ) {

    fun getCourses(): List<CourseInfo> = runBlocking {

        val TAG = this.javaClass.name
        Log.d(TAG, "getting courses from db")
        //delay(1000)
        return@runBlocking courseDao.getAllCourses()
    }

    fun getNotes(): List<NoteInfo> = runBlocking {

        val TAG = this.javaClass.name
        Log.d(TAG, "getting courses from db")
        //delay(1000)
        return@runBlocking notesDao.getAllNotes()
    }
}