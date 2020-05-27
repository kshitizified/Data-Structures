import java.util.*;

// A weighted Graph Node
// The GraphNode is implementing Comparable interface because we are going to use minheap(Priority Queue) for dijkstra
// In PriorityQueue all the elements are ordered using their "natural ordering" and we will be supplying GraphNode to priority queue
// Hence, we need to implement comparable interface to determine the "ordering".
class GraphNode implements Comparable<GraphNode>{
    String name;
    ArrayList<GraphNode> neighbours;
    GraphNode parent;
    
    // following used in Dijkstra's Algo
    int distance;
    // weightmap is used to store weight of edges to adjacent nodes
    HashMap<GraphNode, Integer> weightMap;
    
    // constructor
    GraphNode(String name){
        this.name = name;
        this.neighbours = new ArrayList<>();
        this.parent = null;
        
        // we set distance of each node except source node to Infinity
        // Here setting max value for all nodes, will be changing distance for source node later.
        this.distance = Integer.MAX_VALUE;
        
        this.weightMap = new HashMap<>();
    }
    
    // ONE MORE THING IS REMAINING
    // whenever we use comparable interface, we need to override the compareTo method
    // This is done to provide the comparable interface the knowledge about how to compare two GraphNodes
    // and finally store it in priority queue or minheap as per the ordering determined by this override method.
    @Override
	public int compareTo(GraphNode node) {
	    // compares the current node's distance (this.distance) with other node's distance in minheap or priority queue (node.distance).
	    // if positive no. is returned -> greater
	    // if negative is returned -> smaller
	    // if zero is returned -> equal
		return this.distance - node.distance;
	}
    
    
}
public class Dijkstra {
    
    // to store all nodes of directed weighted graph
    ArrayList<GraphNode> nodeList;
    
    // constructor
    Dijkstra(){
        this.nodeList = new ArrayList<>();
    }
    
    // function to add an edge from i -> j and add a weight 'weight' to the edge
    public void addDirectedWeightedEdge(int i, int j, int weight){
        GraphNode source = this.nodeList.get(i-1);
        GraphNode dest = this.nodeList.get(j-1);
        source.neighbours.add(dest);
        source.weightMap.put(dest,weight);
    }
    
     // function to find shortest path from source node to every other node using Dijkstra's algorithm.
    //Time Complexity: O(v^2), Space Complexity: O(V)
    public void dijkstra(GraphNode source){
        // using minheap to store distance of all nodes
        PriorityQueue<GraphNode> minHeap = new PriorityQueue<>();
        // setting distance of source node as 0
        // Also, we have already set the distance of all other nodes in GraphNode constructor
        source.distance=0;
        // adding all nodes distance to minheap
        minHeap.addAll(this.nodeList);
        
        while(!minHeap.isEmpty()){
            GraphNode currNode = minHeap.poll();
            
            for(GraphNode neighbour : currNode.neighbours){
                // if neighbour is yet to be processed finally
                if(minHeap.contains(neighbour)){
                    
                    // if (source distance + weight of edge to neighbour node) is less than neighbour distance, we update the neighbour distance
                    // and set neighbour's parent to current node
                    if((currNode.distance + currNode.weightMap.get(neighbour)) < neighbour.distance){
                        neighbour.distance = currNode.distance + currNode.weightMap.get(neighbour);
                        neighbour.parent = currNode;
                        
                        // refreshing the minheap as we have updated neighbour details
                        minHeap.remove(neighbour);
                        minHeap.add(neighbour);
                    }
                }
            }
        }
        
        // printing shortest path for all nodes from the given source node
        for(int i=0;i<this.nodeList.size();i++){
            System.out.println("Printing Shortest path from source node: "+source.name+" to node : "+this.nodeList.get(i).name);
            System.out.print(" Total distance: "+this.nodeList.get(i).distance+" Shortest path: ");
            printPath(this.nodeList.get(i));
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
    
    public static void main(String[] args) throws Exception {
        Dijkstra graph = new Dijkstra();
        
        // adding 5 nodes V1-V5 to nodeList
        for(int i=1;i<=5;i++){
            graph.nodeList.add(new GraphNode("V"+i));
        }
        
        // Now adding directed weighted edges between nodes
        graph.addDirectedWeightedEdge(1,3,6); //Add V1 -> V3 , weight 6
		graph.addDirectedWeightedEdge(1,4,6); //Add V1 -> V4 , weight 6
		graph.addDirectedWeightedEdge(2,1,3); //Add V2 -> V1 , weight 3
		graph.addDirectedWeightedEdge(3,4,2); //Add V3 -> V4 , weight 2
		graph.addDirectedWeightedEdge(4,3,1); //Add V4 -> V3 , weight 1
		graph.addDirectedWeightedEdge(4,2,1); //Add V4 -> V2 , weight 1
		graph.addDirectedWeightedEdge(5,2,4); //Add V5 -> V2 , weight 4
		graph.addDirectedWeightedEdge(5,4,2); //Add V5 -> V4 , weight 2
		
		// Now we have added all nodes and directed weighted edges to it, we can now use Dijkstra algo
		
		System.out.println("Printing Shortest Path from source V5 to all other nodes using Dijkstra: ");
		System.out.println();
		graph.dijkstra(graph.nodeList.get(4));
        
    }
}

/*============================================OUTPUT==============================================

Printing Shortest Path from source V5 to all other nodes using Dijkstra: 

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
 
 ====================================================================================================*/
