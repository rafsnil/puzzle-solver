package com.github.rafsnil.ps_backend;

import com.github.rafsnil.ps_backend.algorithms.SearchAlgorithms;
import com.github.rafsnil.ps_backend.dto.ResponseDTO;
import com.github.rafsnil.ps_backend.puzzle.EightPuzzle;
import com.github.rafsnil.ps_backend.puzzle.Maze;
import com.github.rafsnil.ps_backend.utilities.Utils;
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
		int[] initialBoard = {1, 2, 3, 4, 5, 6, 7, 0, 8};
//        int[] initialBoard = {1, 2, 3, 0, 5, 6, 4, 7, 8}; // takes crazy amount of time to generate results for all the algo

        EightPuzzle puzzle = new EightPuzzle(initialBoard);

        SearchAlgorithms algorithms = new SearchAlgorithms();

		ResponseDTO solution1 = algorithms.breadthFirstSearch(puzzle);
     	ResponseDTO solution2 = algorithms.depthFirstSearch(puzzle);
        ResponseDTO solution3 = algorithms.aStarSearch(puzzle);
        ResponseDTO solution4 = algorithms.greedyBestFirstSearch(puzzle);
        ResponseDTO solution5 = algorithms.iterativeDeepeningSearch(puzzle);

        List<ResponseDTO> responses = List.of(
                solution1,
                solution2,
                solution3,
                solution4,
                solution5
        );

        Utils.printResponseTableInTabularFormat(responses);

    }


    @Test
    void testMaze() {
//        char[][] charMaze = {
//                {'S', '0', '1', '0', 'G'},
//                {'0', '1', '0', '0', '0'},
//                {'0', '0', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '1', '0'}
//        };

        char[][] charMaze = {
                {'S', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0'},
                {'0', '1', '0', '0', '0', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '0', '1', '0', '0', '0', '0', '0', '0', '1'},
                {'0', '1', '0', '1', '0', '1', '1', '1', '1', '0', '1'},
                {'0', '0', '0', '1', '0', '1', '0', '0', '1', '0', '1'},
                {'1', '1', '1', '1', '0', '1', '0', '1', '1', '0', '1'},
                {'0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '1'},
                {'0', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '0', '0', '0', '0', '0', '0', '0', '0', 'G'}
        };

//        char[][] charMaze = {
//                {'S', '1', '1', '1', '1', '1', '1', '1', '1', '0', 'G'},
//                {'0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1'},
//                {'1', '1', '1', '0', '1', '0', '1', '0', '1', '0', '1'},
//                {'1', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1'},
//                {'1', '0', '1', '0', '1', '0', '1', '0', '1', '0', '0'},
//                {'1', '0', '1', '0', '0', '0', '0', '0', '1', '1', '1'},
//                {'1', '0', '1', '1', '1', '1', '1', '0', '0', '0', '0'},
//                {'1', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1'},
//                {'1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0'}
//        };


        Maze maze2 = new Maze(charMaze);
        SearchAlgorithms algorithms = new SearchAlgorithms();

        ResponseDTO solution1 = algorithms.breadthFirstSearch(maze2);
        ResponseDTO solution2 = algorithms.depthFirstSearch(maze2);
        ResponseDTO solution3 = algorithms.aStarSearch(maze2);
        ResponseDTO solution4 = algorithms.greedyBestFirstSearch(maze2);
        ResponseDTO solution5 = algorithms.iterativeDeepeningSearch(maze2);

        List<ResponseDTO> responses = List.of(
                solution1,
                solution2,
                solution3,
                solution4,
                solution5
        );

        Utils.printResponseTableInTabularFormat(responses);

    }

}
