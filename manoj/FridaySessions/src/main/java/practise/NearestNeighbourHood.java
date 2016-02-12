package practise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import functioalInterfaces.FreeNodesFinder;
import functioalInterfaces.NearestNodeFinder;
import functioalInterfaces.ShortestDistanceFinder;
import functioalInterfaces.StartNodeFinder;

public class NearestNeighbourHood {

    // Find smallest NodeSegemnt (smallest NodeSegment ---> NodeSegment with minimum distance
    // between two Nodes
    private static ShortestDistanceFinder<NodeSegment> shortestDistanceFinder = (nodeSegments) -> {
        List<NodeSegment> nodeSegmentList = (List<NodeSegment>) nodeSegments;
        NodeSegment nodeSegment = nodeSegmentList.stream().reduce((nodeSegemnt1,
                nodeSegment2) -> nodeSegemnt1.getDistance() > nodeSegment2.getDistance() ? nodeSegment2 : nodeSegemnt1)
                .get();
        return nodeSegment;
    };

    // Function to select a starting node if starting node is not specified
    private static StartNodeFinder<Node> nodeToStart = (nodes) -> {
        Random rand = new Random();
        int index = rand.nextInt(nodes.size() - 1);
        return nodes.get(index);
    };

    // Finds next nearest Node from the given Node
    private static NearestNodeFinder<Node> nextNearestNode = (freeNodes, fromNode) -> {
        List<NodeSegment> nodeSegmentList =
                freeNodes.stream().map(node -> new NodeSegment(fromNode, node)).collect(Collectors.toList());
        NodeSegment nearstNodeSegment = shortestDistanceFinder.find(nodeSegmentList);
        return nearstNodeSegment.getSecondNode();
    };


    // Finds freeNodes --> unvisited nodes
    private static FreeNodesFinder<Node, Integer> freeNodes = (nodes, indexes) -> {
        List<Node> freeNodesList = new ArrayList<Node>();
        List<Integer> indexList = IntStream.rangeClosed(0, nodes.size() - 1).boxed().collect(Collectors.toList());
        List<Integer> freeIndexes =
                indexList.stream().filter(index -> !indexes.contains(index)).collect(Collectors.toList());
        freeNodesList = freeIndexes.stream().map(index -> nodes.get(index)).collect(Collectors.toList());
        return freeNodesList;
    };

    public static List<Node> start(List<Node> inputNodes) {
        Node startNode = nodeToStart.find(inputNodes);
        return start(startNode, inputNodes);
    }

    public static List<Node> start(Node startNode, List<Node> inputNodes) {
        List<Node> finalnodesList = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        indexes.add(inputNodes.indexOf(startNode));
        finalnodesList.add(startNode);
        return findAllNodes(startNode, inputNodes, finalnodesList, indexes);
    }

    private static List<Node> findAllNodes(Node startNode, List<Node> nodes, List<Node> finalNodeList,
            List<Integer> indexes) {
        List<Node> freeNodesList = freeNodes.find(nodes, indexes);
        if (freeNodesList.size() > 0) {
            Node fromNode = nextNearestNode.find(freeNodesList, startNode);
            indexes.add(nodes.indexOf(fromNode));
            finalNodeList.add(fromNode);
            findAllNodes(fromNode, nodes, finalNodeList, indexes);
        }
        return finalNodeList;
    }
}
