import com.rooney.bean.Node
import com.rooney.logic.NearestNeighbour

object Main {
	def main(args: Array[String]) {
		val node1 = new Node("Chennai",1)
		val node2 = new Node("Kolkatta",16)
		val node3 = new Node("Delhi",18)
		val node4 = new Node("Mumbai",5)
		val list : List[Node] = List(node1, node2, node3, node4)
		val nn = new NearestNeighbour()
		nn.getTravelPath(list)
	}
}