package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.notekeeper.data.DataManager
import com.example.notekeeper.db.entity.CourseInfo
import com.example.notekeeper.db.entity.NoteInfo
import kotlinx.android.synthetic.main.fragment_note_edit.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NoteEditFragment : Fragment() {

    var notePosition = POSITION_NOT_SET
    var addOrEditNote = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        //TODO
        if (notePosition >= 5) {
            val menuItem : MenuItem = menu.findItem(R.id.action_next)
            menuItem.icon = activity?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.ic_baseline_block_24) }
            menuItem.isEnabled = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                ++notePosition
                activity?.invalidateOptionsMenu()
                displayNote()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        addOrEditNote = savedInstanceState?.getString(EDIT_OR_ADD_NOTE, "Add/Edit Note") ?: activity?.intent?.getStringExtra(EDIT_OR_ADD_NOTE)
            .toString()

        addOrEditNoteTitle.text = addOrEditNote

        val adapterCourses = activity?.applicationContext?.let {
            ArrayAdapter<CourseInfo>(
                it,
                android.R.layout.simple_spinner_item,
                DataManager.courses.toList() //TODO
            )
        }
        adapterCourses?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCourses.adapter = adapterCourses

//        notePosition = activity?.intent?.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)!!
        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?:
                activity?.intent?.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)!!
        if (notePosition != POSITION_NOT_SET) {
            displayNote()
        }

        save_button.setOnClickListener {
            saveNote()
            val intent = Intent(activity?.application, ItemsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveNote() : NoteInfo {
        val currentCourseTitle = spinnerCourses.selectedItem.toString()
        val currentTitle = textNoteTitle.text.toString()
        val currentNote = textNoteText.text.toString()

        val noteInfo = NoteInfo(0, currentCourseTitle, currentTitle, currentNote)
        if (notePosition >= 0) {
            DataManager.notes[notePosition] = noteInfo
        } else {
            DataManager.notes.add(noteInfo)
        }
        return noteInfo
    }

    override fun onPause() {
        super.onPause()
//        saveNote()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)
        outState.putString(EDIT_OR_ADD_NOTE, addOrEditNote)
    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.noteTitle)
        textNoteText.setText(note.text)

        val coursePosition =
            DataManager.courses.withIndex().first {
                it.value.title == note.courseTitle
            }.index

        spinnerCourses.setSelection(coursePosition)
    }
}