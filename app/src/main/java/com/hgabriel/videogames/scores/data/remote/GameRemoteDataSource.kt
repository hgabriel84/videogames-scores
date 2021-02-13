package com.hgabriel.videogames.scores.data.remote

import com.hgabriel.videogames.scores.data.vo.Game
import com.hgabriel.videogames.scores.data.vo.Resource
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class GameRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchGame(name: String): Resource<Game> {
        val service = retrofit.create(GameService::class.java)
        return getGameFromPlainText(
            request = { service.getPS4Game(name) },
            errorMessage = "Error fetching Game"
        )
    }

    private suspend fun getGameFromPlainText(
        request: suspend () -> Response<String>,
        errorMessage: String
    ): Resource<Game> {
        val result = request.invoke()
        return if (result.isSuccessful) {
            Resource.success(result.body()?.toGame())
        } else {
            Resource.error(errorMessage, null)
        }
    }
}