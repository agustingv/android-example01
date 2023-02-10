package com.xfiles.example01.data.remote

import com.xfiles.example01.application.AppConstants
import com.xfiles.example01.data.model.MovieList
import com.xfiles.example01.repository.WebService

class RemoteMovieDadaSource(private val webService: WebService) {

    suspend fun getMovieList() : MovieList
    {
        return webService.getMovieList(lang = AppConstants.LANG, apiKey = AppConstants.API_KEY)
    }
}