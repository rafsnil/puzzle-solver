package com.github.rafsnil.ps_backend.tracker;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Node {
    private State state;
    private Node parent;
    private List<Node> children;
    private int depth;
    private int cost;

    public Node(State state, Node parent) {
        this.state = state;
        this.parent = parent;
        this.children = new ArrayList<>();
        this.depth = parent == null ? 0 : parent.getDepth() + 1;
        this.cost = parent == null ? 0 : parent.getCost() + state.getPuzzle().getCost();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}
