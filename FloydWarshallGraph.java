import java.util.*;

// A Graph Node
class GraphNode{
    String name;
    
    // Neigbours list
    ArrayList<GraphNode> neighbours;
    // edge (to neighbours) weight map
    HashMap<GraphNode,Integer> weightMap;
    
    // Constructor
    GraphNode(String name){
        this.name = name;
        this.neighbours = new ArrayList<>();
        this.weightMap = new HashMap<>();
    }
}

public class FloydWarshallGraph {
    
    //List of Nodes in the graph
    ArrayList<GraphNode> nodesList;
    
    // Constructor
    FloydWarshallGraph(){
        this.nodesList = new ArrayList<>();
    }
    
    // Method to find All Pairs Shortest Path using Floyd Warshall
    public void floydWarshall(){
        
        int size = this.nodesList.size();
        // Making an adjacency list
        int[][] distanceMatix = new int[size][size];
        
        // for every pair of vertices initialize the distanceMatix
        for(int i=0;i<size;i++){
            GraphNode source = this.nodesList.get(i);
            for(int j=0;j<size;j++){
                GraphNode destination = this.nodesList.get(j);
                // if same vertex
                if(i==j){
                    distanceMatix[i][j]=0;
                }
                // if there is a direct edge from source vertex to destination vertex
                else if(source.weightMap.containsKey(destination)){
                    distanceMatix[i][j] = source.weightMap.get(destination);
                }
                // if there is no direct edge from source vertexx to destination node
                else{
                    // marking it as Integer.MAX_VALUE divided by 10 to avoid arithmetic overflow
                    distanceMatix[i][j] = Integer.MAX_VALUE/10;
                }
            }
        }
        
        // for every pair of vertices if the distance from i to j (distanceMatix[i][j]) is greater than distance from i to j through any intermediate vertex 'k', we update the distance
        // distanceMatix[i][j] -> distance from source vertex 'i' to destination vertex 'j'
        // distanceMatix[i][k] -> distance from source vertex 'i' to intermediate vertex 'k'
        // distanceMatix[k][j] -> distance from intermediate vertex 'k' to destination vertex 'j'
        // THE USE OF THIS FUNCTION IS ONLY TO CHECK THAT WHETHER THERE IS A NON-DIRECT BETTER PATH FROM SOURCE TO DESTINATION
        for(int k=0;k<size;k++){
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    // if we are calculating for kth vertex, so we need to to check our condition for updating distance for all those paths already including kth vertex
                    // That is we need not calculate for vertices in kth row and kth column
                    // for eg. if we are checking for node 0, then we skip all edges in 0th column and 0th row of adjacency matrix (here distanceMatix)
                    if(i==k || j==k){
                        continue;
                    }
                    else if(distanceMatix[i][j] > (distanceMatix[i][k] + distanceMatix[k][j])){
                        distanceMatix[i][j] = distanceMatix[i][k] + distanceMatix[k][j];
                    }
                }
            }
        }
        
        // Print Shortest distance between all pair of vertices
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(distanceMatix[i][j]+" ");
            }
            System.out.println();
        }
        
    }
    
    // helper function to add a directed weighted edge from node i to j, and having with weight of edge 'weight'
    private void addDirectedWeightedEdge(int i, int j, int weight){
        GraphNode src = this.nodesList.get(i-1);
        GraphNode dest = this.nodesList.get(j-1);
        src.neighbours.add(dest);
        src.weightMap.put(dest,weight);
    }
    
    public static void main(String[] args) throws Exception {
        FloydWarshallGraph graph = new FloydWarshallGraph();
        
        // adding 5 nodes V1-V5 to nodeList
        for(int i=1;i<=5;i++){
            graph.nodesList.add(new GraphNode("V"+i));
        }
        
        // Now adding directed weighted edges between nodes
        // Diagram for the graph used is on the top of post: https://theheapspace.blogspot.com/2020/06/all-pairs-shortest-path-floyd-warshall.html
        graph.addDirectedWeightedEdge(1,3,6); //Add V1 -> V3 , weight 6
		    graph.addDirectedWeightedEdge(1,4,6); //Add V1 -> V4 , weight 6
		    graph.addDirectedWeightedEdge(2,1,3); //Add V2 -> V1 , weight 3
		    graph.addDirectedWeightedEdge(3,4,2); //Add V3 -> V4 , weight 2
		    graph.addDirectedWeightedEdge(4,3,1); //Add V4 -> V3 , weight 1
		    graph.addDirectedWeightedEdge(4,2,1); //Add V4 -> V2 , weight 1
		    graph.addDirectedWeightedEdge(5,2,4); //Add V5 -> V2 , weight 4
		    graph.addDirectedWeightedEdge(5,4,2); //Add V5 -> V4 , weight 2
		
		    graph.floydWarshall();
    }
}

/*======================OUTPUT============================

0 7 6 6 214748364 
3 0 9 9 214748364 
6 3 0 2 214748364 
4 1 1 0 214748364 
6 3 3 2 0 

=========================================================*/
