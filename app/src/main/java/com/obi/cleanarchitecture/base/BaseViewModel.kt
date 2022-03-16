package com.obi.cleanarchitecture.base

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    protected val context
        get() = getApplication<Application>()
//
    fun showProgressBarTrue(): Int {
            return  View.VISIBLE
    }
    fun showProgressBarFalse(): Int {
        return  View.GONE
    }

}