package practise;

import java.util.ArrayList;
import java.util.List;

public class T {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Node node1 = new Node(4, 1, "A");
        Node node2 = new Node(3, 2, "B");
        Node node3 = new Node(0, 0, "C");
        Node node4 = new Node(4, 4, "D");
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        TSP tsp = new TSP(nodeList);
        System.out.println(tsp.getSmallestPathUsingNNH());
    }

}
