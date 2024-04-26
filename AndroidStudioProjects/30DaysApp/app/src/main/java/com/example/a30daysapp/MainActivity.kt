package com.example.a30daysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.a30daysapp.ui.theme._30DaysAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DaysAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    ElevatedCard()
                }
            }
        }
    }
}
@Composable
fun ElevatedCard() {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(
                modifier = Modifier.weight((1f))
            ) {
                Text(
                    text = "DAY 1",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    text = "I did it!!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            ){
                //Image(
                 //   painter = painterResource()
                //)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _30DaysAppTheme {
        ElevatedCard()
    }
}