# ExtendedConnectX

A Java implementation of Connect Four with configurable board dimensions, player counts, and win conditions.

## Features

- **2–10 players**, each assigned a unique token character
- **Customizable board size**: 3–20 rows and 3–20 columns
- **Configurable win condition**: connect 3–25 tokens in a row to win
- **Two board implementations** selected automatically based on board size:
  - `GameBoard` — array-backed, fast for small boards
  - `GameBoardMem` — map-backed, memory-efficient for larger/sparse boards
- Graphical interface built with Java Swing

## Requirements

- Java Development Kit (JDK) 8 or later

## Build & Run

Compile all source files from the project root:

```bash
javac -d out src/cpsc2150/extendedConnectX/**/*.java
```

Launch the GUI:

```bash
java -cp out cpsc2150.extendedConnectX.ConnectXApp
```

Launch the console version:

```bash
java -cp out cpsc2150.extendedConnectX.GameScreen
```

## How to Play

1. On the setup screen, enter the number of rows, columns, players, and tokens needed to win.
2. Players take turns clicking the column arrow buttons (↓) to drop their token into that column.
3. Tokens fall to the lowest available row (gravity-based placement).
4. The first player to align the required number of tokens horizontally, vertically, or diagonally wins.
5. If all columns fill with no winner, the game ends in a tie.
6. After a win or tie, the board resets for a new game.

## Game Parameters

| Parameter      | Min | Max | Notes                                      |
|----------------|-----|-----|--------------------------------------------|
| Rows           | 3   | 20  |                                            |
| Columns        | 3   | 20  |                                            |
| Tokens to win  | 3   | 25  | Must not exceed the number of rows or columns |
| Players        | 2   | 10  |                                            |

## Architecture

The project follows the Model-View-Controller pattern:

```
ConnectXApp
├── SetupView / SetupController   — game configuration screen
└── ConnectXView / ConnectXController — game play screen
        └── IGameBoard
                ├── GameBoard       (2D char array)
                └── GameBoardMem    (Map<Character, List<BoardPosition>>)
```

- **`IGameBoard`** — interface defining board operations and game-rule constants
- **`AbsGameBoard`** — abstract base providing shared `toString()` rendering
- **`BoardPosition`** — value object representing a single (row, column) coordinate
- **`SetupController`** — validates input and picks the board implementation
- **`ConnectXController`** — manages turns, win/tie detection, and restart logic

## Testing

Unit tests for both board implementations are in `TestGameBoard.java` and `TestGameBoardMem.java`. Run them with JUnit 4 on your classpath:

```bash
javac -cp out:junit.jar -d out src/cpsc2150/extendedConnectX/models/TestGameBoard*.java
java  -cp out:junit.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.models.TestGameBoard
java  -cp out:junit.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.models.TestGameBoardMem
```
