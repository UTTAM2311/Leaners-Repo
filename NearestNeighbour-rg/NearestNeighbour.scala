package com.rooney.logic
class NearestNeighbour {

	import com.rooney.bean.Node

	def getTravelPath(list: List[Node]) = {
		var currentNode : Node = list.apply(0);
		var startNode : Node = list.apply(0);
		println("Travel Sales Person is going to start his/her Journey from: "+startNode.name)
		var freeNodesList : List[Node] = getFreeNodes(list, startNode)
		for (node : Node <- freeNodesList ) {
			var nextNode : Node = getNodeWithMinDistance(freeNodesList, currentNode)
			currentNode = nextNode
			freeNodesList = getFreeNodes(freeNodesList, currentNode)
		}
		println("Time to go to TSP origin,NextCity:"+startNode.name)
	}
	def getFreeNodes(list : List[Node], currentNode : Node) = {
		list.filter(_.name != currentNode.name);
	}

	def getNodeWithMinDistance(list : List[Node], currentNode : Node) :Node = {
		var minDistance : Int = 0
		var calculatedDistance : Int = 0
		var minNode : Node = null
		for (node : Node <- list) {
			calculatedDistance = node.position-currentNode.position;	
			if (minDistance == 0 || minDistance > calculatedDistance) {
				minDistance = calculatedDistance;
				minNode = node;
			}
		}
		println("Minimun Distance to next Stop: "+minDistance+" , Next Stop:"+minNode.name)
		minNode
	}
}