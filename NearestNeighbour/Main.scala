import scala.io.StdIn
import com.rooney.bean.Node
import com.rooney.logic.NearestNeighbour

object Main {
	def main(args: Array[String]) {
		val node1 = new Node("Chennai",(10, 10))
		val node2 = new Node("Kolkatta",(16, 16))
		val node3 = new Node("Delhi",(12, 12))
		val node4 = new Node("Mumbai",(5, 5))
		val list : List[Node] = List(node1, node2, node3, node4)
		var startNode : Node = list.head;
		print("If you like to provide the start node Please enter the start Node:")
		val strInput = StdIn.readLine()
		if(strInput.length == 0) {
			println("No start node entered , so first node is selected as startNode"+startNode.name)
		} else {
			startNode = list.apply(strInput.toInt)
		}
		val nn = new NearestNeighbour()
		nn.getTravelPath(list, startNode)
	}
}