package com.github.rafsnil.ps_backend.puzzle;

import com.github.rafsnil.ps_backend.tracker.State;

import java.util.List;

public interface Puzzle {
    boolean isGoalState();
    List<Puzzle> getSuccessors();
    int getCost();
    int getHeuristic();
    State toState(State parent, int cost, int heuristic);
}
