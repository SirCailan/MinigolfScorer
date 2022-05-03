package com.jorfald.minigolfscorer.model.repository

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.jorfald.minigolfscorer.*
import com.jorfald.minigolfscorer.model.dataClasses.Game
import com.jorfald.minigolfscorer.model.dataClasses.PlayerScores
import com.jorfald.minigolfscorer.model.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GamesRepository {
    private val appContext = MinigolfScorerApp.application.applicationContext
    private val gameDao = AppDatabase.getInstance(appContext).gameDao()
    private val requestQueue = Volley.newRequestQueue(appContext)

    fun fetchAllGames(callback: (Boolean, Int) -> Unit) {

        val url = "${BASE_URL}getAllGames"

        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val games = Klaxon().parseArray<Game>(response) ?: listOf()

                if (games.isNotEmpty()) {
                    saveGames(games)

                    callback(true, GAME_API_OK)
                } else {
                    //TODO:

                    callback(true, GAME_API_NOT_FOUND)
                }

            },
            { error ->
                val responseCode = when (error.networkResponse.statusCode) {
                    400 -> GAME_API_ERROR_CLIENT
                    500 -> GAME_API_ERROR_SERVER
                    else -> GAME_API_ERROR_UNDEFINED
                }
                callback(false, responseCode)
            }
        )

        requestQueue.add(request)
    }

    fun fetchGameScores(gameId: String, callback: (List<PlayerScores>, Int) -> Unit) {
        val url = "${BASE_URL}getGameScores?gameId=$gameId"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val gameScores = Klaxon().parseArray<PlayerScores>(response) ?: listOf()

                val responseCode = when (gameScores.isNotEmpty()) {
                    true -> GAME_API_OK
                    false -> GAME_API_NOT_FOUND
                }

                callback(gameScores, responseCode)

            },
            { error ->
                callback(listOf(), GAME_API_ERROR_UNDEFINED)
            }
        )

        requestQueue.add(stringRequest)
    }

    fun postScoreForPlayer(
        gameId: String,
        playerName: String,
        hole: Int,
        score: Int,
        callback: (Boolean) -> Unit
    ) {
        //TODO: Implement API-calls
    }

    fun createNewGame(gameName: String, players: List<String>, callback: (Boolean) -> Unit) {
        val url = BASE_URL + "createGame"

        val postRequest: StringRequest = object : StringRequest(
            Method.POST,
            url,
            { json ->
                callback(true)
            },
            { error ->
                callback(false)
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()

                params["gameName"] = gameName
                params["players"] = Klaxon().toJsonString(players)

                return params
            }
        }

        requestQueue.add(postRequest)
    }

    fun saveGames(games: List<Game>) {
        CoroutineScope(Dispatchers.IO).launch {
            gameDao.deleteAllGames()
            gameDao.saveGamesList(games)
        }.start()
    }

    fun getSavedGames(callback: (List<Game>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            callback(gameDao.getAllGames())
        }.start()
    }

}
