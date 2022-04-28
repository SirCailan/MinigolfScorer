package com.jorfald.minigolfscorer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorfald.minigolfscorer.model.repository.GamesRepository

class SplashViewModel : ViewModel() {
    private val gamesRepository = GamesRepository()
    var gamesLoaded: MutableLiveData<Boolean> = MutableLiveData()
    var responseMessage: MutableLiveData<String> = MutableLiveData()

    fun loadAllGames() {
        gamesRepository.fetchAllGames { success, responseCode ->
            gamesLoaded.postValue(success)

            responseMessage.postValue(responseCode.toString())
        }
    }
}