package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRepository){

    suspend operator fun invoke():Quote?{
        val quotes = repository.getAllQuotesFromDatabase()

        if(quotes.isNotEmpty()){
            val random = (quotes.indices).random()
            return quotes[random]
        }

        return null
    }

}