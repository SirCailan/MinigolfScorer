package com.jorfald.minigolfscorer.model.dataClasses

import com.beust.klaxon.Json

data class PlayerScores(
    val playerName: String,
    @Json(serializeNull = false) val scores: List<Score>?
)