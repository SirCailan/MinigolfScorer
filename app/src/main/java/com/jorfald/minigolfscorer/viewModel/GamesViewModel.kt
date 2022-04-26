package com.jorfald.minigolfscorer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorfald.minigolfscorer.model.dataClasses.Game
import com.jorfald.minigolfscorer.model.repository.GamesRepository
import com.jorfald.minigolfscorer.model.dataClasses.Player

class GamesViewModel : ViewModel() {

    private val gameRepository = GamesRepository()

    val allGames: MutableLiveData<List<Game>> = MutableLiveData()
    val allPlayers: MutableLiveData<List<Player>> = MutableLiveData()
    val postScoreSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val createGameSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    var selectedGameId: String = ""

    fun fetchAllGames() {
        //TODO: Call repository
        //TODO: Update liveData
    }

    fun fetchPlayersForSelectedGame() {
        //TODO: Call repository
        //TODO: Update liveData

    }

    fun postScoreForPlayer(gameId: String, playerName: String, hole: Int, score: Int) {
        //TODO: Call repository
        //TODO: Update liveData

    }

    fun createNewGame(gameName: String) {
        //TODO: Call repository
        //TODO: Update liveData

    }
}