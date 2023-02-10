package com.xfiles.example01.repository

import com.xfiles.example01.data.model.MovieList
import com.xfiles.example01.data.remote.RemoteMovieDadaSource

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDadaSource): MovieRepository
{
    override suspend fun getMovieList(): MovieList {
        return dataSourceRemote.getMovieList();
    }
}