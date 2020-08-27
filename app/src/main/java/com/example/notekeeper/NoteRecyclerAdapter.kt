package com.example.notekeeper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeeper.db.entity.NoteInfo
import kotlinx.android.synthetic.main.item_note_list.view.*

class NoteRecyclerAdapter(private val context: Context, private val notes: List<NoteInfo>) :
    RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>(){

    private val layoutInflater = LayoutInflater.from(context);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_note_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.textCourse.text = note.courseTitle
        holder.textTitle.text = note.text
        holder.notePosition = position

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCourse = itemView.textCourse
        val textTitle = itemView.textTitle
        var notePosition = 0

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, NoteEditActivity::class.java)
                intent.putExtra(NOTE_POSITION, notePosition)
                intent.putExtra(EDIT_OR_ADD_NOTE, "Edit Note")
                context.startActivity(intent)
            }
        }
    }
}