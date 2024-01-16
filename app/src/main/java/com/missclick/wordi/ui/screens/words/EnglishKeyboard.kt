package com.missclick.wordi.ui.screens.words

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnglishKeyboard(state: WordsState?, vm: WordsViewModel, widthCell: Dp) {




    val symbols = listOf(
        listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
        listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', '⌫'),
        listOf('Z', 'X', 'C', 'V', 'B', 'N', 'M', '⏎')
    )


    Column() {
        repeat(symbols.size) { row ->
            Row {
                repeat(symbols[row].size) { symbol ->
                    val green =
                        state?.words?.any { check -> check.any { it?.symbol == symbols[row][symbol] && it.status == LetterStatus.GREEN } }
                    val yellow =
                        state?.words?.any { check -> check.any { it?.symbol == symbols[row][symbol] && it.status == LetterStatus.YELLOW } }
                    val black =
                        state?.words?.any { check -> check.any { it?.symbol == symbols[row][symbol] && it.status == LetterStatus.BLACK } }
                    Box(modifier = Modifier

                        .size(
                            height = 48.dp,
                            width = if (symbols[row][symbol] == '⏎') widthCell * 3 else widthCell
                        )
                        .padding(1.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            color = when {
                                green == true -> MaterialTheme.colorScheme.secondaryContainer
                                yellow == true -> MaterialTheme.colorScheme.tertiaryContainer
                                black == true -> MaterialTheme.colorScheme.primaryContainer
                                else -> MaterialTheme.colorScheme.secondary
                            }
                        )
                        .clickable {
                            when (symbols[row][symbol]) {
                                '⏎' -> {
                                    vm.enter()
                                }

                                '⌫' -> {
                                    vm.removeLast()
                                }

                                else -> {
                                    vm.clickLetter(symbols[row][symbol])
                                }
                            }
                        }) {
                        Text(
                            text = if (symbols[row][symbol] == '⏎') "Enter" else symbols[row][symbol].toString(),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                }
            }

        }
    }


}