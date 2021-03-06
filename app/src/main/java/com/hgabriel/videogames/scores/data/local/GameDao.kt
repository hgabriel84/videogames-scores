package com.hgabriel.videogames.scores.data.local

import androidx.room.*
import com.hgabriel.videogames.scores.data.vo.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM game order by averageScore DESC")
    fun getAllByScore(): List<Game>

    @Query("SELECT * FROM game order by name ASC")
    fun getAllByName(): List<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: Game)

    @Delete
    fun delete(game: List<Game>)
}