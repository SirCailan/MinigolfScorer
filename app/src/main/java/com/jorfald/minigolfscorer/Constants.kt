package com.jorfald.minigolfscorer

import com.jorfald.minigolfscorer.model.dataClasses.Game

const val BASE_URL = "https://us-central1-smalltalk-3bfb8.cloudfunctions.net/minigolf/"

// Error-codes
const val GAME_API_OK = 1200
const val GAME_API_NOT_FOUND = 1204
const val GAME_API_ERROR_CLIENT = 1400
const val GAME_API_ERROR_SERVER = 1500
const val GAME_API_ERROR_UNDEFINED = 1001