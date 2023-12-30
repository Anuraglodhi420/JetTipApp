package com.example.jettip

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val Iconbuttonsizemodifier=Modifier.size(40.dp)
@Composable
fun button(modifier: Modifier=Modifier,
            onClick:()->Unit,
           imageVector: ImageVector,
           elevation: Dp =4.dp,
           tint:Color= Color.Black.copy(alpha = 0.8f),
           backgroundColor:Color=MaterialTheme.colors.background
){
        Card(modifier = Modifier
            .padding(4.dp)
            .clickable {
                onClick.invoke()
            }
            .then(Iconbuttonsizemodifier),shape= CircleShape,backgroundColor=backgroundColor, elevation = elevation
        ) {
                Icon(imageVector = imageVector, contentDescription =null,tint=tint)
        }
}
