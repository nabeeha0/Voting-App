package com.studereDevs.votingapp.db

import androidx.room.*

@Dao
interface EmotionDao {
    @Insert
    suspend fun insert(emotion: Emotion)
    @Insert
    fun insertMultiples(vararg emotion: Emotion)

    @Query ("SELECT * From Emotion")
    suspend fun getAll() : List<Emotion>

    @Update
    suspend fun update(emotion: Emotion)

    @Delete
    suspend fun delete(emotion: Emotion)

}