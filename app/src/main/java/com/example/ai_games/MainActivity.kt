package com.example.ai_games

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.example.ai_games.screens.sliding_puzzle.PuzzleScreen
import com.example.ai_games.viewmodel.PuzzleViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val viewModel = remember { PuzzleViewModel() }

            PuzzleScreen(viewModel)

        }
    }
}
