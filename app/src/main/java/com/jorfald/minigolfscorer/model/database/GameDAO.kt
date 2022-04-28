package com.jorfald.minigolfscorer.model.database

import androidx.room.*
import com.jorfald.minigolfscorer.model.dataClasses.Game
import com.jorfald.minigolfscorer.model.dataClasses.PlayerScores

@Dao
interface GameDAO {
    @Query("SELECT * FROM games")
    fun getAllGames(): List<Game>

    @Query("DELETE FROM games")
    fun deleteAllGames()

    @Insert
    fun saveGamesList(games: List<Game>)

    /*@Query("SELECT * FROM user WHERE id = :userId LIMIT 1")
    fun getLoggedUserById(userId: String): User

    @Insert
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)
    */
}