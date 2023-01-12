package com.github.Mindinventory.circularslider

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.Mindinventory.circularslider.ui.theme.DeepGray
import com.github.Mindinventory.circularslider.ui.theme.LightGray
import com.github.Mindinventory.circularslider.ui.theme.TextWhite
import com.github.Mindinventory.circularslider.util.CommonToolbar
import kotlin.math.roundToInt

@Composable
fun MiProjectPlanning() {
    val chatData = populateChatList()
    val weeklyUpdateData = populateWeeklyUpdateList()

    var currentPercentage by remember { mutableStateOf(0f) }
    var currentItem by remember { mutableStateOf(0f) }
    var maxNumber = 50

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepGray)
    ) {
        CommonToolbar.ToolbarHeaderSection(
            headerText = "Mi Project Planning",
            backGroundColor = LightGray
        )

        LazyColumn {
            items(chatData.size) {
                MessageCard(chatData[it])
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .padding(vertical = 10.dp, horizontal = 5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(
                            LightGray
                        ),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "Weekly Update",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 5.dp),
                        color = TextWhite,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 20.dp, bottom = 10.dp),
                        verticalAlignment = CenterVertically
                    ) {
                        itemsIndexed(weeklyUpdateData) { index, item ->
                            WeeklyUpdateItem(
                                weekDay = item.daysList,
                                staticProgress = item.staticProgress
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically
                ) {
                    Text(
                        text = "Update Progress",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 5.dp),
                        color = TextWhite,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    currentItem = maxNumber * currentPercentage / 100

                    CircularProgressBar(
                        radiusCircle = 50.dp,
                        percentageFontSize = 12.sp,
                        progressWidth = 20f,
                        thumbRadius = 14f,
                        tickColor = Color.White,
                        tickhighlightedColor = Color.Gray,
                        currentProgressToBeReturned = { currentPercentageReturned ->
                            currentPercentage = currentPercentageReturned
                        },
                        currentUpdatedValue = "${currentItem.roundToInt()}/$maxNumber",
                        maxNum = maxNumber,
                        onTouchEnabled = true,
                        onDragEnabled = true
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MessageCard(
    messageItem: ChatModel = ChatModel(
        userType = 0,
        chatImage = R.drawable.teamwork,
        chatText = "Planning and requirement analysis"
    )
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = when (messageItem.userType) {
            0 -> Alignment.Start
            1 -> Alignment.End
            else -> Alignment.Start
        },
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = cardShapeFor(messageItem),
            backgroundColor = when (messageItem.userType) {
                0 -> TextWhite
                1 -> LightGray
                else -> LightGray
            },
        ) {
            Row(
                modifier = Modifier
                    .width(250.dp)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)),
            ) {
                Image(
                    painter = painterResource(id = messageItem.chatImage),
                    contentDescription = "profileImage",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(vertical = 4.dp, horizontal = 5.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 5.dp)
                        .align(Alignment.CenterVertically),
                    text = messageItem.chatText,
                    color = when (messageItem.userType) {
                        0 -> LightGray
                        1 -> TextWhite
                        else -> TextWhite
                    },
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun cardShapeFor(messageItem: ChatModel): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)
    return when (messageItem.userType) {
        0 -> roundedCorners.copy(bottomEnd = CornerSize(0))
        else -> roundedCorners.copy(bottomStart = CornerSize(0))
    }
}

@Preview(showBackground = true)
@Composable
fun WeeklyUpdateItem(
    weekDay: String = "Monday", staticProgress: Float = 0f
) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .wrapContentSize()
            .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp)
        ) {
            CircularProgressBar(
                radiusCircle = 30.dp,
                percentageFontSize = 8.sp,
                progressWidth = 20f,
                thumbRadius = 10f,
                isDisabled = true,
                staticProgress = staticProgress,
                tickColor = Color.White,
                tickhighlightedColor = Color.Gray,
                currentProgressToBeReturned = { currentValueReturned ->

                },
//                currentUpdatedValue = currentValue
            )
        }
        Text(
            text = weekDay,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 5.dp),
            color = TextWhite,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}