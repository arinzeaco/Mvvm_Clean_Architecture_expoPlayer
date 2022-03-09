package com.obi.cleanarchitecture.screen.mainact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(app: Application): AndroidViewModel(app) {


}

