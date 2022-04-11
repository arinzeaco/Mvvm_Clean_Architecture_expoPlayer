package com.obi.cleanarchitecture.screen.savedVideo

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.obi.cleanarchitecture.VideoResponse
import com.obi.cleanarchitecture.base.BaseViewModel
import javax.inject.Inject

class SavedVideoViewModel @Inject constructor(app: Application,
) : BaseViewModel(app) {
    val videsData: MutableLiveData<VideoResponse> = MutableLiveData()



}