package com.example.notekeeper.db

import android.content.Context
import android.os.Debug
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notekeeper.db.dao.CourseDao
import com.example.notekeeper.db.dao.NoteDao
import com.example.notekeeper.db.entity.CourseInfo
import com.example.notekeeper.db.entity.NoteInfo

@Database(entities = arrayOf(CourseInfo::class, NoteInfo::class), version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun courseDao() : CourseDao

    abstract fun noteDao() : NoteDao

    companion object{

        @Volatile private var instance: NotesDatabase? = null
        private val LOCK = Any()

        val DATABASE_NAME = "notes_db"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, DATABASE_NAME).apply {
                if (Debug.isDebuggerConnected()) {
                    allowMainThreadQueries()
                }
            }.build()
    }

}