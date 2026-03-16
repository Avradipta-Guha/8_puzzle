package com.example.ai_games.logic

import kotlin.random.Random

object PuzzleGenerator {

    private val goal = intArrayOf(1,2,3,4,5,6,7,8,0)

    fun generateForDifficulty(level: String): IntArray {

        val depth = when(level) {
            "Easy" -> Random.nextInt(10,15)
            "Medium" -> Random.nextInt(16,23)
            "Hard" -> Random.nextInt(24,30)
            "Expert" -> Random.nextInt(30,36)
            else -> 15
        }

        return scrambleBoard(depth)
    }

    private fun scrambleBoard(moves: Int): IntArray {

        val board = goal.copyOf()

        var blank = 8
        var lastBlank = -1

        repeat(moves) {

            val neighbors = mutableListOf<Int>()

            if (blank - 3 >= 0) neighbors.add(blank - 3)
            if (blank + 3 <= 8) neighbors.add(blank + 3)
            if (blank % 3 != 0) neighbors.add(blank - 1)
            if (blank % 3 != 2) neighbors.add(blank + 1)

            neighbors.remove(lastBlank)

            val next = neighbors.random()

            board[blank] = board[next]
            board[next] = 0

            lastBlank = blank
            blank = next
        }

        return board
    }
}