package com.obi.cleanarchitecture.screen


import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AndroidViewModel
import com.obi.cleanarchitecture.R
import com.obi.cleanarchitecture.base.BaseFragment
import com.obi.cleanarchitecture.databinding.SavedVideoFragmentBinding
import com.obi.cleanarchitecture.screen.network.NetworkVideoViewModel


class SavedVideoFragment : BaseFragment<SavedVideoFragmentBinding, AndroidViewModel>()  {


    private val networkVideoViewModel: NetworkVideoViewModel by activityViewModels()

    override fun getLayoutId() = R.layout.saved_video_fragment

    override fun getViewModel(): NetworkVideoViewModel = networkVideoViewModel


    override fun initView() {

    }

}