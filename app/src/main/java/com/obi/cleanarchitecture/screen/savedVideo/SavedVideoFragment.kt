package com.obi.cleanarchitecture.screen.savedVideo


import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AndroidViewModel
import com.obi.cleanarchitecture.R
import com.obi.cleanarchitecture.base.BaseFragment
import com.obi.cleanarchitecture.databinding.SavedVideoFragmentBinding


class SavedVideoFragment : BaseFragment<SavedVideoFragmentBinding, AndroidViewModel>()  {


    private val savedVideoViewModel: SavedVideoViewModel by activityViewModels()

    override fun getLayoutId() = R.layout.saved_video_fragment

    override fun getViewModel(): SavedVideoViewModel = savedVideoViewModel


    override fun initView() {

    }

}