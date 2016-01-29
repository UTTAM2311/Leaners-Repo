function Graph(v) {
   this.vertices = v;
   this.visited = [];
   this.distance = []
   for (var i = 0; i < this.vertices; i++) {
      this.distance[i] = [];
      this.visited[i] = false;
   }
   this.showGraph = showGraph;
   this.populateDistance = populateDistance;
   this.tspOrder = [];
   this.travel = travel;
}

function populateDistance() {
   for (var i = 0; i < this.vertices; ++i) {
      for (var j = i+1; j < this.vertices; ++j) {
        this.distance[i][j] =  Math.floor(Math.random() * 10 + i*j);
        this.distance[j][i] = this. distance[i][j];
      }      
   }
}

function showGraph() {
   for (var i = 0; i < this.vertices; ++i) {
      var distMatrix = ""
      for (var j = 0; j < this.vertices; ++j) {
         distMatrix += this.distance[i][j] + " ";
      }
      console.log(distMatrix);
   }
}


function travel(start) {
   var i = 0;
   this.tspOrder[i++] = start;
   this.visited[start] = true;
   var done = false;
   var min;
   var minNode;
   while(!done) {
      min = 99999;
      done = true;
      for(var j=0;j<this.vertices;j++) {
         if(this.visited[j] == false) {
            done = false;
            if(start!=j && min > this.distance[start][j]) {
               minNode = j;
               min = this.distance[start][j]
            }
         }
      }
      if(!done) {
         this.visited[minNode] = true;
         start = minNode;
         this.tspOrder[i++] = minNode;
      }
      
   }
   console.log(this.tspOrder);
}

g = new Graph(5);
g.populateDistance();
g.showGraph();
g.travel(0);