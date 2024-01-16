package com.missclick.wordi.data

import com.missclick.wordi.data.entity.Language
import com.missclick.wordi.data.entity.WordsEntity


interface IRepository {

    fun getDictionary(language: Language) : List<String>
    fun getDailyWord(language: Language) : String
    fun getRandomWord(language: Language) : String

    fun getState(language: Language, daily : Boolean) : WordsEntity?
    fun putState(language: Language, daily : Boolean, wordsEntity: WordsEntity)




}