package com.github.rafsnil.ps_backend.algorithms;

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
        long startTime = System.currentTimeMillis();

        Node root = new Node(puzzle.toState(null, 0, 0), null);
        frontier.add(root);
        int expandedNodes = 0;
        int maxDepth = 0;
        int totalCost;

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            expandedNodes++;
            maxDepth = Math.max(maxDepth, node.getDepth());
            totalCost = node.getCost();

            if (node.getState().getPuzzle().isGoalState()) {
                return ResponseDTO.builder()
                        .solutionPath(constructPath(node))
                        .expandedNodes(expandedNodes)
                        .maxDepth(maxDepth)
                        .cost(totalCost)
                        .executionTimeInMilliSeconds(System.currentTimeMillis() - startTime)
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
        return new ResponseDTO(Collections.emptyList(), expandedNodes, maxDepth, System.currentTimeMillis() - startTime, 0);
    }

    //DFS
    public ResponseDTO depthFirstSearch(Puzzle puzzle) {
        Stack<Node> frontier = new Stack<>();
        Set<State> explored = new HashSet<>();
        long startTime = System.currentTimeMillis();

        Node root = new Node(puzzle.toState(null, 0, 0), null);
        frontier.push(root);
        int expandedNodes = 0;
        int maxDepth = 0;
        int totalCost;

        while (!frontier.isEmpty()) {
            Node node = frontier.pop();

            expandedNodes++;
            maxDepth = Math.max(maxDepth, node.getDepth());
            totalCost = node.getCost();

            if (node.getState().getPuzzle().isGoalState()) {
                return ResponseDTO.builder()
                        .solutionPath(constructPath(node))
                        .expandedNodes(expandedNodes)
                        .maxDepth(maxDepth)
                        .cost(totalCost)
                        .executionTimeInMilliSeconds(System.currentTimeMillis() - startTime)
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
        return new ResponseDTO(Collections.emptyList(), expandedNodes, maxDepth, System.currentTimeMillis() - startTime, 0);
    }

    //A* Search
    public ResponseDTO aStarSearch(Puzzle puzzle) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.getState().getCost() + n.getState().getHeuristic()));
        Set<State> explored = new HashSet<>();
        long startTime = System.currentTimeMillis();

        Node root = new Node(puzzle.toState(null, 0, 0), null);
        frontier.add(root);
        int expandedNodes = 0;
        int maxDepth = 0;
        int totalCost;

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            expandedNodes++;
            maxDepth = Math.max(maxDepth, node.getDepth());
            totalCost = node.getCost();

            if (node.getState().getPuzzle().isGoalState()) {
                return ResponseDTO.builder()
                        .solutionPath(constructPath(node))
                        .expandedNodes(expandedNodes)
                        .maxDepth(maxDepth)
                        .cost(totalCost)
                        .executionTimeInMilliSeconds(System.currentTimeMillis() - startTime)
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
        return new ResponseDTO(Collections.emptyList(), expandedNodes, maxDepth, System.currentTimeMillis() - startTime, 0);
    }

    //Greedy Best First Search
    public ResponseDTO greedyBestFirstSearch(Puzzle puzzle) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.getState().getHeuristic()));
        Set<State> explored = new HashSet<>();

        Node root = new Node(puzzle.toState(null, 0, 0), null);
        frontier.add(root);
        int expandedNodes = 0;
        int maxDepth = 0;
        long startTime = System.currentTimeMillis();
        int totalCost;

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            expandedNodes++;
            maxDepth = Math.max(maxDepth, node.getDepth());
            totalCost = node.getCost();

            if (node.getState().getPuzzle().isGoalState()) {
                return ResponseDTO.builder()
                        .solutionPath(constructPath(node))
                        .expandedNodes(expandedNodes)
                        .maxDepth(maxDepth)
                        .cost(totalCost)
                        .executionTimeInMilliSeconds(System.currentTimeMillis() - startTime)
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
        return new ResponseDTO(Collections.emptyList(), expandedNodes, maxDepth, System.currentTimeMillis() - startTime, 0);
    }

    //Iterative Deepening Search
    public ResponseDTO iterativeDeepeningSearch(Puzzle puzzle) {
        long startTime = System.currentTimeMillis(); // Start the timer

        for (int depth = 0; ; depth++) {
            Set<State> explored = new HashSet<>();
            List<State> result = depthLimitedSearch(new Node(puzzle.toState(null, 0, 0), null), depth, explored);
            if (!result.isEmpty()) {
                long executionTime = System.currentTimeMillis() - startTime;
                int totalCost = result.get(result.size() - 1).getCost();

                return ResponseDTO.builder()
                        .solutionPath(result)
                        .expandedNodes(explored.size())
                        .maxDepth(depth)
                        .executionTimeInMilliSeconds(executionTime)
                        .cost(totalCost)
                        .build();
            }
        }
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
        boolean isCutoff = false;

        Puzzle puzzle = node.getState().getPuzzle();
        for (Puzzle successor : puzzle.getSuccessors()) {
            Node child = new Node(successor.toState(node.getState(), node.getState().getCost() + 1, successor.getHeuristic()), node);
            if (!explored.contains(child.getState())) {
                List<State> result = depthLimitedSearch(child, depth - 1, explored);
                if (!result.isEmpty()) {
                    return result;
                } else {
                    isCutoff = true;
                }
            }
        }
        return isCutoff ? Collections.emptyList() : constructPath(node);
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

    private boolean containsState(Collection<Node> frontier, State state) {
        for (Node node : frontier) {
            if (node.getState().equals(state)) {
                return true;
            }
        }
        return false;
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
}
