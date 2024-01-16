package com.missclick.wordi.ui.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.missclick.wordi.R
import com.missclick.wordi.ui.Direction
import com.missclick.wordi.ui.helper.swipe

@Composable
fun Menu(navController: NavHostController) {

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()
    ) {

        Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)) {


            Image(painter = painterResource(id = R.drawable.wordi), contentDescription = null,
                modifier = Modifier.size(210.dp))
            
            Spacer(modifier = Modifier.size(52.dp))

            Button(text = context.getString(R.string.daily)) {
                navController.navigate(Direction.DAILY.name)
            }

            Button(text = context.getString(R.string.random)) {
                navController.navigate(Direction.RANDOM.name)
            }



        }

    }

}

@Composable
fun Button(text : String, action: () -> Unit){

    Box(modifier = Modifier
        .size(210.dp, 70.dp)
        .border(2.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp))
        .clickable {
            action.invoke()
        }) {
        Text(
            text = text, fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(12.dp)
        )
    }
}