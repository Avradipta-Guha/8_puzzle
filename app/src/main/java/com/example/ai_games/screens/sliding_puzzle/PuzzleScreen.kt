package com.example.ai_games.screens.sliding_puzzle

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ai_games.viewmodel.PuzzleViewModel
import kotlin.math.max

@Composable
fun PuzzleScreen(viewModel: PuzzleViewModel) {

    LaunchedEffect(Unit) {
        viewModel.startPuzzle("Random")
    }

    val moves by viewModel.currentMoves.collectAsState()
    val optimal by viewModel.optimalMoves.collectAsState()
    val heuristic by viewModel.heuristic.collectAsState()
    val depth by viewModel.searchDepth.collectAsState()
    val solved by viewModel.isSolved.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DifficultySelector(viewModel)

        Spacer(modifier = Modifier.height(24.dp))

        PuzzleBoard(viewModel)

        Spacer(modifier = Modifier.height(24.dp))

        Text("Optimal Moves: $optimal")
        Text("Current Moves: $moves")
        //Text("Remaining Estimated Moves: $optimal")
        Text("Search Depth: $depth")
        Text("Heuristic Value: $heuristic")

        if (solved) {
            Spacer(modifier = Modifier.height(12.dp))
            Text("Puzzle Solved!")
            Text("Efficiency Score: ${viewModel.efficiencyScore()}%")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            Button(onClick = { viewModel.hint() }) {
                Text("Hint")
            }

            Button(onClick = { viewModel.autoSolve() }) {
                Text("Auto Solve")
            }

            Button(onClick = { viewModel.startPuzzle("Random") }) {
                Text("Random")
            }
        }
    }
}