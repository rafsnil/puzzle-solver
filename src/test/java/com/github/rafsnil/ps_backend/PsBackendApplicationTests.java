package com.github.rafsnil.ps_backend;

import com.github.rafsnil.ps_backend.algorithms.SearchAlgorithms;
import com.github.rafsnil.ps_backend.dto.ResponseDTO;
import com.github.rafsnil.ps_backend.puzzle.EightPuzzle;
import com.github.rafsnil.ps_backend.puzzle.Maze;
import com.github.rafsnil.ps_backend.tracker.State;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PsBackendApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testEightPuzzle() {
//		int[] initialBoard = {1, 2, 3, 5, 4, 7, 6, 8, 0};
//		int[] initialBoard = {1, 2, 3, 4, 5, 6, 7, 0, 8};
        int[] initialBoard = {1, 2, 3, 0, 5, 6, 4, 7, 8};
        // Create an instance of the 8-puzzle
        EightPuzzle puzzle = new EightPuzzle(initialBoard);

        // Create an instance of the search algorithms
        SearchAlgorithms algorithms = new SearchAlgorithms();

        // finds solution for the 8-puzzle using BFS
//		ResponseDTO solutionPath = algorithms.breadthFirstSearch(puzzle);
//     	List<State> solutionPath = algorithms.depthFirstSearch(puzzle);
//        ResponseDTO solutionPath = algorithms.aStarSearch(puzzle);
//        ResponseDTO solutionPath = algorithms.greedyBestFirstSearch(puzzle);
        ResponseDTO solution = algorithms.iterativeDeepeningSearch(puzzle);
        // Print the solution path

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                                       RESULTS                                                                  ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");


        System.out.println("Solution Path: " + solution.getSolutionPath());
        System.out.println("Number of Expanded Nodes: " + solution.getExpandedNodes());
        System.out.println("Depth Reached: " + solution.getMaxDepth());
        System.out.println("Execution Time: " + solution.getExecutionTimeInMilliSeconds() + "ms");
        System.out.println("Cost: " + solution.getCost());

    }


    @Test
    void testMaze() {
        char[][] charMaze = {
                {'S', '1', '0', '0', '0'},
                {'0', '1', '0', '1', '0'},
                {'0', '0', '0', '1', '0'},
                {'0', '1', '0', '0', '0'},
                {'0', '0', '0', '1', 'G'}
        };

        Maze maze = new Maze(charMaze);
        SearchAlgorithms algorithms = new SearchAlgorithms();

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                                       RESULTS                                                                  ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

//      ResponseDTO solution = algorithms.aStarSearch(maze)
//      ResponseDTO solution = algorithms.aStarSearch(maze);
        ResponseDTO solution = algorithms.aStarSearch(maze); // example for inefficient search

        System.out.println("Solution Path: " + solution.getSolutionPath());
        System.out.println("Number of Expanded Nodes: " + solution.getExpandedNodes());
        System.out.println("Depth Reached: " + solution.getMaxDepth());
        System.out.println("Execution Time: " + solution.getExecutionTimeInMilliSeconds() + "ms");
        System.out.println("Cost: " + solution.getCost());
    }

}
