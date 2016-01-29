package com.main.java.NNAlgo;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.main.java.Node;

public class TestNearNeighbourAlgo {

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 58; i++) {
            Node node = new Node(i, i);
            nodes.add(node);
        }
       System.out.println(recursionFunction(nodes));
        
    }

    private static List<Node> recursionFunction(List<Node> allnodes) {
        Function<List<Node>, List<Node>> recursion = (nodes) -> {
            NearestNeighAlgo algo = new NearestNeighAlgo(nodes);
            return algo.getNearestNeighbours();
        };
        return recursion.apply(allnodes);

    }
}
