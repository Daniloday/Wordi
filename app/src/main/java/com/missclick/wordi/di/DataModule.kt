package com.missclick.wordi.di

import com.missclick.wordi.data.Dictionary
import com.missclick.wordi.data.IRepository
import com.missclick.wordi.data.LocalData
import com.missclick.wordi.data.Repository
import org.koin.dsl.module

val dataModule = module {
    single { Dictionary(get(), get()) }
    single { Repository(get(),get()) }
    single { LocalData(get()) }
}