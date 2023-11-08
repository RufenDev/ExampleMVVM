package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: QuoteRepository
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(repository)
    }

    @Test
    fun `when the API doesn't return anything Then get values from Database`() = runBlocking {
        //Given
        coEvery { repository.getAllQuotesFromAPI() } returns emptyList()

        //When
        getQuotesUseCase()

        //Then
        coVerify(exactly = 1) { repository.getAllQuotesFromDatabase() }
        coVerify(exactly = 0) { repository.clearQuotesFromDatabase() }
        coVerify(exactly = 0) { repository.insertAllQuotesToDatabase(any()) }

    }

    @Test
    fun `when the API return somthing then get values from API`() = runBlocking {
        //Given
        val apiList = listOf(
            Quote(
                "La muerte soluciona todos los problemas. No hay hombre, no hay problema.",
                "Joseph Stalin"
            ),
            Quote(
                "Los hombres viejos declaran las guerras, pero son los jóvenes las que las luchas y los que mueren.",
                "Herbert Hoover"
            ),
        )
        coEvery { repository.getAllQuotesFromAPI() } returns apiList

        //When
        val responseList = getQuotesUseCase()

        //Then
        coVerify(exactly = 1) { repository.clearQuotesFromDatabase() }
        coVerify(exactly = 1) { repository.insertAllQuotesToDatabase(any()) }
        coVerify(exactly = 0) { repository.getAllQuotesFromDatabase() }

        assert(apiList == responseList)
    }

}