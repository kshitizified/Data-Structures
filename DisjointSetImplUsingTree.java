import java.util.*;

// a node that represent a single disjoint set containing a single data element
class Node{
    int data;
    // we are onl concernd about the rank pf root of the trees
    // we will be using this rank of parent nodes of trees to determine which tree is to be merged to other.
    int rank;
    // this represents the parent node of this node
    // we are going to make this parent equal to current node, initially in makeset() operation
    Node parent;
    
    // constructor
    Node(int x){
        this.data = x;
        this.rank = 1;
        this.parent = null;
    }
}

public class DisjointSetImplUsingTree {
    
    // A map that will give us the node associated with a given data
    HashMap<Integer,Node> dataNodeMap;
    
    // constructor
    DisjointSetImplUsingTree(){
        this.dataNodeMap = new HashMap<>();
    }
    
    // Method to do makeset operation
    // 1. We make a new node with given data
    // 2. we make parent  of this node as self
    // 3. finally we put this node in dataNodeMap
    // After running makeSet operation for all nodes/elements, we get individual nodes as individual disjoint sets
    public void makeSet(int data){
        Node node = new Node(data);
        node.parent = node;
        dataNodeMap.put(data,node);
    }
    
    // method to find the disjoint set to which diven element belongs
    public Node find(int x){
        Node node = this.dataNodeMap.get(x);
        Node parent = findSet(node);
        return parent;
    }
    
    // helper method for find method
    // it also uses path compression 
    private Node findSet(Node node){
        Node parent = node.parent;
        if(parent==node){
            return node;
        }
        else{
            parent = findSet(parent);
        }
        // here it is using path compression
        node.parent = parent;
        
        return parent;
    }
    
    // method to merge/ union two disjoint sets on the basis of rank
    // It will first find the disjoint set to which element 'x' belongs
    // Then it will find the disjoint set to which element 'y' belongs.
    // After this it will merge those disjoint sets by comaparing the ranks
    public void union(int x, int y){
        Node nodeX = this.dataNodeMap.get(x);
        Node nodeY = this.dataNodeMap.get(y);
        
        Node parentX = this.find(x);
        Node parentY = this.find(y);
        
        // if 'x' and 'y' are already in the same set, we return
        if(parentX==parentY){
            return;
        }
        
        // else if 'x' and 'y' are in different set, we merge by rank
        // if rank of 'x' is greater than or equal to rank of 'y', we merge y into x and update the rank of X
        // Here we are not concerned with the ranks for non-root nodes of trees
        if(parentX.rank>=parentY.rank){
            parentX.rank = parentX.rank + parentY.rank;
            parentY.parent = parentX;
        }
        // else if rank of 'y' is greater than the rank of 'x', we merge x into y and update rank accordingly
        // Here we are not concerned with the ranks for non-root nodes of trees
        else if(parentX.rank<parentY.rank){
            parentY.rank = parentY.rank + parentX.rank;
            parentX.parent = parentY;
        }
    }
    
    public static void main(String[] args) throws Exception {
        DisjointSetImplUsingTree ds = new DisjointSetImplUsingTree();
        
        // running makeSet operation for elements from 1-7
        for(int i=1;i<=7;i++){
            ds.makeSet(i);
        }
      
        // performing union operations
        ds.union(1,2);
        ds.union(2,3);
        ds.union(4,5);
        ds.union(6,7);
        ds.union(5,6);
        ds.union(3,7);
        
        // performing find operation on elements 1-7
        for(int i=1;i<=7;i++){
            System.out.print(ds.find(i).data+" ");
        }
        
        System.out.println();
        System.out.println();
        
        // printing current node and it's parent node from hashmap 'dataNodeMap' values
        for(Map.Entry<Integer,Node> hm : ds.dataNodeMap.entrySet()){
            System.out.print("Current Node: "+hm.getValue().data+" , Parent Node: "+hm.getValue().parent.data);
            System.out.println();
        }
    }
}

/*=======================OUTPUT=========================

4 4 4 4 4 4 4 

Current Node: 1 , Parent Node: 4
Current Node: 2 , Parent Node: 4
Current Node: 3 , Parent Node: 4
Current Node: 4 , Parent Node: 4
Current Node: 5 , Parent Node: 4
Current Node: 6 , Parent Node: 4
Current Node: 7 , Parent Node: 4

========================================================*/
