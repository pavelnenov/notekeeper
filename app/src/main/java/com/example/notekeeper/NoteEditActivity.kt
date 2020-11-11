package com.example.notekeeper

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.notekeeper.db.Repository
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_note_edit.*
import kotlinx.android.synthetic.main.fragment_note_edit.*
import kotlinx.android.synthetic.main.fragment_special_instruction.*
import javax.inject.Inject

@AndroidEntryPoint
class NoteEditActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)
        setSupportActionBar(toolbar)
                    val bottomSheetBehavior =  BottomSheetBehavior.from<ConstraintLayout>(special_instruction);
            bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
                override fun onStateChanged(bottomSheet: View, state: Int) {
                    print(state)
                    when (state) {

                        BottomSheetBehavior.STATE_HIDDEN -> {
                            Log.d("PAF", "onStateChanged: Show Bottom Sheet")
                        }
                        BottomSheetBehavior.STATE_EXPANDED ->
                            Log.d("PAF", "onStateChanged: Close Bottom Sheet")
                        BottomSheetBehavior.STATE_COLLAPSED ->
                            Log.d("PAF", "onStateChanged: Show Bottom Sheet")
                        BottomSheetBehavior.STATE_DRAGGING -> {
                        }
                        BottomSheetBehavior.STATE_SETTLING -> {
                        }
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {

                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                    TODO("Not yet implemented")
                }
            })
        bottomSheetBehavior.isHideable = true
        special_button.setOnClickListener {
            expandCollapseSheet(bottomSheetBehavior)
        }
    }
    private fun expandCollapseSheet(bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>) {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//            persistentBtn.text = "Close Bottom Sheet"
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
//            persistentBtn.text = "Show Bottom Sheet"
        }
    }
}