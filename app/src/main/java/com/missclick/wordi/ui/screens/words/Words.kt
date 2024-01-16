package com.missclick.wordi.ui.screens.words

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.missclick.wordi.R
import com.missclick.wordi.ui.helper.Direction
import com.missclick.wordi.ui.helper.swipe
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


@Composable
fun Words(
    navController: NavHostController,
    daily : Boolean,
    vm: WordsViewModel = koinViewModel()
) {



    LaunchedEffect(key1 = null, block = {
        vm.init(daily)
    })

    val state by vm.state.collectAsState()



    val widthCell = (LocalConfiguration.current.screenWidthDp.dp - 16.dp) / 10

    Box(modifier = Modifier
        .fillMaxSize()
        .swipe {
            println(it)
            when (it) {
                Direction.DOWN -> {
                    vm.removeAll()
                }

                Direction.UP, Direction.RIGHT -> {
                    vm.enter()
                }

                Direction.LEFT -> {
                    vm.removeLast()
                }
            }
        }
    ) {

        Cells(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 52.dp), state = state
        )


        Column(
            modifier = Modifier
                .padding(bottom = 24.dp, start = 8.dp, end = 8.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            NextButton(state, vm, daily)

            EnglishKeyboard(state = state, vm = vm, widthCell = widthCell)
        }


    }


}






