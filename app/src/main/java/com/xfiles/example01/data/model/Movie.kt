package com.xfiles.example01.data.model

data class Movie(
    var id: Int = -1,
    var title: String = "",
    var poster_path: String = ""
)

data class MovieList(val results: List<Movie> = listOf())
