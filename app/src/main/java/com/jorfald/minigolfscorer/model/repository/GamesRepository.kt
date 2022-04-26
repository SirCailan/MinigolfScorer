package com.jorfald.minigolfscorer.model.repository

import com.jorfald.minigolfscorer.model.dataClasses.Game
import com.jorfald.minigolfscorer.model.dataClasses.Player

class GamesRepository {

    fun fetchAllGames(callback: (List<Game>) -> Unit) {
        //TODO: Implement API-calls
    }

    fun fetchScoresForGame(gameId: String, callback: (List<Player>) -> Unit) {
        //TODO: Implement API-calls
    }

    fun postScoreForPlayer(gameId: String, playerName: String, hole: Int, score: Int, callback: (Boolean) -> Unit) {
        //TODO: Implement API-calls
    }

    fun createNewGame(gameName: String, callback: (Boolean) -> Unit ) {
        //TODO: Implement API-calls
    }

}
