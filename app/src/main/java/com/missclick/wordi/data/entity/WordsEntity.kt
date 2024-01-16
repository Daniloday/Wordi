package com.missclick.wordi.data.entity


import com.missclick.wordi.ui.screens.words.Letter

data class WordsEntity(
    val words: MutableList<MutableList<Letter?>>,
    val correctWord: String,
    val win : Boolean?
)
