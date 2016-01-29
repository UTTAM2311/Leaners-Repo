
/**
 * Main closure
 */
var TSP = function (inNodesArray, inDistMatrix) {


    // local vars
    // ------------------------------------------------------------------------

    var nodesArray = inNodesArray || [];

    var distMatrix = inDistMatrix || [];
    var nodeIdxArray = range(0, inDistMatrix.length);


    // private functions
    // ------------------------------------------------------------------------

    // TODO is needed ?
    var getDistance = function (n1, n2) {
        return distMatrix[n1][n2];
    };

    var getFreeNodeDistances = function (node, toNodes) {

        // get all distances
        var allDist = distMatrix[node];

        // pick free nodes only
        var toNodeDists = [];
        for(var i = 0; 1 < toNodes.length; i++) {
            var toNodeIdx = toNodes[i];

            toNodeDists.push(allDist[toNodeDists]);
        }

        return toNodeDists;
    };

    var getNearestNeighbour = function(fromNode, freeNodes) {
        var allNodeDists = distMatrix[fromNode];
        var freeNodeDists = getFreeNodeDistances(fromNode, freeNodes);

        var minDist = Math.min.apply(Math, freeNodeDists);
        var nextNearest = allNodeDists.indexOf(minDist);

        console.log("Next nearest node : " + nextNearest);
        return nextNearest;
    };

    var getNextFreeNodes = function(freeNodes, currentNode) {

        console.log("free nodes : " + freeNodes);
        console.log("current node : " + currentNode);

        if(freeNodes && freeNodes.length > 0) {
            var index = freeNodes.indexOf(currentNode);
            if (index > -1) {
                return freeNodes.splice(index, 1);
            }
        }

        return freeNodes;
    };

    var pickRandomStartNode = function() {
        var min = 1;
        var max = distMatrix.length;
        return Math.floor(Math.random() * (max - min + 1)) + min;
    };


    // private utils
    // ------------------------------------------------------------------------

    function range(start, end) {
        var foo = [];
        for (var i = start; i <= end; i++) {
            foo.push(i);
        }
        return foo;
    }


    // public functions
    // ------------------------------------------------------------------------

    this.getNNsolutionRandom = function() {
        // random pick of startNode
        var startNode = pickRandomStartNode();

        return this.getNNsolution(startNode);
    };


    this.getNNsolution = function(startNode) {

        // recursively find next nodes (Tail Recursion)
        function _getNNsolution(currentNode, freeNodes, currentSol) {
            // add currentNode to solution
            currentSol.push(currentNode);

            // boundary condition
            if(freeNodes.length === 0) {
                return currentSol;
            }

            // apply nearest neighbour
            var nextFreeNodes = getNextFreeNodes(freeNodes, currentNode);
            var nextNearest = getNearestNeighbour(currentNode, nextFreeNodes);

            return _getNNsolution(nextNearest, nextFreeNodes, currentSol);
        }

        return _getNNsolution(startNode, nodeIdxArray,[]);
    };

};

// http://stackoverflow.com/questions/11007355/data-for-simple-tsp
/**
 *     | 1 , 2 , 3, 5, 9 |
 * MX= | 4 , 5 , 6, 5, 9 |     Dimensions= 5 x 5
 *     | 7 , 8 , 9, 5, 9 |
 *     | 7 , 8 , 9, 5, 9 |
 *     | 7 , 8 , 9, 5, 9 |
 */

var distances = [
    [0, 1, 2, 3, 5],
    [1, 0, 4, 3, 6],
    [2, 4, 0, 8, 3],
    [3, 3, 8, 0, 4],
    [5, 6, 3, 4, 0]
];

var nodes = ['A', 'B', 'C', 'D', 'E'];

var nearTsp = new TSP(nodes, distances);

var randomSol = nearTsp.getNNsolutionRandom();
console.log(randomSol);

var startNode = 3;
var solution = nearTsp.getNNsolution(startNode);
console.log(solution);
