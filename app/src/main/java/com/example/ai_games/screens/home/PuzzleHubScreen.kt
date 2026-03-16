package com.example.ai_games.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PuzzleHubScreen(
    openSlidingPuzzle: () -> Unit,
    openSudoku: () -> Unit,
    openNQueens: () -> Unit,
    openWaterJug: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "AI Puzzle Lab",
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        PuzzleCard("Sliding Puzzle", openSlidingPuzzle)

        Spacer(modifier = Modifier.height(20.dp))

        PuzzleCard("Sudoku", openSudoku)

        Spacer(modifier = Modifier.height(20.dp))

        PuzzleCard("N Queens", openNQueens)

        Spacer(modifier = Modifier.height(20.dp))

        PuzzleCard("Water Jug", openWaterJug)
    }

}

@Composable
fun PuzzleCard(
    title: String,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(
                color = Color(0xFF1976D2),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}


