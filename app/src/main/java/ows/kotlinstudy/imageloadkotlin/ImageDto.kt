package ows.kotlinstudy.imageloadkotlin

data class ImageDto(
    var images: List<ImageDto>,
    var next_page : Boolean,
    var error: String
)
