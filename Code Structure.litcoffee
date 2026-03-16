# Code Structure

The project is organized into layers separating UI, state management, and algorithm logic.

Directory Structure:

com.example.ai_games

MainActivity.kt

screens/
    sliding_puzzle/
        PuzzleScreen.kt
        PuzzleBoard.kt
        Tile.kt

viewmodel/
    PuzzleViewModel.kt

logic/
    PuzzleGenerator.kt

logic/sliding_puzzle/
    AStarSolver.kt
    ManhattanHeuristic.kt
    PuzzleState.kt
    SolveResult.kt

---

## MainActivity.kt

Entry point of the application.

Responsibilities:

• Initializes the application
• Loads the PuzzleScreen
• Creates the PuzzleViewModel

---

## screens/sliding_puzzle

Contains all UI components related to the puzzle.

### PuzzleScreen.kt

Main screen layout.

Responsibilities:

• Displays puzzle board
• Shows solver metrics
• Contains control buttons:
  - Hint
  - Auto Solve
  - Random puzzle

---

### PuzzleBoard.kt

Responsible for the grid layout.

Responsibilities:

• Renders tiles in 3×3 grid
• Handles swipe gestures
• Handles tile tap events
• Calculates tile position

---

### Tile.kt

Reusable UI component representing a single tile.

Responsibilities:

• Render tile number
• Display tile background
• Show hint highlighting
• Handle styling

---

## viewmodel

### PuzzleViewModel.kt

Handles the application state.

Responsibilities:

• Store puzzle board state
• Track moves
• Trigger AI solver
• Manage hint and autosolve
• Calculate efficiency score

State management is handled with:

StateFlow

---

## logic

### PuzzleGenerator.kt

Generates randomized puzzle boards.

Responsibilities:

• Create solvable board states
• Apply difficulty-based scrambling

---

## logic/sliding_puzzle

Contains AI algorithm implementation.

### AStarSolver.kt

Implements the A* search algorithm.

Responsibilities:

• Explore puzzle states
• Maintain priority queue
• Track visited states
• Return optimal solution path

---

### PuzzleState.kt

Represents nodes in the search tree.

Stores:

• Board configuration
• Parent node
• Depth
• Heuristic value

---

### ManhattanHeuristic.kt

Computes heuristic cost for A*.

Formula:

distance = |x1 - x2| + |y1 - y2|

---

### SolveResult.kt

Stores solver output:

• optimalMoves
• searchDepth
• solutionPath

---

## Data Flow

Application flow:

MainActivity
    ↓
PuzzleScreen
    ↓
PuzzleViewModel
    ↓
PuzzleGenerator
    ↓
AStarSolver

UI updates automatically through StateFlow observers.

---

## Summary

The project is divided into three major layers:

UI Layer – Jetpack Compose interface  
State Layer – ViewModel with reactive state management  
Logic Layer – AI algorithms and puzzle generation

This structure keeps the project modular and maintainable.