package com.example.notekeeper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeeper.db.Repository
import com.example.notekeeper.db.entity.CourseInfo
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.item_course_list.view.*
import javax.inject.Inject

class CourseRecyclerAdapter @Inject constructor(@ApplicationContext context: Context, repository: Repository) :
    RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder>() {

    private val courses = repository.getCourses()

    private val layoutInflater = LayoutInflater.from(context);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_course_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courses[position]
        holder.textCourse.text = course.title
        holder.coursePosition = position
    }

    override fun getItemCount() = courses.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCourse = itemView.textCourseTitle
        var coursePosition = 0

        init {
            itemView.setOnClickListener {
                Snackbar.make(it, courses[coursePosition].title, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}



