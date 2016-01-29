package practise;

import functioalInterfaces.DistanceFinder;

public class NodeSegment {
    private final Node node1;
    private final Node node2;
    private DistanceFinder<Node, Double> distanceBetweenTwoNodes = (node1, node2) -> {
        return Math.sqrt(Math.pow(node1.get_x_cordinate() - node2.get_x_cordinate(), 2)
                + Math.pow(node1.get_y_cordinate() - node2.get_y_cordinate(), 2));
    };

    public NodeSegment(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getFirstNode() {
        return node1;
    }

    public Node getSecondNode() {
        return node2;
    }

    public Double getDistance() {
        return distanceBetweenTwoNodes.find(node1, node2);
    }

}
