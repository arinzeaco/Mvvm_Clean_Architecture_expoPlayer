package com.obi.cleanarchitecture.screen.network

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.obi.cleanarchitecture.VideoResponse
import com.obi.cleanarchitecture.base.BaseViewModel
import com.obi.cleanarchitecture.domain.usecase.GetVideoUseCase
import com.obi.cleanarchitecture.util.AppUtils
import com.obi.cleanarchitecture.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class NetworkVideoViewModel @Inject constructor(app: Application,
   private val getVideoUseCase: GetVideoUseCase,
) : BaseViewModel(app) {
    val videsData: MutableLiveData<VideoResponse> = MutableLiveData()

    fun getVideo(page: Int, per_page:Int) {
        if(AppUtils().isNetworkAvailable(context)) {
            viewModelScope.launch {
                val getvid = getVideoUseCase.execute(page, per_page)
                getVideoResult(getvid)
            }
            }else{
                Toast.makeText(context,"An error occurred", Toast.LENGTH_LONG).show()

            }
    }

    private fun getVideoResult(result: Resource<VideoResponse>) {
        when (result) {
            is Resource.Loading -> {
                _progressBar.value = true

            }
            is Resource.Success -> {
                _progressBar.value = false
                result.data?.let { videsData.setValue(it) }
            }
            is Resource.Error -> {
                _progressBar.value = false
                result.message?.let {
                    Toast.makeText(context,"Internet is not available", Toast.LENGTH_LONG).show()
                }
            }
        }
    }



//    fun getVideo(page: Int, per_page:Int) = viewModelScope.launch(Dispatchers.IO) {
//
//        videsData.postValue(Resource.Loading())
//        try{
//            if(AppUtils().isNetworkAvailable(context)) {
//
//                val apiResult = getVideoUseCase.execute( page, per_page)
//                videsData.postValue(apiResult)
//            }else{
//                videsData.postValue(Resource.Error("Internet is not available"))
//            }
//
//        }catch (e: Exception){
//            videsData.postValue(Resource.Error(e.message.toString()))
//        }
//    }
}