# AI Sliding Puzzle Lab

AI Sliding Puzzle Lab is an Android puzzle application built using **Kotlin and Jetpack Compose** that demonstrates the use of **Artificial Intelligence search algorithms** to solve the classic **3×3 Sliding Puzzle (8-Puzzle)**.

The application not only allows users to play the puzzle but also exposes internal algorithmic metrics such as heuristic values, optimal move counts, and search depth.

---

## Features

• Interactive **3×3 Sliding Puzzle**

• **Swipe and tap based tile movement**

• **AI-powered solving using A* Search Algorithm**

• Real-time metrics display:
  - Optimal Moves
  - Current Moves
  - Remaining Estimated Moves
  - Heuristic Value
  - Search Depth

• **Hint system** (shows best next tile)

• **Auto-solve feature**

• Multiple difficulty levels:
  - Easy
  - Medium
  - Hard
  - Expert

• Minimalist animated UI built with **Jetpack Compose**

---

## Tech Stack

Language: **Kotlin**

UI Framework: **Jetpack Compose**

Architecture: **MVVM**

Algorithm: **A* Search**

Heuristic: **Manhattan Distance**

Platform: **Android**

---

## How the Puzzle Works

The puzzle starts with a scrambled board.

The AI solver calculates the **optimal path to the goal state** using:
f(n) = g(n) + h(n)

Where:

- `g(n)` = cost so far (moves taken)
- `h(n)` = Manhattan distance heuristic

---

## Installation

Clone the repository:
git clone https://github.com/yourusername/ai-sliding-puzzle.git

Open the project in **Android Studio** and run on an emulator or Android device.

---

## Goal State
1 2 3
4 5 6
7 8 _
---

## Example Gameplay Metrics
Optimal Moves: 18
Current Moves: 7
Remaining Estimated Moves: 11
Search Depth: 18
Heuristic Value: 9

---

## Future Improvements

• 4×4 (15 Puzzle) support  
• Advanced heuristics (Linear Conflict)  
• Performance optimization with IDA*  
• Leaderboards  
• Timer mode  
• Multiplayer puzzle races  

---

## License

MIT License
