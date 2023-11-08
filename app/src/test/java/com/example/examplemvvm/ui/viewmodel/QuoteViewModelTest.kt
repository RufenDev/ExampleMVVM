package com.example.examplemvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.examplemvvm.domain.GetQuotesUseCase
import com.example.examplemvvm.domain.GetRandomQuoteUseCase
import com.example.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest {

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule = InstantTaskExecutorRule()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when ViewModel is created, get all quotes and set the first one on the LiveData`() =
        runTest {
            //Given
            val quoteList = listOf(
                Quote(
                    "La muerte soluciona todos los problemas. No hay hombre, no hay problema.",
                    "Joseph Stalin"
                ),
                Quote(
                    "Los hombres viejos declaran las guerras, pero son los jóvenes las que las luchas y los que mueren.",
                    "Herbert Hoover"
                )
            )

            coEvery { getQuotesUseCase() } returns quoteList

            //When
            quoteViewModel.onCreate()

            //Then
            Assert.assertEquals(
                "El primer valor del ViewModel NO es el primer valor de la lista recibida del caso de uso.",
                quoteViewModel.quoteModel.value,
                quoteList.first()
            )

        }

    @Test
    fun `when RandomQuoteCase return a quote, set on the LiveData`() = runTest {
        //Given
        val quote = Quote("Ejemplo de cita", "Yo")
        coEvery { getRandomQuoteUseCase() } returns quote

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quote)

    }

    @Test
    fun `if RandomQuoteCase return null, keep the last quote on the LiveData`() = runTest {
        //Given
        val quote = Quote("Ejemplo de cita", "Yo")
        quoteViewModel.quoteModel.value = quote

        coEvery { getRandomQuoteUseCase() } returns null

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }
}