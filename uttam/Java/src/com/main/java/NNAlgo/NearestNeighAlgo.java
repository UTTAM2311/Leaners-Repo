package com.main.java.NNAlgo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

import com.main.java.Distance;
import com.main.java.Node;

public class NearestNeighAlgo {

    private List<Node> nodes;

    public NearestNeighAlgo(List<Node> nodes) {
        this.nodes = nodes;
    }
    // Can we use Recursion Here.
    public List<Node> getNearestNeighbours() {
        List<Node> algoNodes = new ArrayList<>(nodes.size());
        List<Node> allnodes = nodes;
        System.out.println("AllNodes " + allnodes);
        Node selectNode = getRandomNode(allnodes);
        while (!allnodes.isEmpty()) {
            algoNodes.add(selectNode);
            System.out.println(selectNode.toString());
            allnodes = getFreeNodes.apply(selectNode, allnodes);
            if (allnodes.size() == 0) {
                break;
            }
            Distance minDis = getMinDistance.apply(selectNode, allnodes);
            selectNode = minDis.secondNode();
        }
        return algoNodes;

    }

    private BiFunction<Node, List<Node>, Distance> getMinDistance = (selectedNode, freeNodes) -> {
        Map<Distance, Double> distances = new HashMap<>();
        for (Node node : freeNodes) {
            Distance distance = new Distance(selectedNode, node);
            distances.put(distance, distance.getDistance());
        }
        if (distances.size() >= 1) {
            Double min = distances.values().stream().min((p1, p2) -> Double.compare(p1, p2)).get();
            for (Distance distance : distances.keySet()) {
                if (distances.get(distance).equals(min)) {
                    return distance;
                }
            }
        }
        return new Distance(selectedNode, freeNodes.get(0));
    };


    private BiFunction<Node, List<Node>, List<Node>> getFreeNodes = (node, all) -> {
        List<Node> freeNodes = all;
        if (freeNodes.remove(node)) {
            System.out.println("removed  node " + node);
            System.out.println(" free nodes " + freeNodes);
        }
        return freeNodes;
    };



    private Node getRandomNode(List<Node> nodes) {
        Random rand = new Random();
        Node node = nodes.get(rand.nextInt(nodes.size()));
        System.out.println(node.toString() + "random pick node");
        return node;
    }
}
