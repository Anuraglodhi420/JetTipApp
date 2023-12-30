package com.example.jettip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jettip.ui.theme.JetTipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTipTheme {
                myapp()
            }
        }
    }
}

@Composable
fun myapp(){
    Box(modifier = Modifier
        .fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.rupee2), contentDescription =null,
        contentScale = ContentScale.FillHeight, modifier = Modifier.alpha(.9f))
        Column {

            Spacer(modifier = Modifier.height(10.dp))
            maincontent()


        }
    }
}
@Composable
fun topheader(totalperperson:Double=133.434){
    val total="%.2f".format(totalperperson)
    Card(backgroundColor = Color(0xFFCCCEBD),modifier = Modifier
        .padding(20.dp)
        .height(150.dp)
        .width(350.dp)
        .alpha(1f), shape = RoundedCornerShape(corner = CornerSize(10.dp)), elevation = 4.dp) {
        Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(color = Color(0xFF1A1919),text = "Total per person", style = MaterialTheme.typography.h5)
            Text(color = Color(0xFF528533),text = "₹${total}",style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold)
        }
    }
}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun maincontent(){

        billform(){billAmt->


        }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun billform(

    modifier: Modifier =Modifier,
    onvalChange:(String)->Unit={}
             ){
    var totalbillState= remember {
        mutableStateOf("")
    }
    var validState= remember(totalbillState.value) {
        totalbillState.value.trim().isNotEmpty()
    }
    val sliderPositionState= remember {
        mutableStateOf(0f)
    }
    val tipPercantage=(sliderPositionState.value*100).toInt()
    var splitBystate= remember {
        mutableStateOf(1)
    }

    val tipAmountState= remember {
        mutableStateOf(0.0)
    }
    val totalPerPersonState= remember {
        mutableStateOf(0.0)
    }
    val keyboardController=LocalSoftwareKeyboardController.current;

    topheader(totalPerPersonState.value)

    Card(modifier = Modifier
        .padding(20.dp)
        .width(400.dp)
        .height(350.dp)
        .alpha(1f), shape = RoundedCornerShape(corner = CornerSize(9.dp)), elevation = 4.dp, backgroundColor = Color(
        0xFFDCDFC6
    )
    ) {
        Column() {
            Inputfield(valueState = totalbillState , labelId ="Enter Bill" , enabled =true , isSingleLine =true
                , onAction = KeyboardActions {
                    if(!validState) return@KeyboardActions
                    onvalChange(totalbillState.value.trim())
                    keyboardController?.hide()
                })

            if(validState){

                    Row(modifier=Modifier.padding(3.dp), horizontalArrangement = Arrangement.Start) {
                        Text(text = "Split", modifier = Modifier.padding(12.dp)  )
                        Spacer(modifier = Modifier.width(120.dp))
                        Row(horizontalArrangement = Arrangement.End) {
                                    button( onClick = {
                                                      splitBystate.value=if(splitBystate.value>1) splitBystate.value-1
                                        else 1
                                        totalPerPersonState.value= calculateTotalperPerson(totalbillState.value.toDouble(),splitBystate.value,tipPercantage)


                                    }, imageVector =Icons.Default.KeyboardArrowLeft)
                                    Text(text = splitBystate.value.toString(), modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(5.dp))
                                    button( onClick = { splitBystate.value++
                                        totalPerPersonState.value= calculateTotalperPerson(totalbillState.value.toDouble(),splitBystate.value,tipPercantage)

                                    }, imageVector =Icons.Default.KeyboardArrowRight)

                        }
                    }
                    Row(modifier=Modifier.padding(5.dp), horizontalArrangement = Arrangement.Start) {
                    Text(text = "Tip", modifier = Modifier.padding(12.dp)  )
                    Spacer(modifier = Modifier.width(159.dp))
                    Row(horizontalArrangement = Arrangement.End) {
                        Text(text = "₹${tipAmountState.value}", modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(15.dp))

                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "${tipPercantage}%")
                    Spacer(modifier = Modifier.height(14.dp))
                    Slider(value =sliderPositionState.value , onValueChange ={ newval->
                        sliderPositionState.value=newval
                        tipAmountState.value=calculateTotalTip(totalbillState.value.toDouble(),tipPercantage)

                        totalPerPersonState.value= calculateTotalperPerson(totalbillState.value.toDouble(),splitBystate.value,tipPercantage)

                    }, steps = 10, colors = SliderDefaults.colors(
                        thumbColor = Color.Black,
                        activeTrackColor = Color.Green,
                        inactiveTrackColor = Color.Red,
                        inactiveTickColor = Color.Gray,
                        activeTickColor = Color.Black
                    ))

                }

            }

            else{
                Box() {
                }
            }

        }
    }
}

fun calculateTotalTip(totalbill: Double, tipPercantage: Int): Double {
            return  if(totalbill>1 && totalbill.toString().isNotEmpty())
                (totalbill*tipPercantage)/100
                    else 0.0
}
fun calculateTotalperPerson(totalbill: Double,splitBY:Int,tipPercantage: Int): Double{
        val bill= calculateTotalTip(totalbill,tipPercantage)+totalbill
    return (bill/splitBY)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetTipTheme {
        myapp()
    }
}