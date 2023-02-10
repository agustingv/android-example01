package com.xfiles.example01.repository

import com.xfiles.example01.data.model.MovieList

interface MovieRepository {
    suspend fun getMovieList(): MovieList
}