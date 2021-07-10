package ows.kotlinstudy.imageloadkotlin.data

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("link") var link: String,
    @SerializedName("thumbnail") var thumbnail: String,
    @SerializedName("sizeHeight") var sizeHeight: Int,
    @SerializedName("sizeWidth") var sizeWidth: Int
)