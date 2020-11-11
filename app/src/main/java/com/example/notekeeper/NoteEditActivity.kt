package com.example.notekeeper

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.notekeeper.db.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_note_edit.*
import kotlinx.android.synthetic.main.content_note_edit.*
import kotlinx.android.synthetic.main.content_note_list.*
import kotlinx.android.synthetic.main.fragment_note_edit.*
import javax.inject.Inject

@AndroidEntryPoint
class NoteEditActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)
        setSupportActionBar(toolbar)
    }
}