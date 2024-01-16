package com.missclick.wordi.data

import com.missclick.wordi.data.entity.Language
import com.missclick.wordi.data.entity.WordsEntity

class Repository(val localData: LocalData, val dictionary: Dictionary) : IRepository {

    override fun getDictionary(language: Language): List<String> {
        return dictionary.getDictionary(language)
    }

    override fun getDailyWord(language: Language): String {
        return dictionary.getDailyWord(language)
    }

    override fun getRandomWord(language: Language): String {
        return dictionary.getRandomWord(language)
    }




    override fun getState(language: Language, daily: Boolean): WordsEntity? {
        return localData.getState(language, daily)
    }

    override fun putState(language: Language, daily: Boolean, wordsEntity: WordsEntity) {
        localData.putState(language,daily,wordsEntity)
    }



}