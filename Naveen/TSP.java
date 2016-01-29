import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */

/**
 * @author naveens
 *
 */
public class TSP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<String> nodes = new ArrayList<String>() {
			{
				add("0");
				add("1");
				add("2");
				add("3");
				add("4");
			}
		};

		HashMap<String, Integer> weights = new HashMap<String, Integer>() {
			{
				put("01", 9);
				put("02", 1);
				put("03", 54);
				put("04", 54);
				put("12", 23);
				put("13", 23);
				put("14", 8);
				put("23", 9);
				put("24", 80);
				put("34", 2);
			}
		};

		String currentNode = "0";
		String path = tsp_nearest_neighbor(nodes, weights, currentNode);
		System.out.println(path);

	}

	private static String tsp_nearest_neighbor(List<String> nodes, Map<String, Integer> weights, String currentNode) {

		String path = "";
		List<String> unVisitedNodes = updateUnvistedNodes(nodes, currentNode);
		if (unVisitedNodes == null || unVisitedNodes.size() == 0)
			path = currentNode;
		else {
			String nearestNode = findNearestNeighbor(unVisitedNodes, weights, currentNode);
			path = currentNode + "-->" + tsp_nearest_neighbor(unVisitedNodes, weights, nearestNode);
		}

		return path;

	}

	private static List<String> updateUnvistedNodes(List<String> nodes, String nearestNode) {
		nodes.removeIf(p -> p.equalsIgnoreCase(nearestNode));
		return nodes;
	}

	private static String findNearestNeighbor(List<String> unVisitedNodes, Map<String, Integer> weights,
			String currentNode) {
		int nearestNodeWeight = -1;
		String nearestNeighbor = "";

		for (String node : unVisitedNodes) {
			String edge = currentNode + node;
			if (weights.get(edge) == null) {
				edge = node + currentNode;
			}

			if (nearestNodeWeight == -1 || nearestNodeWeight > weights.get(edge)){
				nearestNeighbor = node;
				nearestNodeWeight=weights.get(edge);
				
			}
				

		}

		return nearestNeighbor;
	}

}
