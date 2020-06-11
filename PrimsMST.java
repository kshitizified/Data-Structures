import java.util.*;

// The GraphNode implementing Comparable interface as we are going to use min-heap on these graph nodes.
class GraphNode implements Comparable<GraphNode>{
    String data;
    // used for prim's algorithm
    int distance;
    // adjacent nodes to current node
    ArrayList<GraphNode> neighbours;
    // weight of edges from currentNode
    HashMap<GraphNode,Integer> weightMap;
    
    // constructor
    GraphNode(String x){
        this.data = x;
        // setting the distance to Integer.MAX_VALUE as required by Prim's algorithm
        this.distance = Integer.MAX_VALUE;
        this.neighbours = new ArrayList<>();
        this.weightMap = new HashMap<>();
    }
    
    // We are going to use min-heap on these graphnodes, so we need to tell the minheap how to compare these graph nodes
    // for doing that we need to override the compareTo method of comparable interface
    @Override
    public int compareTo(GraphNode e){
        return this.distance - e.distance;
    }
    
}

public class PrimsMST {
    
    // stores list of all the nodes present in the graph
    ArrayList<GraphNode> nodesList;
    
    // constructor
    PrimsMST(){
        this.nodesList = new ArrayList<>();
    }
    
    // method to add an undirected weighted edge from node represented by index i and j in the nodesList
    public void addUndirectedWeightedEdge(int i, int j, int wt){
        GraphNode firstNode = this.nodesList.get(i);
        GraphNode secondNode = this.nodesList.get(j);
        
        firstNode.neighbours.add(secondNode);
        firstNode.weightMap.put(secondNode,wt);
        
        secondNode.neighbours.add(firstNode);
        secondNode.weightMap.put(firstNode,wt);
    }
    
    // method to find Minimum Spanning Tree using Prim's Algorithm
    public void prim(){
        // hashset used to mark noded as visited
        HashSet<GraphNode> visited = new HashSet<>();
        // the minHeap
        PriorityQueue<GraphNode> minHeap = new PriorityQueue<>();
        // setting the source to V0
        GraphNode source = this.nodesList.get(0);
        // setting the surce distance to 0, as required by Prim's algorithm
        source.distance = 0;
        
        // adding all vertices in the nodesList to the minHeap
        minHeap.addAll(this.nodesList);
        
        // this keeps track of the total cost of edges in the Minimum Spanning Tree
        int cost = 0;
        
        // while minHeap is not empty 
        while(!minHeap.isEmpty()){
            // extract min 
            GraphNode currentNode = minHeap.poll();
            // mark it as visited
            visited.add(currentNode);
            // update cost
            cost += currentNode.distance;
            // for all the adjacent nodes of the currentNode, check if the distance of neighbour is greater that weight of edge
            // if yes, update distance of edge as weight of edge
            for(GraphNode neighbour : currentNode.neighbours){
                // distanceToNeighbour is edge weight from currentNode to current neighbour
                int distanceToNeighbour = currentNode.weightMap.get(neighbour);
                // if current neighbour is not visited, check distanc and update
                if(!visited.contains(neighbour) && (neighbour.distance > distanceToNeighbour)){
                    neighbour.distance = distanceToNeighbour;
                    
                    // refresh the minheap
                    minHeap.remove(neighbour);
                    minHeap.add(neighbour);
                }
            }
            System.out.println("Node selected: "+ currentNode.data+" || Weight of edge from selected node: " + currentNode.distance);
        }
        
        System.out.println("Total cost of Minimum Spanning Tree = " + cost);
    }
    
    public static void main(String[] args) throws Exception {
        PrimsMST graph = new PrimsMST();
        
        // adding vertices/nodes
        for(int i=0;i<4;i++){
            graph.nodesList.add(new GraphNode("V"+i));
        }
        
        // adding undirected, weighted edges 
        graph.addUndirectedWeightedEdge(0,1,10);
        graph.addUndirectedWeightedEdge(1,3,15);
        graph.addUndirectedWeightedEdge(3,2,4);
        graph.addUndirectedWeightedEdge(2,0,6);
        graph.addUndirectedWeightedEdge(3,0,5);
        
        graph.prim();
        
    }
}

/*============================OUTPUT=============================

Node selected: V0 || Weight of edge from selected node: 0
Node selected: V3 || Weight of edge from selected node: 5
Node selected: V2 || Weight of edge from selected node: 4
Node selected: V1 || Weight of edge from selected node: 10
Total cost of Minimum Spanning Tree = 19

=================================================================*/
