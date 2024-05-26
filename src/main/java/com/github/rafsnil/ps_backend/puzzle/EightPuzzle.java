package com.github.rafsnil.ps_backend.puzzle;

import com.github.rafsnil.ps_backend.tracker.State;

import java.util.ArrayList;
import java.util.List;

public class EightPuzzle implements Puzzle {

    private final int[]board;
    private final int emptyTileIndex;
    private static final int SIZE = 3;

    public EightPuzzle(int[] board) {
        this.board = board;
        this.emptyTileIndex = findEmptyTileIndex();
    }
    @Override
    public boolean isGoalState() {
        for (int i = 0; i < board.length - 1; i++) {
            if (board[i] != i + 1) {
                return false;
            }
        }
        return board[board.length - 1] == 0;
    }

    @Override
    public List<Puzzle> getSuccessors() {
        List<Puzzle> successors = new ArrayList<>();
        int[][] moves = {
                {0, -1}, {0, 1}, {-1, 0}, {1, 0} // left, right, up, down
        };

        int emptyRow = emptyTileIndex / SIZE;
        int emptyCol = emptyTileIndex % SIZE;

        for (int[] move : moves) {
            int newRow = emptyRow + move[0];
            int newCol = emptyCol + move[1];

            if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE) {
                int newEmptyTileIndex = newRow * SIZE + newCol;
                int[] newBoard = board.clone();
                newBoard[emptyTileIndex] = newBoard[newEmptyTileIndex];
                newBoard[newEmptyTileIndex] = 0;
                successors.add(new EightPuzzle(newBoard));
            }
        }
        return successors;
    }

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public int getHeuristic() {
        int manhattanDistance = 0;
        for(int i = 0; i < board.length; i++) {
            if(board[i] != 0) {
                int currentRow = i / SIZE;
                int currentColumn = i % SIZE;
                int targetRow = board[i] / SIZE;
                int targetCol = board[i] % SIZE;
                manhattanDistance += Math.abs(currentRow - targetRow) + Math.abs(currentColumn - targetCol);
            }
        }
        return manhattanDistance;
    }

    private int findEmptyTileIndex() {
        for(int i = 0; i < board.length; i++) {
            if(board[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public State toState(State parent, int cost, int heuristic) {
        return new State(board.clone(), parent, getCost(), getHeuristic(), this);
    }
}
