package com.example.examplemvvm.data

import com.example.examplemvvm.data.database.dao.QuoteDAO
import com.example.examplemvvm.data.database.entities.QuoteEntity
import com.example.examplemvvm.data.network.QuoteService
import com.example.examplemvvm.domain.model.Quote
import com.example.examplemvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDAO: QuoteDAO
) {

    suspend fun getAllQuotesFromAPI(): List<Quote> {
        val response = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase():List<Quote>{
        val response = quoteDAO.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertAllQuotesToDatabase(quotes:List<QuoteEntity>){
        quoteDAO.insertAllQuotes(quotes)
    }

    suspend fun clearQuotesFromDatabase(){
        quoteDAO.deleteAllQuotes()
    }

}