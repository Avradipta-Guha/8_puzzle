package com.example.ai_games.logic.sliding_puzzle

import kotlin.math.abs

object ManhattanHeuristic {

    fun calculate(board: IntArray): Int {
        var distance = 0

        for (i in board.indices) {
            val value = board[i]

            if (value == 0) continue

            val targetRow = (value - 1) / 3
            val targetCol = (value - 1) % 3

            val currentRow = i / 3
            val currentCol = i % 3

            distance += abs(currentRow - targetRow) +
                    abs(currentCol - targetCol)
        }

        return distance
    }
}