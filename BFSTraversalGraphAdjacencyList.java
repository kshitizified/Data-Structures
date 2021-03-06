import java.util.*;

// A graph node 
class GraphNode{
    String name;
    // this is a list of adjacent nodes for the current node and is  used in adjacency list
    ArrayList<GraphNode> neighbours;
    
    // constructor
    GraphNode(String name){
        this.name = name;
        this.neighbours = new ArrayList<>();
    }
}

public class BFSTraversalGraphAdjacencyList {
    
    // The node list of graph , also used to maintain adjacency list
    ArrayList<GraphNode> nodeList;
    
    // constructor
    BFSTraversalGraphAdjacencyList(){
        this.nodeList = new ArrayList<>();
    }
    
    // function to add an undirected and unweighted edge between two nodes with index i and j in nodeList
    public void addUndirectedEdge(int i, int j){
        GraphNode node1 = this.nodeList.get(i);
        GraphNode node2 = this.nodeList.get(j);
        this.nodeList.get(i).neighbours.add(node2);
        this.nodeList.get(j).neighbours.add(node1);
    }
    
    
    // function to do BFS Traversal on Graph
    // Time Complexity - O(V+E)  Space Complexity - O(V+E)
    public void bfs(){
        // using hashset to keep track of visited nodes is better as compared to using flags
        // If we use flags, we should reset all the visited flags to false for all the nodes after one bfs iteration.
        // But in hashset we don not need to do anything like thing as a new hashset is created for every iteration of bfs
        System.out.println("Traversing Graph using BFS :");
        HashSet<GraphNode> visitedNodes = new HashSet<GraphNode>();
        for(GraphNode graphNode : this.nodeList){
            if(!visitedNodes.contains(graphNode)){
                bfsVisit(graphNode,visitedNodes);
            }
        }
    }
    
    // helper function for bfs
    private void bfsVisit(GraphNode graphNode, HashSet<GraphNode> visitedNodes){
        
        Queue<GraphNode> q = new LinkedList<>();
        q.add(graphNode);
        visitedNodes.add(graphNode);
        while(!q.isEmpty()){
            GraphNode curr = q.poll();
            System.out.print(curr.name+" ");
            
            for(GraphNode neighbour : curr.neighbours){
                if(!visitedNodes.contains(neighbour)){
                    q.add(neighbour);
                    visitedNodes.add(neighbour);
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        BFSTraversalGraphAdjacencyList graph = new BFSTraversalGraphAdjacencyList();
        
        
        // Add 6 nodes to nodes list
        
        for(int i=0;i<6;i++){
            graph.nodeList.add(new GraphNode("V"+i));
        }
        
        
        // add adjancent nodes of each node
        
        /*	undirected and Unweighted Graph used for this code  :
	
		                     V0
		                   /  \
		                  V1   V2
		                 / \   |
		                |   \ /
		               V3----V4
		                \    /
		                 \  /
		                  V5
		                  
	*/
	
	    // add adjancent nodes of each node
        graph.addUndirectedEdge(2,4);
        graph.addUndirectedEdge(0,1);
        graph.addUndirectedEdge(1,3);
        graph.addUndirectedEdge(0,2);
        graph.addUndirectedEdge(3,4);
        graph.addUndirectedEdge(4,5);
        graph.addUndirectedEdge(3,5);
        graph.addUndirectedEdge(1,4);
        
        // Now as we have added all the edges or adjacent nodes in adjacency list [the neighbour ArrayList of each node],
        // we are ready to traverse the graph
        
        graph.bfs();
        
    }
}
/*============================= OUTPUT =================================

BFS Traversal of given graph :
V0 V1 V2 V3 V4 V5 

=======================================================================*/
