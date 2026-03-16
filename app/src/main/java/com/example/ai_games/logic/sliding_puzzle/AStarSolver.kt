package com.example.ai_games.logic.sliding_puzzle

import com.example.ai_games.logic.sliding_puzzle.ManhattanHeuristic
import com.example.ai_games.logic.sliding_puzzle.PuzzleState
import java.util.PriorityQueue

class AStarSolver {

    data class Result(
        val solutionPath: List<IntArray>,
        val optimalMoves: Int,
        val nodesExpanded: Int,
        val searchDepth: Int
    )

    fun solve(startBoard: IntArray): Result {

        val goal = intArrayOf(1,2,3,4,5,6,7,8,0)

        val openSet = PriorityQueue<PuzzleState>(compareBy { it.fCost })
        val visited = HashSet<String>()

        val startState = PuzzleState(
            board = startBoard,
            gCost = 0,
            hCost = ManhattanHeuristic.calculate(startBoard),
            parent = null,
            blankIndex = startBoard.indexOf(0)
        )

        openSet.add(startState)

        var nodesExpanded = 0

        while (openSet.isNotEmpty()) {

            val current = openSet.poll()
            nodesExpanded++

            if (current.board.contentEquals(goal)) {
                val path = reconstructPath(current)

                return Result(
                    solutionPath = path,
                    optimalMoves = path.size - 1,
                    nodesExpanded = nodesExpanded,
                    searchDepth = current.gCost
                )
            }

            visited.add(current.board.joinToString(","))

            val neighbors = generateNeighbors(current)

            for (neighbor in neighbors) {

                val key = neighbor.board.joinToString(",")

                if (visited.contains(key)) continue

                openSet.add(neighbor)
            }
        }

        throw IllegalStateException("No solution found")
    }

    private fun reconstructPath(state: PuzzleState): List<IntArray> {
        val path = mutableListOf<IntArray>()
        var current: PuzzleState? = state

        while (current != null) {
            path.add(current.board)
            current = current.parent
        }

        return path.reversed()
    }

    private fun generateNeighbors(state: PuzzleState): List<PuzzleState> {

        val neighbors = mutableListOf<PuzzleState>()

        val blank = state.blankIndex

        val moves = listOf(-3, 3, -1, 1)

        for (move in moves) {

            val newIndex = blank + move

            if (!isValidMove(blank, newIndex)) continue

            val newBoard = state.copyBoard()

            newBoard[blank] = newBoard[newIndex]
            newBoard[newIndex] = 0

            val h = ManhattanHeuristic.calculate(newBoard)

            neighbors.add(
                PuzzleState(
                    board = newBoard,
                    gCost = state.gCost + 1,
                    hCost = h,
                    parent = state,
                    blankIndex = newIndex
                )
            )
        }

        return neighbors
    }

    private fun isValidMove(blank: Int, newIndex: Int): Boolean {

        if (newIndex !in 0..8) return false

        if (blank % 3 == 0 && newIndex == blank - 1) return false
        if (blank % 3 == 2 && newIndex == blank + 1) return false

        return true
    }
}