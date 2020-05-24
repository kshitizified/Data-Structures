import java.util.*;

// A graph node with extra attribute parent 
class GraphNode{
    String name;
    ArrayList<GraphNode> neighbours;
    
    // this keeps track of the parent node of the current node (in BFS Traversal)
    GraphNode parent;
    
    // constructor
    GraphNode(String name){
        this.name = name;
        this.neighbours = new ArrayList<>();
        this.parent=null;
    }
}

public class ShortestPathBFS {
    // node list of graph
    ArrayList<GraphNode> nodesList;
    
    // Constructor
    ShortestPathBFS(){
        this.nodesList = new ArrayList<>();
    }
    
    // function to add an undirected and unweighted edge between nodes at index i and j of nodelist
    public void addUndirectedEdge(int i, int j){
        GraphNode node1 = this.nodesList.get(i);
        GraphNode node2 = this.nodesList.get(j);
        
        node1.neighbours.add(node2);
        node2.neighbours.add(node1);
        
    }
    
    // function to backtrack to parent node when we found shortest path from source to dest node 
    // and print value of all nodes from parent to dest
    public void printPath(GraphNode curr){
        if(curr.parent!=null){
            printPath(curr.parent);
        }
        System.out.print(curr.name+" ");
    }
    
    // function to find the shortest Distane between two nodes using BFS, assuming there is a valid path from src to dest node
    // Time Complexity - O(V+E)   Space Complexity- O(V+E)
    public void shortestDistaneBFS(GraphNode src, GraphNode dest){
        // resetting parent nodes to null
        for(int i=0;i<this.nodesList.size();i++){
            this.nodesList.get(i).parent=null;
        }
        System.out.println("Printing Shortest Path from "+src.name+"->"+dest.name);
        Queue<GraphNode> q = new LinkedList<>();
        HashSet<GraphNode> visited = new HashSet<>();
        q.add(src);
        
        while(!q.isEmpty()){
            GraphNode curr = q.poll();
            visited.add(curr);
            
            if(curr==dest){
                printPath(dest);
                break;
            }
            
            for(GraphNode neighbour : curr.neighbours){
                if(!visited.contains(neighbour)){
                    if(neighbour.parent==null){
                        // if the parent of neighbour is null, we set its parent to curr node
                        // if the parent of neighbour is not null, we ignore and not update
                        neighbour.parent = curr;
                    }
                    q.add(neighbour);
                }
            }
        }
        
        System.out.println();
        
    }
    
    public static void main(String[] args) throws Exception {
        ShortestPathBFS graph = new ShortestPathBFS();
        
        // add 10 nodes from v0-v9
        for(int i=0;i<10;i++){
            GraphNode newNode = new GraphNode("V"+i);
            graph.nodesList.add(newNode);
        }
        
        // add undirected and unweighted edge between two nodes
        graph.addUndirectedEdge(0,8);
		graph.addUndirectedEdge(8,2);
		graph.addUndirectedEdge(8,9);
		graph.addUndirectedEdge(2,1);
		graph.addUndirectedEdge(9,1);
		graph.addUndirectedEdge(2,4);
		graph.addUndirectedEdge(1,3);
		graph.addUndirectedEdge(1,7);
		graph.addUndirectedEdge(3,4);
		graph.addUndirectedEdge(3,5);
		graph.addUndirectedEdge(7,6);
		graph.addUndirectedEdge(5,6);
		
		// assuming there is a valid path between source node and destination node
		// calculating shortest distance between source node and destination node
		graph.shortestDistaneBFS(graph.nodesList.get(2),graph.nodesList.get(5));
		graph.shortestDistaneBFS(graph.nodesList.get(2),graph.nodesList.get(9));
		graph.shortestDistaneBFS(graph.nodesList.get(2),graph.nodesList.get(6));
		graph.shortestDistaneBFS(graph.nodesList.get(2),graph.nodesList.get(7));
		graph.shortestDistaneBFS(graph.nodesList.get(2),graph.nodesList.get(3));
		graph.shortestDistaneBFS(graph.nodesList.get(2),graph.nodesList.get(0));
		graph.shortestDistaneBFS(graph.nodesList.get(2),graph.nodesList.get(4));
		graph.shortestDistaneBFS(graph.nodesList.get(5),graph.nodesList.get(2));
		graph.shortestDistaneBFS(graph.nodesList.get(6),graph.nodesList.get(2));
		graph.shortestDistaneBFS(graph.nodesList.get(5),graph.nodesList.get(5));
    }
}

/*========================== OUTPUT ============================

Printing Shortest Path from V2->V5
V2 V1 V3 V5 
Printing Shortest Path from V2->V9
V2 V8 V9 
Printing Shortest Path from V2->V6
V2 V1 V7 V6 
Printing Shortest Path from V2->V7
V2 V1 V7 
Printing Shortest Path from V2->V3
V2 V1 V3 
Printing Shortest Path from V2->V0
V2 V8 V0 
Printing Shortest Path from V2->V4
V2 V4 
Printing Shortest Path from V5->V2
V5 V3 V1 V2 
Printing Shortest Path from V6->V2
V6 V7 V1 V2 
Printing Shortest Path from V5->V5
V5 

========================================================*/
