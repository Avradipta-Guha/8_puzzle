package com.example.ai_games.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_games.logic.*
import com.example.ai_games.logic.sliding_puzzle.AStarSolver
import com.example.ai_games.logic.sliding_puzzle.ManhattanHeuristic
//import com.example.ai_games.logic.sliding_puzzle.PuzzleGenerator
import com.example.ai_games.logic.PuzzleGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PuzzleViewModel : ViewModel() {

    private val solver = AStarSolver()

    private val _board = MutableStateFlow(IntArray(9))
    val board: StateFlow<IntArray> = _board

    private val _currentMoves = MutableStateFlow(0)
    val currentMoves: StateFlow<Int> = _currentMoves

    private val _optimalMoves = MutableStateFlow(0)
    val optimalMoves: StateFlow<Int> = _optimalMoves

    private val _heuristic = MutableStateFlow(0)
    val heuristic: StateFlow<Int> = _heuristic

    private val _searchDepth = MutableStateFlow(0)
    val searchDepth: StateFlow<Int> = _searchDepth

    private val _bestNextTile = MutableStateFlow<Int?>(null)
    val bestNextTile: StateFlow<Int?> = _bestNextTile

    private var solutionPath: List<IntArray> = emptyList()

    private val _hintTile = MutableStateFlow<Int?>(null)
    val hintTile: StateFlow<Int?> = _hintTile

    private val _isAutoSolving = MutableStateFlow(false)
    val isAutoSolving: StateFlow<Boolean> = _isAutoSolving

    private val goalState = intArrayOf(1,2,3,4,5,6,7,8,0)

    private val _isSolved = MutableStateFlow(false)
    val isSolved: StateFlow<Boolean> = _isSolved

    private val difficulties = listOf("Easy","Medium","Hard","Expert")
    private var currentDifficultyIndex = 0

    private val _initialOptimalMoves = MutableStateFlow(0)


    init {
        startPuzzle("Easy")
    }

    fun startPuzzle(level: String) {

        _board.value = PuzzleGenerator.generateForDifficulty(level)

        _currentMoves.value = 0
        _isSolved.value = false
        _hintTile.value = null

        solvePuzzle(_board.value)
    }

    private fun solvePuzzle(board: IntArray) {

        viewModelScope.launch(Dispatchers.Default) {

            val result = solver.solve(board)

            solutionPath = result.solutionPath

            _optimalMoves.value = result.optimalMoves
            _searchDepth.value = result.searchDepth

            if (_currentMoves.value == 0) {
                _initialOptimalMoves.value = result.optimalMoves
            }
            updateBestMove()
        }
    }

    fun moveTile(index: Int) {

        if (_isSolved.value) return

        val board = _board.value.copyOf()
        val blank = board.indexOf(0)

        if (!isValidMove(index, blank)) return

        board[blank] = board[index]
        board[index] = 0

        _board.value = board

        _currentMoves.value += 1
        _heuristic.value = ManhattanHeuristic.calculate(board)
        solvePuzzle(board)   // ADD THIS LINE


        updateBestMove()
        checkSolved(board)
        _hintTile.value = null

    }

    private fun updateBestMove() {

        val board = _board.value

        val next = solutionPath.getOrNull(_currentMoves.value + 1) ?: return

        for (i in board.indices) {
            if (board[i] != next[i] && board[i] != 0) {
                _bestNextTile.value = i
                return
            }
        }
    }

    fun hint() {
        val board = _board.value

        val next = solutionPath.getOrNull(_currentMoves.value + 1) ?: return

        for (i in board.indices) {
            if (board[i] != next[i] && board[i] != 0) {
                _hintTile.value = i
                return
            }
        }
    }

    private fun isValidMove(tile: Int, blank: Int): Boolean {

        val rowTile = tile / 3
        val colTile = tile % 3

        val rowBlank = blank / 3
        val colBlank = blank % 3

        return (kotlin.math.abs(rowTile - rowBlank) +
                kotlin.math.abs(colTile - colBlank)) == 1
    }

    fun autoSolve() {

        if (solutionPath.isEmpty()) return

        viewModelScope.launch {

            _isAutoSolving.value = true

            for (i in (_currentMoves.value + 1) until solutionPath.size) {

                val nextBoard = solutionPath[i]

                _board.value = nextBoard
                _currentMoves.value = i
                _heuristic.value = ManhattanHeuristic.calculate(nextBoard)

                delay(350)   // controls animation speed
            }

            _isAutoSolving.value = false
        }
    }

    fun moveByDirection(direction: String) {

        if (_isSolved.value) return

        val board = _board.value.copyOf()
        val blank = board.indexOf(0)

        val target = when (direction) {
            "UP" -> blank + 3
            "DOWN" -> blank - 3
            "LEFT" -> blank + 1
            "RIGHT" -> blank - 1
            else -> -1
        }

        if (target !in 0..8) return

        val rowBlank = blank / 3
        val colBlank = blank % 3
        val rowTarget = target / 3
        val colTarget = target % 3

        if (kotlin.math.abs(rowBlank - rowTarget) + kotlin.math.abs(colBlank - colTarget) != 1) return

        board[blank] = board[target]
        board[target] = 0

        _board.value = board
        _currentMoves.value += 1
        _heuristic.value = ManhattanHeuristic.calculate(board)

        solvePuzzle(board)   // ADD THIS LINE

        checkSolved(board)

        _hintTile.value = null
    }

    private fun checkSolved(board: IntArray) {
        if (board.contentEquals(goalState)) {
            _isSolved.value = true
        }
    }

    fun efficiencyScore(): Int {

        if (_currentMoves.value == 0) return 100

        val ratio = _initialOptimalMoves.value.toFloat() /
                _currentMoves.value.toFloat()

        return (ratio * 100).toInt().coerceAtMost(100)
    }

    fun startNextLevel() {
        val level = difficulties[currentDifficultyIndex]

        startPuzzle(level)

        if (currentDifficultyIndex < difficulties.lastIndex) {
            currentDifficultyIndex++
        }
    }
}