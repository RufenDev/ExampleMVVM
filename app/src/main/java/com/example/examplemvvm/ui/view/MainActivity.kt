package com.example.examplemvvm.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.examplemvvm.databinding.ActivityMainBinding
import com.example.examplemvvm.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initUI()
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        quoteViewModel.onCreate()
        quoteViewModel.quoteModel.observe(this) {
            binding.tvQuote.text = it.quote
            binding.tvAuthor.text = it.author
        }

        quoteViewModel.isLoading.observe(this){
            binding.pbQuoteLoading.isVisible = it
            binding.tvQuote.isVisible = !it
            binding.tvAuthor.isVisible = !it
        }
    }

    private fun initListeners() {
        binding.viewContainer.setOnClickListener {
            quoteViewModel.randomQuote()
        }
    }

}