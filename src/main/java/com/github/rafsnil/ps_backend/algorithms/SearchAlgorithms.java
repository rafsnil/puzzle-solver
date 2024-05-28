package com.github.rafsnil.ps_backend.algorithms;

import com.github.rafsnil.ps_backend.constants.Algorithm;
import com.github.rafsnil.ps_backend.dto.ResponseDTO;
import com.github.rafsnil.ps_backend.puzzle.Puzzle;
import com.github.rafsnil.ps_backend.tracker.Node;
import com.github.rafsnil.ps_backend.tracker.State;

import java.util.*;

public class SearchAlgorithms {
    //BFS
    public ResponseDTO breadthFirstSearch(Puzzle puzzle) {
        Queue<Node> frontier = new LinkedList<>();
        Set<State> explored = new HashSet<>();
        long startTime = System.nanoTime();

        Node root = new Node(puzzle.toState(null, 0, 0), null);
        frontier.add(root);
        int expandedNodes = 0;
        int maxDepth = 0;
        int totalCost;

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            expandedNodes++;
            maxDepth = Math.max(maxDepth, node.getDepth());
            totalCost = expandedNodes;

            if (node.getState().getPuzzle().isGoalState()) {
                return ResponseDTO.builder()
                        .algorithmUsed(Algorithm.BFS.toString())
                        .solutionPath(constructPath(node))
                        .expandedNodes(expandedNodes)
                        .maxDepth(maxDepth)
                        .cost(totalCost)
                        .executionTimeInMilliSeconds(System.nanoTime() - startTime)
                        .build();
            }
            explored.add(node.getState());

            for (Puzzle successor : node.getState().getPuzzle().getSuccessors()) {
                Node child = new Node(successor.toState(node.getState(), node.getState().getCost() + 1, successor.getHeuristic()), node);
                if (!explored.contains(child.getState()) && !containsState(frontier, child.getState())) {
                    frontier.add(child);
                }
            }
        }
        return new ResponseDTO(Algorithm.BFS.toString(),Collections.emptyList(), expandedNodes, maxDepth, System.currentTimeMillis() - startTime, 0);
    }

    //DFS
    public ResponseDTO depthFirstSearch(Puzzle puzzle) {
        Stack<Node> frontier = new Stack<>();
        Set<State> explored = new HashSet<>();
        long startTime = System.nanoTime();

        Node root = new Node(puzzle.toState(null, 0, 0), null);
        frontier.push(root);
        int expandedNodes = 0;
        int maxDepth = 0;
        int totalCost;

        while (!frontier.isEmpty()) {
            Node node = frontier.pop();

            expandedNodes++;
            maxDepth = Math.max(maxDepth, node.getDepth());
            totalCost = expandedNodes;

            if (node.getState().getPuzzle().isGoalState()) {
                return ResponseDTO.builder()
                        .algorithmUsed(Algorithm.DFS.toString())
                        .solutionPath(constructPath(node))
                        .expandedNodes(expandedNodes)
                        .maxDepth(maxDepth)
                        .cost(totalCost)
                        .executionTimeInMilliSeconds(System.nanoTime() - startTime)
                        .build();
            }
            explored.add(node.getState());

            for (Puzzle successor : node.getState().getPuzzle().getSuccessors()) {
                Node child = new Node(successor.toState(node.getState(), node.getState().getCost() + 1, successor.getHeuristic()), node);
                if (!explored.contains(child.getState()) && !containsState(frontier, child.getState())) {
                    frontier.push(child);
                }
            }
        }
        return new ResponseDTO(Algorithm.DFS.toString(),Collections.emptyList(), expandedNodes, maxDepth, System.currentTimeMillis() - startTime, 0);
    }

    //A* Search
    public ResponseDTO aStarSearch(Puzzle puzzle) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.getState().getCost() + n.getState().getHeuristic()));
        Set<State> explored = new HashSet<>();
        long startTime = System.nanoTime();

        Node root = new Node(puzzle.toState(null, 0, 0), null);
        frontier.add(root);
        int expandedNodes = 0;
        int maxDepth = 0;
        int totalCost;

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            expandedNodes++;
            maxDepth = Math.max(maxDepth, node.getDepth());
            totalCost = expandedNodes;

            if (node.getState().getPuzzle().isGoalState()) {
                return ResponseDTO.builder()
                        .algorithmUsed(Algorithm.A_STAR.toString())
                        .solutionPath(constructPath(node))
                        .expandedNodes(expandedNodes)
                        .maxDepth(maxDepth)
                        .cost(totalCost)
                        .executionTimeInMilliSeconds(System.nanoTime() - startTime)
                        .build();
            }
            explored.add(node.getState());

            for (Puzzle successor : node.getState().getPuzzle().getSuccessors()) {
                Node child = new Node(successor.toState(node.getState(), node.getState().getCost() + 1, successor.getHeuristic()), node);
                if (!explored.contains(child.getState()) && !containsState(frontier, child.getState())) {
                    frontier.add(child);
                }
            }
        }
        return new ResponseDTO(Algorithm.A_STAR.toString(),Collections.emptyList(), expandedNodes, maxDepth, System.currentTimeMillis() - startTime, 0);
    }

    //Greedy Best First Search
    public ResponseDTO greedyBestFirstSearch(Puzzle puzzle) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.getState().getHeuristic()));
        Set<State> explored = new HashSet<>();

        Node root = new Node(puzzle.toState(null, 0, 0), null);
        frontier.add(root);
        int expandedNodes = 0;
        int maxDepth = 0;
        long startTime = System.nanoTime();
        int totalCost;

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            expandedNodes++;
            maxDepth = Math.max(maxDepth, node.getDepth());
            totalCost = expandedNodes;

            if (node.getState().getPuzzle().isGoalState()) {
                return ResponseDTO.builder()
                        .algorithmUsed(Algorithm.GREEDY_BEST_FIRST.toString())
                        .solutionPath(constructPath(node))
                        .expandedNodes(expandedNodes)
                        .maxDepth(maxDepth)
                        .cost(totalCost)
                        .executionTimeInMilliSeconds(System.nanoTime() - startTime)
                        .build();
            }
            explored.add(node.getState());

            for (Puzzle successor : node.getState().getPuzzle().getSuccessors()) {
                Node child = new Node(successor.toState(node.getState(), node.getState().getCost() + 1, successor.getHeuristic()), node);
                if (!explored.contains(child.getState()) && !containsState(frontier, child.getState())) {
                    frontier.add(child);
                }
            }
        }
        return new ResponseDTO(Algorithm.GREEDY_BEST_FIRST.toString(),Collections.emptyList(), expandedNodes, maxDepth, System.currentTimeMillis() - startTime, 0);
    }

    //Iterative Deepening Search
    public ResponseDTO iterativeDeepeningSearch(Puzzle puzzle) {
        long startTime = System.nanoTime();
        int maxDepthLimit = 1000;

        for (int depth = 0; depth <= maxDepthLimit; depth++) {
            Set<State> explored = new HashSet<>();
            List<State> result = depthLimitedSearch(new Node(puzzle.toState(null, 0, 0), null), depth, explored);
            if (!result.isEmpty()) {
                long executionTime = System.nanoTime() - startTime;
                int totalCost = result.size() - 1;

                return ResponseDTO.builder()
                        .algorithmUsed(Algorithm.ITERATIVE_DEEPENING.toString())
                        .solutionPath(result)
                        .expandedNodes(explored.size())
                        .maxDepth(depth)
                        .executionTimeInMilliSeconds(executionTime)
                        .cost(totalCost)
                        .build();
            }
        }

        long executionTime = System.currentTimeMillis() - startTime;
        return ResponseDTO.builder()
                .algorithmUsed(Algorithm.ITERATIVE_DEEPENING.toString())
                .solutionPath(Collections.emptyList())
                .expandedNodes(0)
                .maxDepth(maxDepthLimit)
                .executionTimeInMilliSeconds(executionTime)
                .cost(0)
                .build();
    }

    //Depth Limited Search
    private List<State> depthLimitedSearch(Node node, int depth, Set<State> explored) {
        if (depth == 0) {
            if (node.getState().getPuzzle().isGoalState()) {
                return constructPath(node);
            } else {
                return Collections.emptyList();
            }
        }
        explored.add(node.getState());

        Puzzle puzzle = node.getState().getPuzzle();
        for (Puzzle successor : puzzle.getSuccessors()) {
            Node child = new Node(successor.toState(node.getState(), node.getState().getCost() + 1, successor.getHeuristic()), node);
            if (!explored.contains(child.getState())) {
                List<State> result = depthLimitedSearch(child, depth - 1, explored);
                if (!result.isEmpty()) {
                    return result;
                }
            }
        }
        explored.remove(node.getState()); // Remove from explored if not fully expanded
        return Collections.emptyList();
    }

    private List<State> constructPath(Node node) {
        List<State> path = new ArrayList<>();
        while (node != null) {
            path.add(node.getState());
            node = node.getParent();
        }
        Collections.reverse(path);
        return path;
    }


    private boolean containsState(Collection<Node> frontier, State state) {
        for (Node node : frontier) {
            if (node.getState().equals(state)) {
                return true;
            }
        }
        return false;
    }


    //    public List<State> iterativeDeepeningSearch(Puzzle puzzle) {
//        for (int depth = 0; ; depth++) {
//            Set<State> explored = new HashSet<>();
//            List<State> result = depthLimitedSearch(new Node(puzzle.toState(null, 0, 0), null), depth, explored);
//            if (!result.isEmpty()) {
//                return result;
//            }
//        }
//    }
//
//    private List<State> depthLimitedSearch(Node node, int depth, Set<State> explored) {
//        if (depth == 0) {
//            if (node.getState().getPuzzle().isGoalState()) {
//                return constructPath(node);
//            } else {
//                return Collections.emptyList();
//            }
//        }
//        explored.add(node.getState());
//        boolean isCutoff = false;
//
//        Puzzle puzzle = node.getState().getPuzzle();
//        for (Puzzle successor : puzzle.getSuccessors()) {
//            Node child = new Node(successor.toState(node.getState(), node.getState().getCost() + 1, successor.getHeuristic()), node);
//            if (!explored.contains(child.getState())) {
//                List<State> result = depthLimitedSearch(child, depth - 1, explored);
//                if (!result.isEmpty()) {
//                    return result;
//                } else {
//                    isCutoff = true;
//                }
//            }
//        }
//        return isCutoff ? Collections.emptyList() : constructPath(node);
//    }
}
