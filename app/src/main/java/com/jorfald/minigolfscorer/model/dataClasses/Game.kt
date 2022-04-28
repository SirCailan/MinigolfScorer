package com.jorfald.minigolfscorer.model.dataClasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @PrimaryKey val gameId: String,
    val gameName: String,
    val created: Long,
    val isActive: Boolean
)
