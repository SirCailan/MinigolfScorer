package com.jorfald.minigolfscorer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorfald.minigolfscorer.model.dataClasses.Game
import com.jorfald.minigolfscorer.model.dataClasses.PlayerScores
import com.jorfald.minigolfscorer.model.repository.GamesRepository

class GamesViewModel : ViewModel() {

    private val gamesRepository = GamesRepository()
    private lateinit var selectedGame: Game //Will be set by selectGame function.

    val allGames: MutableLiveData<List<Game>> = MutableLiveData()
    val playerScores: MutableLiveData<List<PlayerScores>> = MutableLiveData()
    val postScoreSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val createGameSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val responseMessage: MutableLiveData<String> = MutableLiveData()
    val pleaseWait: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchAllGames() {
        gamesRepository.getSavedGames { games ->
            allGames.postValue(games)
        }
    }

    fun selectGame(game: Game) {
        selectedGame = game
    }

    fun fetchPlayersForSelectedGame() {
        gamesRepository.fetchGameScores(selectedGame.gameId) { scores, responseCode ->
            playerScores.postValue(scores)

            responseMessage.postValue(responseCode.toString())
        }
        //TODO: Update liveData

    }

    fun postScoreForPlayer(gameId: String, playerName: String, hole: Int, score: Int) {
        //TODO: Call repository
        //TODO: Update liveData

    }

    fun createNewGame(gameName: String, players: List<String>) {
        gamesRepository.createNewGame(gameName, players) {  success ->
            createGameSuccess.postValue(success)
        }
        //TODO: Update liveData

    }
}