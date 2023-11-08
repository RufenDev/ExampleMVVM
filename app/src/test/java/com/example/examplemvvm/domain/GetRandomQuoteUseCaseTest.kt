package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: QuoteRepository
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(repository)
    }

    @Test
    fun `when Database is empty then return null`() = runBlocking {
        //Given
        coEvery { repository.getAllQuotesFromDatabase() } returns emptyList()

        //when
        val response = getRandomQuoteUseCase()

        //Then
        assert(response == null)
    }

    @Test
    fun `when Database is not empty then return a quote`() = runBlocking {
        //Given
        val myList = listOf(
            Quote(
                "La muerte soluciona todos los problemas. No hay hombre, no hay problema.",
                "Joseph Stalin"
            )
        )
        coEvery { repository.getAllQuotesFromDatabase() } returns myList

        //When
        val response = getRandomQuoteUseCase()

        //Then
        assert(response == myList.first())
    }
}