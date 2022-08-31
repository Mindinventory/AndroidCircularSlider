package com.github.Mindinventory.circularslider.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.Mindinventory.circularslider.R

object CommonToolbar {
    @Composable
    fun ToolbarHeaderSection(headerText: String = "Home", isSettingVisible: Boolean = false, backGroundColor: Color = White) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = backGroundColor),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "menu",
                modifier = Modifier
                    .padding(10.dp)
                    .size(width = 30.dp, height = 30.dp)
                    .padding(2.dp)
            )
            Text(
                text = headerText,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 8.dp, end = 8.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                color = colorResource(id = R.color.white)
            )
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "menu",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(width = 30.dp, height = 30.dp)
                        .padding(2.dp)
                )
                if (isSettingVisible) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "menu",
                        modifier = Modifier
                            .padding(10.dp)
                            .size(width = 30.dp, height = 30.dp)
                            .padding(2.dp)
                    )
                }
            }
        }
    }
}