var TSP = {
	findPath: function(nodes, nodeSelector) {
		var start_node = randomNode(undefined, nodes);
		nodeSelector = nodeSelector ? nodeSelector : randomNode;
		return __travel__(nodes, nodeSelector, start_node);

		function __travel__(nodes, nodeSelector, currentNode) {
			var freeNodes = getFreeNodes(currentNode, nodes);
			var nextNode = nodeSelector(currentNode, freeNodes);
			if (freeNodes.length == 0) {
				return [currentNode];
			} else {
				return [currentNode].concat(__travel__(freeNodes, nodeSelector, nextNode));
			}

		}

		function getFreeNodes(currentNode, nodes) {
			var nodes = nodes.slice(0);
			var i = nodes.indexOf(currentNode);
			nodes.splice(i, 1);
			return nodes;
		}
	}
}

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

function nearestNeighbour(distances) {
	return function nearestNeighbour(currentNode, nodes) {
		if (nodes.length <= 1) {
			return nodes[0]
		}
		var nearest = nodes[0]
		var nearestDistance = distances[currentNode + nodes[0]];
		for (var i = 1; i < nodes.length; i++) {
			var d = distances[currentNode + nodes[i]]
			if (d < nearestDistance) {
				nearest = nodes[i];
				nearestDistance = d;
			}
		}
		return nearest;
	}
}


function random(maxBound) {
	return Math.floor((Math.random() * maxBound) + 1)
}

function generateSample(number) {
	var nodes = generateNodes(number);
	var distances = generateDistances(nodes);
	return [nodes, distances];
}

function generateNodes(number) {
	if (number < 1) {
		return [];
	}
	return generateNodes(number - 1).concat(String.fromCharCode(64 + number));
}

function reverse(s) {
	return s.split('').reverse().join('');
}

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