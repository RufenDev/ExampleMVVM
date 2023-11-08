package com.example.examplemvvm.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examplemvvm.data.database.entities.QuoteEntity

@Dao
interface QuoteDAO {

    @Query("SELECT * FROM quotes_table ORDER BY author DESC")
    suspend fun getAllQuotes():List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuotes(quotes:List<QuoteEntity>)

    @Query("DELETE FROM quotes_table")
    suspend fun deleteAllQuotes()
}