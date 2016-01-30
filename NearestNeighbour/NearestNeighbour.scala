package com.rooney.logic
class NearestNeighbour {

	import com.rooney.bean.Node

	def getTravelPath(nodesList_ : List[Node], currentNode_ :Node){
		var nodesList = nodesList_;
		var currentNode : Node = currentNode_;
		println("Travel Sales Person is going to start his/her Journey from: "+currentNode.name)
		nodesList = getFreeNodes(nodesList, currentNode)
		if(!nodesList.isEmpty) {
			getTravelPath(nodesList, getNodeWithMinDistance(nodesList, currentNode))
		} else {
			println("Time to go to TSP origin,NextCity:"+currentNode.name)	
		}
	}
	
	val getFreeNodes = (nodesList_ : List[Node], currentNode_ : Node) => 
		if(nodesList_.isEmpty) {
			Nil
		} else {
			nodesList_.filter(_.name != currentNode_.name)
		}

	def getNodeWithMinDistance(list : List[Node], currentNode : Node) :Node = {
		var minDistance : Double = -1
		var calculatedDistance : Double = 0
		var minNode : Node = null
		for (node : Node <- list) {
			calculatedDistance = Math.sqrt(Math.pow(node.x - currentNode.x, 2)+Math.pow(node.y - currentNode.y, 2));
			if (minDistance == -1 || minDistance > calculatedDistance) {
				minDistance = calculatedDistance;
				minNode = node;
			}
		}
		println("Minimun Distance to next Stop: "+minDistance+" , Next Stop:"+minNode.name)
		minNode
	}
}