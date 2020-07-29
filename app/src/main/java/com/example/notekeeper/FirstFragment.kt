package com.example.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.notekeeper.data.CourseInfo
import com.example.notekeeper.data.DataManager
import kotlinx.android.synthetic.main.fragment_first.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var notePositions = POSITION_NOT_SET

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val adapterCourses = activity?.applicationContext?.let {
            ArrayAdapter<CourseInfo>(
                it,
                android.R.layout.simple_spinner_item,
                DataManager.courses.values.toList()
            )
        }
        adapterCourses?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCourses.adapter = adapterCourses

        notePositions = activity?.intent?.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)!!
        if (notePositions != POSITION_NOT_SET) {
            displayNote()
        }

    }

    private fun displayNote() {
        val note = DataManager.notes[notePositions]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)

       var coursePosition : Int = 0
       for ( (index, course) in DataManager.courses.values.withIndex()) {
           if (course.title == note.course) {
               coursePosition = index
               break
           }
       }

        spinnerCourses.setSelection(coursePosition)
    }
}