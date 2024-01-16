package com.missclick.wordi.data

import android.content.Context
import com.google.gson.Gson
import com.missclick.wordi.data.entity.Language
import com.missclick.wordi.data.entity.WordsEntity
import java.util.Calendar

class LocalData(context: Context) {

    val sharedPreferences = context.getSharedPreferences("shared", 0)

    fun getState(language: Language, daily: Boolean): WordsEntity?{
        val jsonState = sharedPreferences.getString("state_${language.code}_${daily}_${
            if (daily) getDay() else ""
        }",null)
            ?: return null
        val gson = Gson()
        return gson.fromJson(jsonState, WordsEntity::class.java)

    }

    fun putState(language: Language, daily: Boolean, wordsEntity: WordsEntity){
        val gson = Gson()
        val jsonState = gson.toJson(wordsEntity)
        sharedPreferences.edit().putString("state_${language.code}_${daily}_${
            if (daily) getDay() else ""
        }", jsonState).apply()

    }

    fun getDay(): Int {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_YEAR)
        val year = calendar.get(Calendar.YEAR)
        return (year - 2024) * 366 + day
    }

}