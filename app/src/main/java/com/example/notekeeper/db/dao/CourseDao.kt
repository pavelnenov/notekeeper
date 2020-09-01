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
    suspend fun insertCourses(vararg  courses: CourseInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseInfo>)

    @Query("SELECT * FROM courseinfo")
    suspend fun getAllCourses() : List<CourseInfo>

    @Query("SELECT * FROM courseinfo where title = :courseTitle")
    suspend fun getCourseByCourseTitle(courseTitle : String) : List<CourseInfo>

    @Query("SELECT * FROM courseinfo where courseInfoId = :courseId")
    suspend fun getNoteByCourseId(courseId : String) : List<CourseInfo>


}