package ows.kotlinstudy.imageloadkotlin.service

import ows.kotlinstudy.imageloadkotlin.data.ImageResponse
import ows.kotlinstudy.imageloadkotlin.data.ImageResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("images")
    suspend fun getImageLoad(
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1
    ): ImageResponse

    companion object{
        const val BASE_URL = "https://rabh97p6f8.execute-api.us-east-2.amazonaws.com/challenge/"
    }
}