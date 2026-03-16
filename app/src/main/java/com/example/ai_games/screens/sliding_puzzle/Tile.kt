package com.example.ai_games.screens.sliding_puzzle

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tile(
    number: Int,
    isHighlighted: Boolean,
    modifier: Modifier = Modifier
) {

    if (number == 0) return

    Box(
        modifier = modifier
            .background(
                if (isHighlighted) Color(0xFF42A5F5) else Color(0xFF1976D2),
                shape = RoundedCornerShape(8.dp)
            )
            .border(2.dp, Color.Black, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = number.toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}