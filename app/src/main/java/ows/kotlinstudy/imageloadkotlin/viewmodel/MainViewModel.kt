package ows.kotlinstudy.imageloadkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ows.kotlinstudy.imageloadkotlin.data.ImageResponse
import ows.kotlinstudy.imageloadkotlin.data.ImageResponseBody
import ows.kotlinstudy.imageloadkotlin.service.ImageService
import ows.kotlinstudy.imageloadkotlin.service.ImageService.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {

    private var service: ImageService
    var isLoadingState = MutableLiveData<Boolean>()
    var mutableImageModels = MutableLiveData<ImageResponseBody>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ImageService::class.java)
    }

    fun fecthServer(){
        isLoadingState.value = true

        viewModelScope.launch {
            val response = service.getImageLoad(10,1)

            if(response.statusCode == 200){
                isLoadingState.value = false
                mutableImageModels.value = response.body
            }else{
                Log.d(TAG, "HTTP Status not success")
            }
        }
    }

    companion object{
        const val TAG = "MainViewModel"
    }
}