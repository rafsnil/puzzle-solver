package com.github.rafsnil.ps_backend.puzzle;

import com.github.rafsnil.ps_backend.tracker.State;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Maze implements Puzzle{

    private final int[][] maze;
    private final int[] start;
    private final int[] goal;
    private final int[] currentPosition;

    public Maze(char[][] charMaze) {
        // Convert char maze to int maze, find start and goal positions
        this.maze = new int[charMaze.length][charMaze[0].length];
        int[] start = null;
        int[] goal = null;

        for (int i = 0; i < charMaze.length; i++) {
            for (int j = 0; j < charMaze[i].length; j++) {
                if (charMaze[i][j] == 'S') {
                    start = new int[]{i, j};
                    maze[i][j] = 0; // Start position is an open path
                } else if (charMaze[i][j] == 'G') {
                    goal = new int[]{i, j};
                    maze[i][j] = 0; // Goal position is an open path
                } else if (charMaze[i][j] == '0') {
                    maze[i][j] = 0; // Open path
                } else {
                    maze[i][j] = 1; // Wall
                }
            }
        }

        if (start == null || goal == null) {
            throw new IllegalArgumentException("Maze must have a start (S) and goal (G) position.");
        }

        this.start = start;
        this.goal = goal;
        this.currentPosition = start;
    }

    @Override
    public boolean isGoalState() {
        return currentPosition[0] == goal[0] && currentPosition[1] == goal[1];
    }

    @Override
    public List<Puzzle> getSuccessors() {
        List<Puzzle> successors = new ArrayList<>();
        int[][] moves = {
                {0, -1}, {0, 1}, {-1, 0}, {1, 0} // left, right, up, down
        };

        for (int[] move : moves) {
            int newRow = currentPosition[0] + move[0];
            int newCol = currentPosition[1] + move[1];

            // Check if the new position is within the maze boundaries and is an open path (0)
            if (newRow >= 0 && newRow < maze.length && newCol >= 0 && newCol < maze[0].length && maze[newRow][newCol] == 0) {
                successors.add(new Maze(maze, start, goal, new int[]{newRow, newCol}));
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
        // Manhattan distance heuristic
        return Math.abs(currentPosition[0] - goal[0]) + Math.abs(currentPosition[1] - goal[1]);
    }

    @Override
    public State toState(State parent, int cost, int heuristic) {
        int[] stateRepresentation = new int[]{currentPosition[0], currentPosition[1]};
        return new State(stateRepresentation, parent, getCost(), getHeuristic(), this);
    }
}
