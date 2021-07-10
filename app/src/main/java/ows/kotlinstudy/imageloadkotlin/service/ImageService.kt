package ows.kotlinstudy.imageloadkotlin.service

import ows.kotlinstudy.imageloadkotlin.ImageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("/Images")
    suspend fun getImageLoad(
        @Query("display") display: Int,
        @Query("start") start: Int,
        @Query("err") err: Int
    ): ImageDto
}