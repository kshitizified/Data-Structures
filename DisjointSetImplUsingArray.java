

public class DisjointSetImplUsingArray {
	// here 'set' array will store following information about our disjoint set:
	// 1. index of set denotes the nodes
    // 				For example - index 0 denotes node1, index 4 denotes node 4 and so on.
	// 2. If there is a negative value at a index, then it indicates that node denoted by that particular index is a parent node
	//				For example - suppose index 0 has value -1 in the array 'set', so this indicates that node denoted by the index 0 is a parent node.
	// 3. If there is a negative value at a index, then it's absolute value indicates about the number of nodes under that parent.
	//				For example - suppose index 1 has value -2, so this indicates that node represented by index 1 is a parent and has 2 nodes(including itself) under it.
    int[] set;
    
    // Constructor
    DisjointSetImplUsingArray(int size){
        this.set = new int[size];
    }
    
    // method to make 'size' number of disjoint sets initially
    public void makeSet(int size){
        for(int i=0;i<size;i++){
            this.set[i]=-1;
        }
    }
    
    // method to find the disjoint set to which x belongs
    // find using path compression
    public int find(int x){
        int parent = this.set[x];
        if(parent<0){
            parent =  x;
        }
        else{
            parent = find(parent);
        }
        
        return parent;
    }
    
    // x and y are index/nodes
    // method to union the disjoint sets of elements x and y if they belong to different disjoint sets
    public void union(int x,int y){
        int parentX = find(x);
        int parentY = find(y);
        
        // already belong to same set
        if(parentX==parentY){
        	System.out.println();
            System.out.println(x+" and "+y+" already belongs to the same set");
            return;
        }
        
        // Not belong to the same set
        // we need to decide which set is to be merged where
        
        // Calculating weight/rank of x and y nodes
        // weight node tells us how many nodes/elements are associated with the disjoint set of current element
        // these should always come negative as it is indicating the parent
        int weightX = this.set[parentX];
        int weightY = this.set[parentY];
        
        
        // if weight/rank is equal, merging y into x
        if(Math.abs(weightX)==Math.abs(weightY)){
            this.set[parentY] = parentX;
            // As we are merging Y into X, the weight of X will be updated.
            // if weight of Y is negative, we take it's weight's absolute value and add it to current weight of X
            weightX = -1 * (Math.abs(weightX)+ ((weightY<0) ? Math.abs(weightY) : 1));
            
            this.set[parentX] = weightX;
        }
        // if weight/rank of X is greater than Y, merge Y into X
         else if(Math.abs(weightX) > Math.abs(weightY)) {
        	 this.set[parentY] = parentX;
             
             weightX = -1 * (Math.abs(weightX)+ ((weightY<0) ? Math.abs(weightY) : 1));
             
             this.set[parentX] = weightX;
         }
        // if weight/rank of Y is greater than X, merge X into Y
         else if(Math.abs(weightX) < Math.abs(weightY)) {
        	 this.set[parentX] = parentY;
             
             weightY = -1 * (Math.abs(weightY)+ ((weightX<0) ? Math.abs(weightX) : 1));
             
             this.set[parentY] = weightY;
         }
 
    }
    
    public static void main(String[] args) throws Exception {
        int size = 8; // for nodes 0,1,2,3,4,5,6,7
        
        DisjointSetImplUsingArray disjointSet = new DisjointSetImplUsingArray(size);
        
        disjointSet.makeSet(size);
        
         for(int i=0;i<size;i++){
             System.out.print(disjointSet.find(i)+" ");
         }
        
         disjointSet.union(0, 1);
         disjointSet.union(2, 3);
         disjointSet.union(4, 5);
         disjointSet.union(6, 7);
         
         disjointSet.union(1, 3);
         disjointSet.union(1, 4);
         disjointSet.union(0, 2);
         disjointSet.union(5, 7);
         disjointSet.union(4, 6);
         
         System.out.println();
         for(int i=0;i<size;i++){
             System.out.print(disjointSet.find(i)+" ");
         }
         
    }
}

/*================================OUTPUT===================================

0 1 2 3 4 5 6 7 
0 and 2 already belongs to the same set

4 and 6 already belongs to the same set

0 0 0 0 0 0 0 0 

===========================================================================*/
