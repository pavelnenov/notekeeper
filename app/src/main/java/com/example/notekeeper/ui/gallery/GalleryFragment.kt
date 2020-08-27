package com.example.notekeeper.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.notekeeper.R
import kotlinx.android.synthetic.main.nav_header_items.*

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        galleryViewModel =
//            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
//        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
    }
}