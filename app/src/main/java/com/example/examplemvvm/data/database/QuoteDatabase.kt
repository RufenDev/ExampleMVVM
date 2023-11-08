package com.example.examplemvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examplemvvm.data.database.dao.QuoteDAO
import com.example.examplemvvm.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDAO():QuoteDAO


}