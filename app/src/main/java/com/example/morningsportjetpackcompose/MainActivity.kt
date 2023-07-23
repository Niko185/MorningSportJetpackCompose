package com.example.morningsportjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Background()
            MainScreen()
        }
    }
}

@Composable
fun Background() {
  Image(
      painter = painterResource(id = R.drawable.ic_background),
      contentDescription ="background",
      modifier = Modifier
          .fillMaxSize(),
      contentScale = ContentScale.Crop
    )
}

@Composable
fun TextSport(textSport: String) {
    val colorResource = LocalContext.current.resources.getColor(R.color.secondary_color, null)
    val buttonColor = Color(colorResource)
    Text(
        text = "To morning your sport is: $textSport",
        color = buttonColor,
        fontSize = 16.sp,
    )
}

@Composable
fun Polygon() {
    Image(
        painter = painterResource(id = R.drawable.ic_main_polygon),
        contentDescription = "polygon",
        modifier = Modifier
            .size(54.dp)
    )
}

@Composable
fun Circle(value: Float){
    Image(painter = painterResource(id = R.drawable.ic_main_cicrle),
        contentDescription = "circle",
        modifier = Modifier
            .padding(16.dp)
            .rotate(value)
    )
}

@Composable
fun ButtonStart(onClickAction: () -> Unit) {
    val colorResource = LocalContext.current.resources.getColor(R.color.secondary_color, null)
    val buttonColor = Color(colorResource)
    Button(
        onClick = onClickAction,
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(buttonColor)
    ) {
        Text(text = "Start")
    }
}

@Composable
fun CircleAndPolygon(value: Float){
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Polygon()
        Circle(value)
    }
}

@Composable
fun MainScreen() {
    val nameSportList = listOf("Bicycle", "Fitness", "Running", "Stretching", "Karate", "Workout", "Jump", "Yoga")
    var stateValueForCircle by remember { mutableStateOf(0) }

    var nameSport by remember {
        mutableStateOf("")
    }

    val animatedStateValue: Float by animateFloatAsState(
        targetValue = stateValueForCircle.toFloat(),
        animationSpec = tween(3000),
        finishedListener = {
            val index = (360 - (it % 360).toInt()) / (360 / nameSportList.size )
            Log.e("MyLog", "$index")
            nameSport = nameSportList[index]
            }
    )




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        TextSport(nameSport)
        CircleAndPolygon(animatedStateValue)
        ButtonStart {
            stateValueForCircle = (720..1080).random() + animatedStateValue.toInt()
        }
    }
}

