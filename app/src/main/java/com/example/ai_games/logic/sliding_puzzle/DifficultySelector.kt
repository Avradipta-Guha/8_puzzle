package com.example.ai_games.screens.sliding_puzzle

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ai_games.viewmodel.PuzzleViewModel

@Composable
fun DifficultySelector(viewModel: PuzzleViewModel) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        DifficultyButton("Easy") { viewModel.startPuzzle("Easy") }
        DifficultyButton("Medium") { viewModel.startPuzzle("Medium") }
        DifficultyButton("Hard") { viewModel.startPuzzle("Hard") }
        DifficultyButton("Expert") { viewModel.startPuzzle("Expert") }
    }
}

@Composable
fun DifficultyButton(text: String, onClick: () -> Unit) {

    Button(
        onClick = onClick,
        modifier = Modifier.height(40.dp)
    ) {
        Text(text)
    }
}