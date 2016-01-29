package practise;

import java.util.ArrayList;
import java.util.List;

import functioalInterfaces.StartNodeFinder;
import functioalInterfaces.FreeNodesFinder;
import functioalInterfaces.NearestNodeFinder;
import functioalInterfaces.ShortestDistanceFinder;

public class NearestNeighbourHood {

    private List<Node> nodes;
    private Node startNode = null;

    // Find smallest NodeSegemnt (smallest NodeSegment ---> NodeSegment with minimum distance
    // between two Nodes
    private ShortestDistanceFinder<NodeSegment> shortestDistanceFinder = (nodeSegments) -> {
        List<NodeSegment> nodeSegmentList = (List<NodeSegment>) nodeSegments;
        NodeSegment nodeSegment = nodeSegmentList.get(0);
        for (int i = 1; i < nodeSegments.size(); i++) {
            if (nodeSegment.getDistance() > nodeSegmentList.get(i).getDistance()) {
                nodeSegment = nodeSegmentList.get(i);
            }
        }
        return nodeSegment;
    };

    // Function to select a starting node if starting node is not specified
    private StartNodeFinder<Node, NodeSegment> nodeToStart = (nodes, shortestDistanceFinder) -> {
        List<NodeSegment> nodeSegementList = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            Node node1 = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                Node node2 = nodes.get(j);
                nodeSegementList.add(new NodeSegment(node1, node2));
            }
        }
        NodeSegment nodeSegment = shortestDistanceFinder.find(nodeSegementList);
        return nodeSegment.getFirstNode();
    };

    // Finds next nearest Node from the given Node
    private NearestNodeFinder<Node> nextNearestNode = (freeNodes, fromNode) -> {
        List<NodeSegment> nodeSegmentList = new ArrayList<>();
        for (int i = 0; i < freeNodes.size(); i++) {
            Node node = freeNodes.get(i);
            nodeSegmentList.add(new NodeSegment(fromNode, node));
        }
        NodeSegment nearstNodeSegment = shortestDistanceFinder.find(nodeSegmentList);
        return nearstNodeSegment.getSecondNode();
    };


    // Finds freeNodes --> unvisited nodes
    private FreeNodesFinder<Node> freeNodes = (nodes) -> {
        List<Node> freeNodesList = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node.isVisited() == false) {
                freeNodesList.add(node);
            }
        }
        return freeNodesList;
    };

    public NearestNeighbourHood(List<Node> nodes, Node nodeToStart) {
        this.nodes = nodes;
        this.startNode = nodeToStart;
    }

    public NearestNeighbourHood(List<Node> nodes) {
        this.nodes = nodes;
        this.startNode = null;
    }

    public List<Node> start() {
        List<Node> finalnodesList = new ArrayList<>();
        Node fromNode = null;
        if (startNode == null) {
            fromNode = nodeToStart.find(nodes, shortestDistanceFinder);
        } else {
            fromNode = startNode;
        }
        fromNode.setVisited(true);
        finalnodesList.add(fromNode);
        return findAllNodes(fromNode, finalnodesList);
    }

    private List<Node> findAllNodes(Node startNode, List<Node> nodeList) {
        List<Node> freeNodesList = freeNodes.find(nodes);
        if (freeNodesList.size() > 0) {
            Node fromNode = nextNearestNode.find(freeNodesList, startNode);
            fromNode.setVisited(true);
            nodeList.add(fromNode);
            findAllNodes(fromNode, nodeList);
        }
        return nodeList;

    }

}
