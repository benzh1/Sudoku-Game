# Sudoku

A terminal-based Sudoku game built in Java, featuring three difficulty levels and real-time input validation.

## Overview

This project implements a fully playable Sudoku game that runs in the console. The player is presented with a partially filled 9×9 grid and inputs moves via coordinate-based prompts. The game validates each entry against standard Sudoku rules — checking rows, columns, and 3×3 subgrids — before updating the board. Invalid moves are rejected without altering the grid state, and the game tracks remaining numbers to give the player a clear picture of progress.

## Features

- **Three difficulty levels** — Easy, Medium, and Hard, each reducing the number of pre-filled cells to increase the challenge
- **Coordinate-based input** — Players specify a column (x) then row (y) to target a specific cell
- **Real-time validation** — Each number entered is checked against Sudoku constraints before being placed
- **Progress tracking** — A "numbers remaining" display updates each turn to show which digits still need to be placed
- **Clean grid rendering** — The board is printed to the console with clear axis labels and boundary separators after each move

## How to Play

Run the game from the `Main` class. When the board is displayed, use the numbers along the top and sides as your coordinate reference.

At each turn you will be prompted to:
1. Enter the **x-coordinate** (column) of the cell you want to fill
2. Enter the **y-coordinate** (row) of the cell you want to fill
3. Enter the **number** (1–9) to place in that cell

If the number is valid for that position, the grid updates. If not, the board remains unchanged and you can try again.

Cells shown as `_` are empty and available to fill.

## Difficulty Levels

Difficulty is set by passing an integer argument to the `Grid` constructor in `Main.java`:

| Argument | Difficulty |
|----------|------------|
| `0`      | Easy       |
| `1`      | Medium     |
| `2`      | Hard       |

```java
// Example: start a Hard game
Grid grid = new Grid(2);
```

## Future Improvements

Add a GUI that represents the sudoku grid allowing for more intuitive gameplay
