package practise;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class TSPTest {

    private TSP tsp;

    @Test
    public void test_getSmallestPathUsingNNH() {
        Node node1 = new Node(4, 1, "A");
        Node node2 = new Node(3, 2, "B");
        Node node3 = new Node(0, 0, "C");
        Node node4 = new Node(4, 4, "D");
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        tsp = new TSP(nodeList);
        List<Node> nodes = tsp.getSmallestPathUsingNNH();
        assertEquals(nodes.get(2).getName(), node4.getName());
        assertEquals(nodes.get(3).getName(), node3.getName());
    }

}
