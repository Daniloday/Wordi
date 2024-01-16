package com.missclick.wordi.ui.screens.words

import androidx.lifecycle.ViewModel
import com.missclick.wordi.data.Dictionary
import com.missclick.wordi.data.IRepository
import com.missclick.wordi.data.Repository
import com.missclick.wordi.data.entity.Language
import com.missclick.wordi.data.entity.WordsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale
import kotlin.random.Random

class WordsViewModel(
    private val repository: Repository
) : ViewModel() {

    private lateinit var dictionaryCurrent: List<String>

    private val _state = MutableStateFlow<WordsState?>(null)

    val state: StateFlow<WordsState?> = _state


    fun init(daily: Boolean) {
        val language = Language.English
        dictionaryCurrent = repository.getDictionary(language)
        val state = repository.getState(language, daily)
        if (state == null) {
            createEmptyField(language, daily)
            return
        }
        _state.value = WordsState(
            words = state.words,
            language = language,
            correctWord = state.correctWord,
            win = state.win,
            daily = daily,
            worker = 0
        )
    }

    fun saveState() {

        repository.putState(
            language = state.value!!.language,
            daily = state.value!!.daily,
            wordsEntity = WordsEntity(
                words = state.value!!.words,
                correctWord = state.value!!.correctWord,
                win = state.value!!.win
            )
        )
    }


    fun createEmptyField(language: Language, daily: Boolean) {
        val emptyColumns = mutableListOf<MutableList<Letter?>>()
        repeat(6) {
            val emptyLetters = mutableListOf<Letter?>()
            repeat(5) {
                emptyLetters.add(null)
            }
            emptyColumns.add(emptyLetters)
        }
        _state.value = WordsState(
            words = emptyColumns,
            language = language,
            correctWord = if (daily) repository.getDailyWord(language) else repository.getRandomWord(language),
            daily = daily,
            worker = 0
        )
        saveState()
    }


    fun clickLetter(symbol: Char) {

        if (state.value?.win != null) {
            return
        }

        val indexColumn = state.value?.words?.indexOfFirst {
            it.first()?.status == LetterStatus.GRAY || it.first() == null
        } ?: -1
        println(indexColumn)
        if (indexColumn != -1) {
            val indexLetter = state.value?.words?.get(indexColumn)?.indexOfFirst {
                it == null
            } ?: -1
            println(indexLetter)
            if (indexLetter != -1) {
                _state.value = state.value?.copy(
                    words = state.value!!.words.also {
                        it[indexColumn][indexLetter] = Letter(symbol, LetterStatus.GRAY)
                    },
                    worker = state.value!!.worker + 1
                )
            }

        }
        saveState()
    }

    fun enter() {


        if (state.value?.win != null) {
            return
        }

        val indexColumn = state.value?.words?.indexOfFirst {
            it.first()?.status == LetterStatus.GRAY
        } ?: -1
        if (indexColumn != -1) {
            val count = state.value?.words?.get(indexColumn)?.count {
                it?.status == LetterStatus.GRAY
            } ?: -1
            var nowWords = ""
            repeat(5) {
                nowWords += state.value?.words?.get(indexColumn)?.get(it)?.symbol.toString()
            }
            if (count == 5 && nowWords.lowercase(Locale.getDefault()) in dictionaryCurrent) {

                val newColumn = mutableListOf<Letter?>(null, null, null, null, null)
                val correctWord = state.value!!.correctWord.toMutableList()
                state.value!!.words[indexColumn].forEachIndexed { index, letter ->
                    if (letter?.symbol == correctWord[index]) {
                        newColumn[index] =
                            Letter(
                                symbol = letter.symbol,
                                status = LetterStatus.GREEN
                            )

                        correctWord[index] = ' '
                    }
                }
                state.value!!.words[indexColumn].forEachIndexed { index, letter ->
                    if (letter!!.symbol in correctWord && newColumn[index] == null) {
                        newColumn[index] =
                            Letter(
                                symbol = letter.symbol,
                                status = LetterStatus.YELLOW
                            )
                        correctWord.remove(letter.symbol)
                    }
                }
                state.value!!.words[indexColumn].forEachIndexed { index, letter ->

                    if (newColumn[index] == null) {
                        newColumn[index] =
                            Letter(
                                symbol = letter!!.symbol,
                                status = LetterStatus.BLACK
                            )

                    }
                }
                _state.value = state.value?.copy(
                    words = state.value!!.words.also {
                        it[indexColumn] = newColumn
                    },
                    worker = state.value!!.worker + 1
                )
                checkFinish(nowWords)
            }

        }
        saveState()
    }


    private fun checkFinish(lastWord: String) {

        if (lastWord == state.value?.correctWord) {
            _state.value = state.value?.copy(
                win = true
            )
            saveState()
            return
        }

        if (state.value?.words?.last()?.last() != null) {
            _state.value = state.value?.copy(
                win = false
            )
            saveState()
            return
        }

    }

    fun removeLast() {

        if (state.value?.win != null) {
            return
        }

        val indexColumn = state.value?.words?.indexOfFirst {
            it.first()?.status == LetterStatus.GRAY
        } ?: -1
        println(indexColumn)
        if (indexColumn != -1) {

            val indexLetter = state.value?.words?.get(indexColumn)?.indexOfLast {
                it?.status == LetterStatus.GRAY
            } ?: -1
            println(indexLetter)
            if (indexLetter != -1) {
                _state.value = state.value?.copy(
                    words = state.value!!.words.also {
                        it[indexColumn][indexLetter] = null
                    },
                    worker = state.value!!.worker + 1
                )
            }

        }
        saveState()
    }

    fun removeAll() {

        if (state.value?.win != null) {
            return
        }

        val indexColumn = state.value?.words?.indexOfFirst {
            it.first()?.status == LetterStatus.GRAY
        } ?: -1

        if (indexColumn != -1) {
            _state.value = state.value?.copy(
                words = state.value!!.words.also {
                    it[indexColumn] = mutableListOf(null, null, null, null, null)
                },
                worker = state.value!!.worker + 1
            )
        }
        saveState()
    }

    fun restart() {
        val emptyColumns = mutableListOf<MutableList<Letter?>>()
        repeat(6) {
            val emptyLetters = mutableListOf<Letter?>()
            repeat(5) {
                emptyLetters.add(null)
            }
            emptyColumns.add(emptyLetters)
        }
        _state.value = WordsState(
            words = emptyColumns,
            language = state.value!!.language,
            correctWord = repository.getRandomWord(language = state.value!!.language),
            worker = 0,
            daily = state.value!!.daily
        )
        saveState()
    }


}


data class WordsState(
    val words: MutableList<MutableList<Letter?>>,
    val language: Language,
    val correctWord: String,
    val worker: Int,
    val daily: Boolean,
    val win: Boolean? = null,

    )

data class Letter(
    val symbol: Char,
    val status: LetterStatus
)

enum class LetterStatus {
    GRAY, BLACK, YELLOW, GREEN
}

