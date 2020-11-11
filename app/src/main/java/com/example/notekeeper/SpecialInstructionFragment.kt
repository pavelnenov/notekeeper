package com.example.notekeeper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    SpecialInstructionFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class SpecialInstructionFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_special_instruction, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        special_instruction_text.text = "Lorem ipsum dolor sit amet"
//    }
}