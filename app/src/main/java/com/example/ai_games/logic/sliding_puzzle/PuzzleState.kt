package com.example.ai_games.logic.sliding_puzzle

data class PuzzleState(
    val board: IntArray,
    val gCost: Int,
    val hCost: Int,
    val parent: PuzzleState?,
    val blankIndex: Int
) {

    val fCost: Int
        get() = gCost + hCost

    fun isGoal(): Boolean {
        return board.contentEquals(intArrayOf(1,2,3,4,5,6,7,8,0))
    }

    fun copyBoard(): IntArray {
        return board.copyOf()
    }

}