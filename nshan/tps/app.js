var TSP = {
	findPath: function(nodes,  nodeSelector) {
		var start_node = randomNode(undefined, nodes);
		nodeSelector = nodeSelector ? nodeSelector : randomNode;
		return __travel__(nodes, nodeSelector, start_node);

		//Internal travel function.
		function __travel__(nodes, nodeSelector, currentNode) {
			var freeNodes = getFreeNodes(currentNode, nodes);
			var nextNode = nodeSelector(currentNode, freeNodes);
			if (freeNodes.length == 0) {
				return [currentNode];
			} else {
				return [currentNode].concat(__travel__(freeNodes, nodeSelector, nextNode));
			}

		}
		// return the free nodes by splicing the current node.
		function getFreeNodes(currentNode, nodes) {
			var nodes = nodes.slice(0);
			var i = nodes.indexOf(currentNode);
			nodes.splice(i, 1);
			return nodes;
		}
	}
}
/*
	Selecting node random
*/
function randomNode(currentNode, nodes) {
	var i = 0;
	var result;
	if (nodes == undefined || nodes.length == 0) {
		result = undefined;
	} else if (nodes.length == 1) {
		result = nodes[0];
	} else {
		result = nodes[random(nodes.length) - 1];
	}
	return result;
}

/*
	Return the nearest neighbour to the current node.
*/
function nearestNeighbour(distances) {
	return function __nearestNeighbour__(currentNode, nodes, nn) {
		if (nodes.length == 0) {
			return nn;
		}
		nn = getNearestNode(currentNode, nodes[0], nn);
		return __nearestNeighbour__(currentNode, nodes.slice(1), nn);		
	}

	function getNearestNode(currentNode, node1, node2){
		if(!node1){
			return node2;
		}
		if(!node2){
			return node1;
		}
		var d1 = distances[currentNode + node1];
		var d2 = distances[currentNode + node2];
		if(d1 < d2){
			return node1;
		}else{
			return node2;
		}
	}
}

//Random Function

function random(maxBound) {
	return Math.floor((Math.random() * maxBound) + 1)
}

//Generate sample.
function generateSample(number) {
	var nodes = generateNodes(number);
	var distances = generateDistances(nodes);
	return [nodes, distances];
}

// generate nodes.
function generateNodes(number) {
	if (number < 1) {
		return [];
	}
	return generateNodes(number - 1).concat(String.fromCharCode(64 + number));
}

function reverse(s) {
	return s.split('').reverse().join('');
}
// Simple logic to generate random distances.
function generateDistances(nodes) {
	var distances = {};
	for (var i = 0; i < nodes.length; i++) {
		for (var j = i + 1; j < nodes.length; j++) {
			var key = nodes[i] + nodes[j];
			var distance = random(10);
			distances[key] = distance;
			distances[reverse(key)] = distance;
		}
	}
	return distances;
}

var data = generateSample(5);
console.log(data);

var result = Object.create(TSP).findPath(data[0]);
console.log(result);

var result = Object.create(TSP).findPath(data[0], nearestNeighbour(data[1]));
console.log(result);