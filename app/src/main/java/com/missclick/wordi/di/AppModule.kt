package com.missclick.wordi.di

import com.missclick.wordi.ui.screens.words.WordsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
       WordsViewModel(
           get()
       )
    }

}

