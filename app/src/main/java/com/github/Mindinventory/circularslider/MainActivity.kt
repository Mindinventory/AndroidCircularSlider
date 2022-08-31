package com.github.Mindinventory.circularslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.github.Mindinventory.circularslider.ui.theme.CircularSliderTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircularSliderTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MiProjectPlanning()
                //...

                   /* var currentValue by remember { mutableStateOf(0f) }

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressBar(
                            radiusCircle = 150.dp,
                            percentageFontSize = 12.sp,
                            progressWidth = 30f,
                            thumbRadius = 20f,

                            tickColor = Color.White,
                            tickhighlightedColor = Color.Gray,
                            currentProgressToBeReturned = { currentValueReturned ->
                                currentValue = currentValueReturned
                            },
                            currentUpdatedValue = currentValue
                        )
                    }*/
                }
            }
        }
    }
}



