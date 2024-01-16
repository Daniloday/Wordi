package com.missclick.wordi.ui.screens.words

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.missclick.wordi.R

@Composable
fun NextButton(state: WordsState?, vm: WordsViewModel,daily: Boolean) {
    val context = LocalContext.current


    if (state?.win != null) {


        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {

            if (state.win == false){

                repeat(5){
                    Box(
                        Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .border(2.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp))
                            ) {
                        Text(
                            text = state.correctWord.toList()[it].toString(),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(
                                Alignment.Center
                            ),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


                Spacer(modifier = Modifier.width(24.dp))
            }


            if (!daily){
                Box(modifier = Modifier
                    .border(2.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp))
                    .clickable {
                        vm.restart()
                    }) {
                    Text(
                        text = context.getString(R.string.next), fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(12.dp)
                    )
                }
            }

        }



        Spacer(modifier = Modifier.height(24.dp))
    }
}