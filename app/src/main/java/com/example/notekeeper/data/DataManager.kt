package com.example.notekeeper.data

import com.example.notekeeper.db.NotesDatabase
import com.example.notekeeper.db.entity.CourseInfo
import com.example.notekeeper.db.entity.NoteInfo

class DataManager(val db: NotesDatabase) {

    companion object {
        private val LOCK = Any()
        var courses = mutableListOf<CourseInfo>()
        var notes = mutableListOf<NoteInfo>()
    }


    init {
        initialiseCourses()
    }

    fun execute(runnable : Runnable){
        synchronized(LOCK) {
            Thread(runnable).start()
        }
    }

    private fun initialiseCourses() {
        courses.run {
            add(CourseInfo(1, "android_intent", "Android programming with intents"))
            add(CourseInfo(2, "android_async", "Async Android programming and services"))
            add(CourseInfo(3, "java_lang", "Java fundamentals"))
            add(CourseInfo(4, "java_core1", "Java core libraries"))
            add(CourseInfo(5, "java_core2", "Java core libraries"))
            add(CourseInfo(6, "java_core3", "Threads"))
            add(CourseInfo(7, "java_core4", "Executors"))
            add(CourseInfo(8, "java_core5", "NIO"))

        }

        notes.run {
            add(NoteInfo(1, courses[0].title, "Wow", "Android uses intents to create activities"))
            add(NoteInfo(2, courses[1].title, "Async", "I should really learn this"))
            add(NoteInfo(3, courses[2].title, "JVM", "Java is a JVM language"))
            add(NoteInfo(4, courses[3].title, "Java Lang", "Learn more core libraries"))
            add(NoteInfo(5, courses[4].title, "Java Lang", "Learn more core libraries"))
            add(NoteInfo(6, courses[5].title, "Java Lang", "Synchronize threads"))
            add(NoteInfo(7, courses[6].title, "Java Lang", "Executors make your life easier"))
            add(NoteInfo(8, courses[7].title, "Java Lang", "NIO is next gen IO"))
        }

        val t = Thread {
            db.courseDao().insertCourses(courses)
            db.noteDao().insertNotes(notes)
        }.apply {
            isDaemon = true
            name = "Paf Thread"
        }
        synchronized(LOCK) {
            t.start()
        }
        print("")
    }
}