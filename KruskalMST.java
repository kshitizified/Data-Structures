import java.util.*;

// The Graph Node
class GraphNode{
    String data;
    // adjacent nodes
    ArrayList<GraphNode> neighbours;
    // weight od edges connecting this node to it's adjacent nodes
    HashMap<GraphNode,Integer> weightMap;
    
    // Constructor
    GraphNode(String x){
        this.data = x;
        this.neighbours = new ArrayList<>();
        this.weightMap = new HashMap<>();
    }
    
}


// class representing an undirected weighted edge between two nodes: 'firstNode' and 'secondNode'
class UndirectedEdge{
    GraphNode firstNode;
    GraphNode secondNode;
    // weight of edge
    int weight;
    
    // constructor
    UndirectedEdge(GraphNode x, GraphNode y, int wt){
        this.firstNode = x;
        this.secondNode = y;
        this.weight = wt;
    }
}

// class to represent a disjoint set containing a graph node
class DisjointSet{
    GraphNode node;
    // rank of this disjont set
    int rank;
    // parent/representative of this disjoint set
    DisjointSet parent;
    
    // constructor
    DisjointSet(GraphNode node){
        this.node = node;
        this.rank = 1;
        this.parent = null;
    }
}


public class KruskalMST {
    
    // list to store all nodes
    ArrayList<GraphNode> nodesList;
    // list to store all edges in the graph
    ArrayList<UndirectedEdge> edgesList;
    // a hashmap that to get the disjoint set associated with a graphnode
    HashMap<GraphNode,DisjointSet> nodeDisjointSetMap;
    
    // constructor
    KruskalMST(){
        this.nodesList = new ArrayList<>();
        this.edgesList = new ArrayList<>();
        this.nodeDisjointSetMap = new HashMap<>();
    }
    
    // method to add an undirected weighted edge between nodes at index i and j of nodesList
    public void addUndirectedWeightedEdge(int i, int j, int weight){
        GraphNode node1 = this.nodesList.get(i);
        GraphNode node2 = this.nodesList.get(j);
        
        // adding node 2 in neighbours of node1
        node1.neighbours.add(node2);
        // adding node2 with weight in the weightmap of node 1
        node1.weightMap.put(node2,weight);
        
        // similarly for node 2 as well
        node2.neighbours.add(node1);
        node2.weightMap.put(node1,weight);
        
        // adding the newly created edge into the edge list too
        this.edgesList.add(new UndirectedEdge(node1,node2,weight));
    }
    
    // method to sort th undirected Weighted edges in edgesList on the basis of weight in non-decreasing order
    // this is done to ensure that we pick the least cost/weight edges for out minimum spanning tree.
    public void sortUndirectedEdgesByWeight(){
        // we have to provide a comparator and overload the compareTo method  to sort the arraylist containing 'UndirectedEdge'
        Comparator<UndirectedEdge> comparator = new Comparator<UndirectedEdge>() {
			@Override
			public int compare(UndirectedEdge o1, UndirectedEdge o2) {
				return o1.weight - o2.weight;
			}
		};
		
		// finally sorting the edgesList on the basis of edge weight with the help of comparator
		Collections.sort(this.edgesList, comparator);
    }
    
    // method to do makeset operation of disjoint set
    public void makeSet(){
        for(int i=0;i<this.nodesList.size();i++){
            DisjointSet disJointSet = new DisjointSet(this.nodesList.get(i));
            disJointSet.parent = disJointSet;
            nodeDisjointSetMap.put(this.nodesList.get(i),disJointSet);
        }
    }
    
    // mehod to implement find set method of disjoint set using path compression
    public DisjointSet find(DisjointSet node){
        DisjointSet parent = node.parent;
        if(parent==node){
            return parent;
        }
        else{
            parent = find(parent);
        }
        node.parent = parent;
        return parent;
    }
    
    // method to find the union of two disjoint sets
    public int union(DisjointSet node1Set, DisjointSet node2Set){
        DisjointSet parentNode1 = find(node1Set);
        DisjointSet parentNode2 = find(node2Set);
        
        // if same parent
        if(parentNode1==parentNode2){
            // returning -1 indicates we have encountered a cycle
            return -1;
        }
        
        // if parents/representatives are different for both disjoint sets
        if(parentNode1.rank>=parentNode2.rank){
            parentNode1.rank = parentNode1.rank + parentNode2.rank;
            parentNode2.parent = parentNode1;
        }
        else{
            parentNode2.rank = parentNode2.rank + parentNode1.rank;
            parentNode1.parent = parentNode2;
        }
        
        return 0;
    }
    
    // method to implement Kruskal Algorithm to find Minimum Spanning Tree (MST)
    public void kruskal(){
        makeSet();
        sortUndirectedEdgesByWeight();
        
        int totalWeight = 0;
        
        // for each edge in the graph
        for(int i=0;i<edgesList.size();i++){
            UndirectedEdge currentEdge = this.edgesList.get(i);
            GraphNode node1 = currentEdge.firstNode;
            GraphNode node2 = currentEdge.secondNode;
            
            DisjointSet node1Set = this.nodeDisjointSetMap.get(node1);
            DisjointSet node2Set = this.nodeDisjointSetMap.get(node2);
            
            
            int unionSet = union(node1Set,node2Set);
            // if cycle exits
            if(unionSet==-1){
                continue;
            }
            else{
                System.out.println("Edge: " + node1.data + "------" + node2.data +"   || weight= " + currentEdge.weight);
                totalWeight += currentEdge.weight;
            }
        }
        
        System.out.println("Total weight of MST: " + totalWeight);
    }
    
    public static void main(String[] args) throws Exception {
        KruskalMST graph = new KruskalMST();
        
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
        
        graph.kruskal();
        
    }
}

/*======================OUTPUT=======================

Edge: V3------V2   || weight= 4
Edge: V3------V0   || weight= 5
Edge: V0------V1   || weight= 10
Total weight of MST: 19

====================================================*/
