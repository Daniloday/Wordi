package com.missclick.wordi.ui.screens.words

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Cells(modifier: Modifier, state: WordsState?) {




    if (state != null) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = modifier) {
            repeat(6) { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(5) { letter ->

                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    color = when (state.words[row][letter]?.status) {
                                        LetterStatus.BLACK -> MaterialTheme.colorScheme.primaryContainer
                                        LetterStatus.GREEN -> MaterialTheme.colorScheme.secondaryContainer
                                        LetterStatus.YELLOW -> MaterialTheme.colorScheme.tertiaryContainer
                                        else -> Color.Transparent
                                    }
                                )
                                .border(
                                    border =
                                    when (state.words[row][letter]?.status) {
                                        null -> BorderStroke(
                                            2.dp,
                                            MaterialTheme.colorScheme.secondary
                                        )

                                        LetterStatus.GRAY ->
                                            BorderStroke(
                                                2.dp,
                                                MaterialTheme.colorScheme.primary
                                            )

                                        else -> BorderStroke(0.dp, Color.Transparent)
                                    },
                                    shape = RoundedCornerShape(4.dp)


                                )
                        ) {

                            state.words[row][letter]?.let {
                                Text(
                                    text = it.symbol.toString(),
                                    fontSize = 28.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .align(Alignment.Center),
                                    fontWeight = FontWeight.Bold

                                )
                            }


                        }
                    }
                }
            }
        }
    }


}