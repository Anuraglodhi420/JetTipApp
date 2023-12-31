package com.example.jettip

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Inputfield(modifier: Modifier=Modifier,valueState: MutableState<String>,
labelId:String,enabled:Boolean,isSingleLine:Boolean,keyboardType:KeyboardType=KeyboardType.Number,
imeAction:ImeAction=ImeAction.Next,
onAction:KeyboardActions=KeyboardActions.Default
){

        OutlinedTextField(value = valueState.value, onValueChange ={valueState.value=it}, label = { Text(text = labelId)}, leadingIcon = { Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = null
        )}, singleLine = isSingleLine,
             textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground, fontWeight = FontWeight(20)), enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction), keyboardActions = onAction, modifier = modifier.padding(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black, // Text color
                cursorColor = Color.Blue, // Cursor color
                placeholderColor = Color.Gray, // Placeholder color
                focusedBorderColor = Color.Black, // Border color when focused
                unfocusedBorderColor = Color.Gray, // Border color when not focused
                disabledBorderColor = Color.LightGray, // Border color when disabled
            )
        )
}
