package practise;

import java.util.List;

public class TSP {

    private List<Node> nodeList;

    public TSP(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public List<Node> getSmallestPathUsingNNH() {
        NearestNeighbourHood nnh = new NearestNeighbourHood(nodeList);
        return nnh.start();
    }
}
