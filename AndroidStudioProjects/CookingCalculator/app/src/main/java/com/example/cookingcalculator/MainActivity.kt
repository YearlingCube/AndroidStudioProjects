@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cookingcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.cookingcalculator.ui.theme.CookingCalculatorTheme
import androidx.compose.material3.TopAppBarColors as TopAppBarColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookingCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CookingCalculator()
                }
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent()
{
    //Scaffold(
        //topBar = { TopAppBar(title = { Text("Cooking | Unit Converter", color = Color.White) }, ) },
    //    content = { CookingCalculator() }
    //)
}


@Composable
fun CookingCalculator(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("1") }

    var mExpanded by remember { mutableStateOf(false) }

    var originalUnit by remember { mutableStateOf("tsp") }


    val mUnits = listOf("tsp", "tbsp", "fl oz", "c", "pt", "qt", "gal")

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("tsp") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(
        Modifier
            .fillMaxWidth()
            .absolutePadding(10.dp, 125.dp, 10.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Cooking Amount") }
        )
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {Text("Cooking Units")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            mUnits.forEach { label ->
                DropdownMenuItem(text = { Text(text = label)}, onClick = {
                    mSelectedText = label
                    mExpanded = false
                    if(isNumeric(text)) {
                       text = UnitButton(label, originalUnit, text.toDouble()).toString()
                        originalUnit = label
                    }
                })
            }
        }
    }
    Column(
        Modifier
            .fillMaxWidth()
            .absolutePadding(10.dp, 300.dp, 10.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ){

            Button(
                onClick = { text = doubleNumber(text).toString() },
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp)
            ) {
                Text("Double", color = Color.White, fontSize = 15.sp)
            }
            Button(
                onClick = { text = halfNumber(text).toString() },
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp)
            ) {
                Text("Half", color = Color.White, fontSize = 15.sp)
            }
            Button(
                onClick = { text = tripleNumber(text).toString() },
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp)
            ) {
                Text("Triple", color = Color.White, fontSize = 15.sp)
            }
        }
    }
}
fun doubleNumber(text: String): Double {
    if (isNumeric(text)) {
        return (text.toDouble() * 2)
    }
    return 0.0
}
fun tripleNumber(text: String): Double {
    if (isNumeric(text)) {
        return (text.toDouble() * 3)
    }
    return 0.0
}
fun halfNumber(text: String): Double {
    if(isNumeric(text))
    {
        return (text.toDouble() / 2)
    }
    return 0.0
}
fun isNumeric(toCheck: String): Boolean{
    return toCheck.toDoubleOrNull() != null
}
fun UnitButton(Unit: String, originalUnit: String, amount: Double): Double{

    if(originalUnit == "tsp")
    {
        if(Unit == "tbsp")
        {
            return amount / 3
        }
        if(Unit == "fl oz")
        {
            return amount / 6
        }
        if(Unit == "c")
        {
            return amount / 48
        }
        if(Unit == "pt")
        {
            return amount / 96
        }
        if(Unit == "qt")
        {
            return amount / 192
        }
        if(Unit == "gal")
        {
            return amount / 768
        }
    }
    if(originalUnit == "tbsp")
    {
        if(Unit == "tsp")
        {
           return amount * 3
        }
        if(Unit == "fl oz")
        {
            return amount / 16
        }
        if(Unit == "c")
        {
            return amount / 32
        }
        if(Unit == "pt")
        {
            return amount / 64
        }
        if(Unit == "qt")
        {
            return amount / 128
        }
        if(Unit == "gal")
        {
            return amount / 512
        }
    }
    if(originalUnit == "fl oz")
    {
        if(Unit == "tsp")
        {
            return amount * 6
        }
        if(Unit == "tbsp")
        {
            return amount * 2
        }
        if(Unit == "c")
        {
            return amount / 8
        }
        if(Unit == "pt")
        {
            return amount / 16
        }
        if(Unit == "qt")
        {
            return amount / 32
        }
        if(Unit == "gal")
        {
            return amount / 128
        }
    }
    if(originalUnit == "c")
    {
        if(Unit == "tsp")
        {
            return amount * 48
        }
        if(Unit == "tbsp")
        {
            return amount * 16
        }
        if(Unit == "fl oz")
        {
            return amount * 8
        }
        if(Unit == "pt")
        {
            return amount / 2
        }
        if(Unit == "qt")
        {
            return amount / 4
        }
        if(Unit == "gal")
        {
            return amount / 16
        }
    }
    if(originalUnit == "pt")
    {
        if(Unit == "tsp")
        {
            return amount * 96
        }
        if(Unit == "tbsp")
        {
            return amount * 32
        }
        if(Unit == "fl oz")
        {
            return amount * 16
        }
        if(Unit == "c")
        {
            return amount * 2
        }
        if(Unit == "qt")
        {
            return amount / 2
        }
        if(Unit == "gal")
        {
            return amount / 8
        }
    }
    if(originalUnit == "qt")
    {
        if(Unit == "tsp")
        {
            return amount * 192
        }
        if(Unit == "tbsp")
        {
            return amount * 64
        }
        if(Unit == "fl oz")
        {
            return amount * 32
        }
        if(Unit == "c")
        {
            return amount * 4
        }
        if(Unit == "pt")
        {
            return amount * 2
        }
        if(Unit == "gal")
        {
            return amount / 4
        }
    }
    if(originalUnit == "gal") {
        if (Unit == "tsp") {
            return amount * 768
        }
        if (Unit == "tbsp") {
            return amount * 256
        }
        if (Unit == "fl oz") {
            return amount * 128
        }
        if (Unit == "c") {
            return amount * 16
        }
        if (Unit == "pt") {
            return amount * 8
        }
        if (Unit == "qt") {
            return amount * 4
        }
    }
    return amount
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CookingCalculatorTheme {
        CookingCalculator()
    }
}