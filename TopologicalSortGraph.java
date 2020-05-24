import java.util.*;

// A Graph Node
class GraphNode{
    String name;
    // list we use in adjacency list
    ArrayList<GraphNode> neighbours;
    
    // Constructor
    GraphNode(String name){
        this.name = name;
        this.neighbours = new ArrayList<>();
    }
}

public class TopologicalSortGraph {
    
    ArrayList<GraphNode> nodesList;
    
    // constructor
    TopologicalSortGraph(){
        this.nodesList = new ArrayList<>();
    }
    
    // function to add a directed edge from i->j
    public void addDirectedAcyclicEdge(int source, int destination){
        if(source==destination){
            System.out.println("Can't add cyclic edge in graph, as for topological sort the graph should be Directed Acyclic Graph");
            return;
        }
        // As we have to add a directed edge from source to destination node,
        // we get the source node from the list and add destination node in source node's neighbours
        this.nodesList.get(source).neighbours.add(this.nodesList.get(destination));
    
    }
    
    // function to do topological sort 
    // Time Complexity - O(V+E)  , Space Complexity - O(V+E)
    public void topologicalSort(){
        System.out.println("Topological Sort of given graph...");
        // Hashset to store nodes which are already visited
        HashSet<GraphNode> visitedNodes = new HashSet<>();
        // stack to do linear ordering of nodes using topologicalSort
        Stack<GraphNode> stack = new Stack<>();
        
        // for every unvisited node 
        for(GraphNode graphnode : this.nodesList){
            // here I am starting from node v0
            if(!visitedNodes.contains(graphnode)){
                topologicalVisit(graphnode,visitedNodes,stack);
            }
        }
        
        // Now here we will have all our node into the stack, so we just pop it one by one and display them
        // for the linear ordering of topological sort of given graph
        while(!stack.isEmpty()){
            System.out.print(stack.pop().name+" ");
        }
        
        
    }
    
    // hepler method for topologicalSort. We will call this method recursively for neighbours of current node
    private void topologicalVisit(GraphNode curr, HashSet<GraphNode> visitedNodes,Stack<GraphNode> stack){
        // if current node is visited, we return
        // base case of recursion
        if(visitedNodes.contains(curr)){
            return;
        }
        
        // for every neighbour or adjacent nodes which are unvisited call this function recursively
        for(GraphNode neighbour : curr.neighbours){
            if(!visitedNodes.contains(neighbour)){
                topologicalVisit(neighbour,visitedNodes,stack);
            }
        }
        
        // if no neigbour of current node, then simply push it to stack and mark it visited    
        visitedNodes.add(curr);
        stack.push(curr);
            
    }
    
    public static void main(String[] args) throws Exception {
        TopologicalSortGraph graph = new TopologicalSortGraph();
        
        // add 8 nodes to nodes list
        for(int i=0;i<8;i++){
            graph.nodesList.add(new GraphNode("V"+i));
        }
        
        // add directed acyclic edges between nodes. 
        // The image of graph used for this code is published in blog post.
        // IF you are on blog, see above.
        // If you are on github follow link - https://theheapspace.blogspot.com/2020/05/topological-sort-code-graphs.html
        
        // Add directed and acyclic edge i->j
        graph.addDirectedAcyclicEdge(0,2);
        graph.addDirectedAcyclicEdge(1,3);
        graph.addDirectedAcyclicEdge(1,2);
        graph.addDirectedAcyclicEdge(2,4);
        graph.addDirectedAcyclicEdge(3,5);
        graph.addDirectedAcyclicEdge(4,7);
        graph.addDirectedAcyclicEdge(4,5);
        graph.addDirectedAcyclicEdge(5,6);
        
        // Now we have added all the directed acyclic edges.
        // we are now ready to do topological sorting
        
        graph.topologicalSort();
    }
}
