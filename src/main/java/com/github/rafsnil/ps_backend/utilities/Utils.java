package com.github.rafsnil.ps_backend.utilities;

import com.github.rafsnil.ps_backend.dto.ResponseDTO;

import java.util.List;

public class Utils {
    public static void printResponseTableInTabularFormat(List<ResponseDTO> responses) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                              PERFORMANCE METRICS                             ");
        System.out.println("------------------------------------------------------------------------------");

        System.out.printf("%-20s %-15s %-10s %-20s %-10s%n", "AlgorithmUsed", "ExploredNodes", "MaxDepth", "ExecutionTime(ms)", "Cost");
        System.out.println("------------------------------------------------------------------------------");
        for (ResponseDTO response : responses) {
            System.out.printf("%-22s %-15d %-10d %-20d %-10d%n",
                    response.getAlgorithmUsed(),
                    response.getExpandedNodes(),
                    response.getMaxDepth(),
                    response.getExecutionTimeInMilliSeconds(),
                    response.getCost());
        }
    }
}
