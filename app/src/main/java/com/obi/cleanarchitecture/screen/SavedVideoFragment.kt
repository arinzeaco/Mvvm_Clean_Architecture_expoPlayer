package com.obi.cleanarchitecture.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.obi.cleanarchitecture.R
import com.obi.cleanarchitecture.databinding.SavedVideoFragmentBinding


class SavedVideoFragment : Fragment() {
    private lateinit var fragmentSavedBinding: SavedVideoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.saved_video_fragment, container, false)
    }

//
}