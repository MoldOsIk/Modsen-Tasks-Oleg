package com.example.modsen_tasks_oleg.ui.screen.task2.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.modsen_tasks_oleg.R

@Composable
fun SearchField(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            onSearch(it)
        },
        modifier = modifier
            .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(12.dp),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        decorationBox = { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    text = stringResource(R.string.text_for_search),
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
            innerTextField()
        },
        cursorBrush = SolidColor(Color.Blue)
    )
}