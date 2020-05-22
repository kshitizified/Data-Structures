import java.util.*;

// A graph Node
class GraphNode{
    String name;
    boolean isVisited;
    //index is used to map this node with index of Adjacency Matrix
    int index; 
    GraphNode(String name,int index){
        this.name = name;
        this.isVisited = false;
        this.index = index;
    }
    
}

public class BFSGraphAdjacencyMatrix{
    
    // A List to store the address of all the nodes of the graph
    ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();

    // An adjacency Matrix to store the details about which nodes are adjacent to each other (basically which two nodes has a edge between them)
    int [][] adjacencyMatrix;
    
    // Constructor
    BFSGraphAdjacencyMatrix(ArrayList<GraphNode> nodeList){
        this.nodeList = nodeList;
        this.adjacencyMatrix = new int[nodeList.size()][nodeList.size()];
    }
    
    // function to add an undirected edge between two nodes whose index are i-1 and j-1 in adjacency matrix
    public void addUndirectedEdge(int i, int j) {
		//decrement i, j for array indexes
		i--;
		j--;
		this.adjacencyMatrix[i][j] = 1;
		this.adjacencyMatrix[j][i] = 1;
		
	}
	
	// function to do a breadh first search on the graph
	// Time Complexity - O(V+E)  Space Complexity - O(V+E)  where V is no. of vertices and E is no. of edges
	public void bfs(){
	    if(nodeList.size()==0){
	        System.out.println("Can't traverse empty graph");
	        return;
	    }
	    
	    System.out.println("BFS Traversal of given graph :");
	    // this for loop also takes care of disconnected graphs, in which any node is not connected with the portion of graph
	    // for each node iterate
	    for(GraphNode graphNode : this.nodeList){
	        if(graphNode.isVisited==false){
	            bfsVisit(graphNode);
	        }
	    }
	}
	
	// Helper function for BFS 
	private void bfsVisit(GraphNode graphNode){
	    // queue to store neighbour nodes 
	    Queue<GraphNode> q = new LinkedList<>();
	    
	    // first put the starting node into queue
	    q.add(graphNode);
	    
	    // iterate till queue is not empty
	    // basically what it does is it adds neighbours of curr node, removes and print curr node 
	    //and does this for all other connected nodes (which are connected to the starting node) 
	    while(!q.isEmpty()){
	        GraphNode curr = q.poll();
	        curr.isVisited = true;
	        System.out.print(curr.name+" ");
	        
	        ArrayList<GraphNode> neighbours = getNeighbors(curr);
	        
	        // for each node's neighbours , if the current neighbour is visited leave it, else add it to queue 
	        for(GraphNode neighbour : neighbours){
	            if(neighbour.isVisited==false){
	                q.add(neighbour);
	                neighbour.isVisited=true;
	            }
	        }
	    }
	}
    
    // helper function to get all neighbors of a particular node by checking adjacency matrix and adding it to neighbours arraylist
    private ArrayList<GraphNode> getNeighbors(GraphNode graphNode){
        ArrayList<GraphNode> neighbours = new ArrayList<>();
        for(int i=0;i<this.adjacencyMatrix.length;i++){
            if(adjacencyMatrix[graphNode.index][i] != 0){
                neighbours.add(this.nodeList.get(i));
            }
        
        }
        return neighbours;
    }
    
    
    public static void main(String[] args) throws Exception {
        
        //Will store Nodes in this List
		ArrayList<GraphNode> nodeList = new ArrayList<>();
		
		//Creating 6 nodes: V1-V6 and initializing their index for adjacency matrix 
		for(int i=1;i<=6; i++) {
			nodeList.add(new GraphNode("V"+i,i-1));
		}
		
		// finally adding all nodes to Main nodeList using constructor
		BFSGraphAdjacencyMatrix graph = new BFSGraphAdjacencyMatrix(nodeList);
		
	/*	undirected and Unweighted Graph used for this code  :
	
		                     V1
		                   /  \
		                  V2   V3 
		                 / \   |
		                |   \ /
		               V4----V5
		                \    /
		                 \  /
		                  V6
		                  
	*/
	
		// now as we have node list, let's add the edges to it, using adjacency matrix
		// this adds an undirected edge between v1 and v2
		graph.addUndirectedEdge(1,2);
		
		// similarly as above these adds edges between mentioned nodes
		graph.addUndirectedEdge(1,3);
		graph.addUndirectedEdge(2,4);
		graph.addUndirectedEdge(2,5);
		graph.addUndirectedEdge(3,5);
		graph.addUndirectedEdge(4,5);
		graph.addUndirectedEdge(4,6);
		graph.addUndirectedEdge(5,6);
		
	    
		
		// Now till here we have created nodes of graph and connected them by edges.
		
		// So, now we are ready to traverse it 
        
    graph.bfs();
    }
}

/*============================= OUTPUT =================================

BFS Traversal of given graph :
V1 V2 V3 V4 V5 V6 

=======================================================================*/
