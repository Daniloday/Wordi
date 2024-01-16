package com.missclick.wordi.data


import android.content.Context
import androidx.compose.ui.text.toUpperCase
import com.missclick.wordi.R
import com.missclick.wordi.data.entity.Language
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.Locale
import kotlin.random.Random


class Dictionary(val context: Context, val localData: LocalData) {

    fun getDailyWord(language: Language): String{
        val index = (0..2315).random(Random(localData.getDay()))
        println(index)
        return getDictionary(language)[index].uppercase(Locale.ROOT)
    }

    fun getRandomWord(language: Language) : String{
        return getDictionary(language).random(Random(System.currentTimeMillis())).uppercase(Locale.ROOT)
    }

    fun getDictionary(language: Language): List<String> {

        val fileName = when(language){
            Language.English -> R.raw.en
            else -> {R.raw.en}
        }

        val inputStream: InputStream = context.resources.openRawResource(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val dictionary = mutableListOf<String>()
        try {
            while (true) {
                val line = reader.readLine()
                if (line != null){
                    dictionary.add(line)
                }else
                    break
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return dictionary

    }


}