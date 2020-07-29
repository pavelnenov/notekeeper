package com.example.notekeeper.data

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initialiseCourses()
    }

    private fun initialiseCourses() {
        var course = CourseInfo("android_intent", "Android programming with intents")
        courses[course.courseId] = course

        notes.add(NoteInfo(course.title, "Wow", "Android uses intents to create activities"))

        course = CourseInfo("android_async", "Async Android programming and services")
        courses[course.courseId] = course

        notes.add(NoteInfo(course.title, "Async", "I should really learn this"))

        course = CourseInfo("java_lang", "Java fundamentals")
        courses[course.courseId] = course

        notes.add(NoteInfo(course.title, "JVM", "Java is a JVM language"))

        course = CourseInfo("java_core", "Java core libraries")
        courses[course.courseId] = course

        notes.add(NoteInfo(course.title, "Java Lang", "Learn more core libraries"))

    }
}