package com.example.notekeeper.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notekeeper.db.entity.CourseInfo
import com.example.notekeeper.db.entity.NoteInfo

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourses(vararg  courses: CourseInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourses(courses: List<CourseInfo>)

    @Query("SELECT * FROM courseinfo")
    fun getAllCourses() : List<CourseInfo>

    @Query("SELECT * FROM courseinfo where title = :courseTitle")
    fun getCourseByCourseTitle(courseTitle : String) : List<CourseInfo>

    @Query("SELECT * FROM courseinfo where courseInfoId = :courseId")
    fun getNoteByCourseId(courseId : String) : List<CourseInfo>


}