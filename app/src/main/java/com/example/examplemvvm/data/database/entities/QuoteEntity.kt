package com.example.examplemvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.examplemvvm.domain.model.Quote

@Entity(tableName = "quotes_table")
data class QuoteEntity(

    @PrimaryKey(true)
    @ColumnInfo("id") val id:Int = 0,

    @ColumnInfo("quote") val quote:String,

    @ColumnInfo("author") val author:String
)

fun Quote.toDatabase() = QuoteEntity(quote = quote, author = author)