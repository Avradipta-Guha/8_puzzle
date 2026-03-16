package com.example.ai_games.screens.sliding_puzzle

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset
import com.example.ai_games.viewmodel.PuzzleViewModel
import kotlin.math.abs

@Composable
fun PuzzleBoard(viewModel: PuzzleViewModel) {

    val board by viewModel.board.collectAsState()
    val hintTile by viewModel.hintTile.collectAsState()

    val tileSize = 96.dp
    val spacing = 6.dp

    var totalDx by remember { mutableStateOf(0f) }
    var totalDy by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .size((tileSize * 3) + (spacing * 2))
            .pointerInput(Unit) {

                detectDragGestures(

                    onDragStart = {
                        totalDx = 0f
                        totalDy = 0f
                    },

                    onDrag = { change, dragAmount ->
                        totalDx += dragAmount.x
                        totalDy += dragAmount.y
                        change.consume()
                    },

                    onDragEnd = {

                        if (abs(totalDx) > abs(totalDy)) {

                            if (totalDx > 0)
                                viewModel.moveByDirection("RIGHT")
                            else
                                viewModel.moveByDirection("LEFT")

                        } else {

                            if (totalDy > 0)
                                viewModel.moveByDirection("DOWN")
                            else
                                viewModel.moveByDirection("UP")
                        }
                    }
                )
            }
    ) {

        board.forEachIndexed { index, tile ->

            val row = index / 3
            val col = index % 3

            Tile(
                number = tile,
                isHighlighted = hintTile == index,
                modifier = Modifier
                    .size(tileSize)
                    .offset(
                        x = (tileSize + spacing) * col,
                        y = (tileSize + spacing) * row
                    )
                    .pointerInput(Unit) {
                        detectTapGestures {
                            viewModel.moveTile(index)
                        }
                    }
            )
        }
    }
}