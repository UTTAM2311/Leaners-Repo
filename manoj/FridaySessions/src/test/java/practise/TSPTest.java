package practise;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class TSPTest {


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
        List<Node> nodes = NearestNeighbourHood.start(nodeList);
        assertEquals(nodeList.size(), nodes.size());
        assertNotEquals(nodeList, nodes);
        List<Node> nodes2 = NearestNeighbourHood.start(node1, nodeList);
        assertEquals(node1, nodes2.get(0));
    }

}
