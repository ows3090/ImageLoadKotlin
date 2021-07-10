package ows.kotlinstudy.imageloadkotlin.data

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("body") val body: ImageResponseBody
)
