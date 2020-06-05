import java.util.*;

// A weighted Graph Node
class GraphNode{
    String name;
    // list to store neighbours
    ArrayList<GraphNode> neighbours;
    // hashmap to store edge weight
    HashMap<GraphNode,Integer> weightMap;
    // shortest distance from source node
    int distance;
    //parent node
    GraphNode parent;
    
    
    // constructor
    GraphNode(String name){
        this.name = name;
        this.neighbours = new ArrayList<>();
        this.weightMap = new HashMap<>();
        this.distance = Integer.MAX_VALUE/2;
        this.parent = null;
    }
    
}

public class BellmanFordGraphSSSP {
    ArrayList<GraphNode> nodesList;
    
    //constructor
    BellmanFordGraphSSSP(){
        this.nodesList = new ArrayList<>();
    }
    
    // method to find shortest distance from source node to all other nodes
    // Time Complexity: O(V.E)  Space Complexity: O(V)
    public void bellmanFord(GraphNode source){
       // setting distance of source node as zero
        source.distance=0;
        
        // run v-1 times
        for(int i=1;i<this.nodesList.size();i++){
            // for all node's neighbours
            // so going for all nodes first (u)
            for(GraphNode u : this.nodesList){
                // now going for each edge of node (v)
                for(GraphNode v : u.neighbours){
                    if(u.distance==Integer.MAX_VALUE && v.distance==Integer.MAX_VALUE){
                        continue;
                    }
                    else if((u.distance + u.weightMap.get(v))<v.distance){
                        v.distance = u.distance + u.weightMap.get(v);
                        v.parent = u;
                    }
                }
            }
        }
        
        // Vth iteration , here V is no. of vertices/nodes in the graph
        // used to detect negative cycle
        for(GraphNode u : this.nodesList){
            for(GraphNode v : u.neighbours){
                // if at any time the current value gets updated, it means we have a negative cycle
                if((u.distance + u.weightMap.get(v)) < v.distance){
                    System.out.println("A Negative Cycle exists in the given graph, hence can't find SSSP");
                    return;
                }
            }
        }
        
        // printing shortest path for all nodes from the given source node
        for(int i=0;i<this.nodesList.size();i++){
            System.out.println("Printing Shortest path from source node: "+source.name+" to node : "+this.nodesList.get(i).name);
            System.out.print(" Total distance: "+this.nodesList.get(i).distance+" Shortest path: ");
            printPath(this.nodesList.get(i));
            System.out.println();
            System.out.println();
        }
    }
    
     // A helper function to print the path from source node to the given node
    private void printPath(GraphNode node){
        if(node.parent!=null){
            printPath(node.parent);
        }
        System.out.print(node.name+" ");
    }
    
    //a helper function to add directed weighted edge between source and dest node
    private void addDirectedWeightedEdge(int i, int j, int weight){
        GraphNode src = this.nodesList.get(i-1);
        GraphNode dest = this.nodesList.get(j-1);
        src.neighbours.add(dest);
        src.weightMap.put(dest,weight);
    }
    
    public static void main(String[] args) throws Exception {
        BellmanFordGraphSSSP graph = new BellmanFordGraphSSSP();
        
        // adding 5 nodes V1-V5 to nodeList
        for(int i=1;i<=5;i++){
            graph.nodesList.add(new GraphNode("V"+i));
        }
        
        // Now adding directed weighted edges between nodes
        // Diagram for the graph used is on the top of post: https://theheapspace.blogspot.com/2020/06/single-source-shortest-path-bellman.html
        graph.addDirectedWeightedEdge(1,3,6); //Add V1 -> V3 , weight 6
		graph.addDirectedWeightedEdge(1,4,6); //Add V1 -> V4 , weight 6
		graph.addDirectedWeightedEdge(2,1,3); //Add V2 -> V1 , weight 3
		graph.addDirectedWeightedEdge(3,4,2); //Add V3 -> V4 , weight 2
		graph.addDirectedWeightedEdge(4,3,1); //Add V4 -> V3 , weight 1
		graph.addDirectedWeightedEdge(4,2,1); //Add V4 -> V2 , weight 1
		graph.addDirectedWeightedEdge(5,2,4); //Add V5 -> V2 , weight 4
		graph.addDirectedWeightedEdge(5,4,2); //Add V5 -> V4 , weight 2
		
		// Now we have added all nodes and directed weighted edges to it, we can now use Bellman Ford algo
		
		System.out.println("Printing Shortest Path from source V5 to all other nodes using Bellman Ford: ");
		System.out.println();
		graph.bellmanFord(graph.nodesList.get(4));
    }
}

/*=======================================OUTPUT==========================================

Printing Shortest Path from source V5 to all other nodes using Bellman Ford: 

Printing Shortest path from source node: V5 to node : V1
 Total distance: 6 Shortest path: V5 V4 V2 V1 

Printing Shortest path from source node: V5 to node : V2
 Total distance: 3 Shortest path: V5 V4 V2 

Printing Shortest path from source node: V5 to node : V3
 Total distance: 3 Shortest path: V5 V4 V3 

Printing Shortest path from source node: V5 to node : V4
 Total distance: 2 Shortest path: V5 V4 

Printing Shortest path from source node: V5 to node : V5
 Total distance: 0 Shortest path: V5
 
 ========================================================================================*/
