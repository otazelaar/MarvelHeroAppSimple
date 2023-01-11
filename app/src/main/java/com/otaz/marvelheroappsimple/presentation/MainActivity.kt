package com.otaz.marvelheroappsimple.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.otaz.marvelheroappsimple.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            Image(
                painter = painterResource(id = R.drawable.happy_meal_small),
                contentDescription = "Happy Meal",
                modifier = Modifier.height(300.dp),
                contentScale= ContentScale.Crop
            )
        }

    }
}