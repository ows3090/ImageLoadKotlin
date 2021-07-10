package ows.kotlinstudy.imageloadkotlin.data

import com.google.gson.annotations.SerializedName

data class ImageResponseBody(
    @SerializedName("images") val images: List<Images>,
    @SerializedName("next_page") val nextPage: Boolean,
    @SerializedName("error") val error: String?
)

