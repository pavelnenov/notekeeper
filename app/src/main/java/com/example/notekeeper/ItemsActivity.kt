package com.example.notekeeper

import android.content.ContentProvider
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notekeeper.data.DataManager
import com.example.notekeeper.db.NotesDatabase
import com.example.notekeeper.db.dao.CourseDao
import com.example.notekeeper.db.dao.NoteDao
import com.example.notekeeper.db.entity.CourseInfo
import com.example.notekeeper.db.entity.NoteInfo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.app_bar_items.*
import kotlinx.android.synthetic.main.content_items.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ItemsActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val noteLayoutManager by lazy { LinearLayoutManager(this) }

    private val noteRecyclerAdapter by lazy { NoteRecyclerAdapter(this, DataManager.notes) }

    private val courseLayoutManager by lazy { GridLayoutManager(this, 2) }

    private val courseRecyclerAdapter by lazy { CourseRecyclerAdapter(this, DataManager.courses) }

    private lateinit var noteDao : NoteDao
    private lateinit var courseDao: CourseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = NotesDatabase(this)
        val dbManager = DataManager(db)
        noteDao = db.noteDao()
        courseDao = db.courseDao()

        var courses: List<CourseInfo>
        var notes: List<NoteInfo>
        val executorService: ExecutorService = Executors.newFixedThreadPool(4)
        val dbPath = getDatabasePath("notes_db").absolutePath

        Thread{ courses = db.courseDao().getAllCourses() }.start()
        Thread{ notes = db.noteDao().getAllNotes() }.start()

        setContentView(R.layout.activity_items)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            intent = Intent(this, NoteEditActivity::class.java)
            intent.putExtra(EDIT_OR_ADD_NOTE, "Add Note")
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_notes, R.id.nav_courses
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id) {
                R.id.nav_notes -> displayNotes()
                R.id.nav_courses -> displayCourses()
            }
        }

//        displayNotes()

    }

    private fun displayCourses() {
        listItems.layoutManager = courseLayoutManager
        listItems.adapter = courseRecyclerAdapter
        nav_view.menu.findItem(R.id.nav_notes).isEnabled = true
    }

    private fun displayNotes() {
        listItems.layoutManager = noteLayoutManager
        listItems.adapter = noteRecyclerAdapter
        nav_view.menu.findItem(R.id.nav_notes).isEnabled = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        listItems.adapter?.notifyDataSetChanged()

        val client = OkHttpClient()
        val request = Request.Builder().url("http://10.0.2.2:8080").build()
//        val request = Request.Builder().url("https://reqres.in/api/users?page=2").build()

        val queue = ArrayBlockingQueue<String>(1)
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error")
                queue.add(e.stackTrace.contentToString())
                Snackbar.make(listItems, e.stackTrace.contentToString(), Snackbar.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    queue.add(body)
                    runOnUiThread {Snackbar.make(listItems, body ?: "", Snackbar.LENGTH_LONG).show()}

                } else {
                    queue.add("not successful")
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun handleSelection(message: String) {
        Snackbar.make(listItems, message, Snackbar.LENGTH_LONG).show()
    }
}