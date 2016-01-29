var TSP = {
	findPath: function(nodes, nodeSelector) {
		var start_node = randomNodeSelector(undefined, nodes);
		return __travel__(nodes, randomNodeSelector, start_node);

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
			var i = nodes.indexOf(currentNode);
			nodes.splice(i, 1);
			return nodes;
		}
	}
}

function randomNodeSelector(currentNode, nodes) {
	var i = 0;
	var result;
	if (nodes == undefined || nodes.length == 0) {
		result = undefined;
	} else if (nodes.length == 1) {
		result = nodes[0];
	} else {
		result =  nodes[Math.floor((Math.random() * nodes.length))];
	}
	return result;
}
console.log();

function generateSample(number){
	var nodes = generateNodes(number);
	return nodes;
}

function generateNodes (number) {
	if(number < 1){
		return[];
	}
	return generateNodes(number-1).concat(String.fromCharCode(64 + number));
}

var data = generateSample(5);
console.log(data);
var result = Object.create(TSP).findPath(data);
console.log(result);