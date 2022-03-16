package com.obi.cleanarchitecture.screen.network

import android.app.Application
import androidx.lifecycle.*
import com.obi.cleanarchitecture.VideoResponse
import com.obi.cleanarchitecture.base.BaseViewModel
import com.obi.cleanarchitecture.domain.usecase.GetVideoUseCase
import com.obi.cleanarchitecture.util.AppUtils
import com.obi.cleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.launch
import java.lang.Exception


@HiltViewModel
class NetworkVideoViewModel @Inject constructor(app: Application,
   private val getVideoUseCase: GetVideoUseCase,
) : BaseViewModel(app) {
    val videsData: MutableLiveData<Resource<VideoResponse>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()


    fun getVideo(page: Int, per_page:Int) = viewModelScope.launch(Dispatchers.IO) {

        videsData.postValue(Resource.Loading())
        try{
            if(AppUtils().isNetworkAvailable(context)) {

                val apiResult = getVideoUseCase.execute( page, per_page)
                videsData.postValue(apiResult)
            }else{
                videsData.postValue(Resource.Error("Internet is not available"))
            }

        }catch (e: Exception){
            videsData.postValue(Resource.Error(e.message.toString()))
        }
    }
}