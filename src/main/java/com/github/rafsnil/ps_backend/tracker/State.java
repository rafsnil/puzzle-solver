package com.github.rafsnil.ps_backend.tracker;

import com.github.rafsnil.ps_backend.puzzle.Puzzle;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class State {
    private final int[] stateRepresentation;
    private final State parent;
    private final int cost;
    private final int heuristic;
    private final Puzzle puzzle;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return Arrays.equals(stateRepresentation, state.stateRepresentation);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(stateRepresentation);
    }

    @Override
    public String toString() {
        return Arrays.toString(stateRepresentation);
    }

}
