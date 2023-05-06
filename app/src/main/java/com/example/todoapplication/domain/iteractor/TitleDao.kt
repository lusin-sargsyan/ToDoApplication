package com.example.todoapplication.domain.iteractor

import androidx.room.*
import com.example.todoapplication.domain.entities.Title
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {
    @Insert
    suspend fun insertTitle(title: Title)

    @Query("SELECT * FROM titles WHERE id = :userId")
    suspend fun getTitle(userId: Long): Title

    @Query("SELECT * FROM titles")
    fun getAllTitles(): Flow<List<Title>>

    @Delete
    suspend fun deleteTitle(title: Title)
}
