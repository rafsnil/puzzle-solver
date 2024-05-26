package com.github.rafsnil.ps_backend.dto;

import com.github.rafsnil.ps_backend.tracker.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
public class ResponseDTO {
    List<State> solutionPath;
    int expandedNodes;
    int maxDepth;
    long executionTimeInMilliSeconds;
    int cost;
}
