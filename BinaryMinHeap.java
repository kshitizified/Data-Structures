public class BinaryMinHeap {
    // the array used to implement min heap
    // left child index is [2*x]  and right child index is [(2*x)+1]  if 'x' is the index of parent in 
// array 'arr'.
    // Cell 0 is unused for the sake of mathematical simplicity.
    int arr[];

    // variable to store the last used index of arr
    int lastUsedIndex;
 
    // creating heap using constuctor
    // Time Complexity - O(1) , Space Complexity - O(n)
    BinaryMinHeap(int size){
        this.arr = new int[size];
        this.lastUsedIndex=0;
    }
 
    // method to initialize the arr with Integer.MIN_VALUE, when it is empty
    public void initializeArray(){
        if(this.lastUsedIndex==0){
            for(int i=0;i<this.arr.length;i++){
                this.arr[i]=Integer.MIN_VALUE;
            }
        }
    }
 
    // method to add a new node to the binary min heap
    // Time Complexity - O(log n) , Space Complexity - O(1)
    public void addNode(int x){
        // if binary min heap is full
        if(this.lastUsedIndex==this.arr.length-1){
            System.out.println("Binary heap is full, can't add new node");
            return;
        }
        // else if binary min heap is empty
        this.lastUsedIndex++;
        this.arr[this.lastUsedIndex]=x;
     
        // heapify bottom to top
        heapifyBottomToTop();
    }
   
    // helper method to heapify from bottom node (newly added node) to top node. Heapify means swaping nodes to maintain the min heap property
    // Time Complexity - O(log n) , Space Complexity - O(1)
    private void heapifyBottomToTop(){ 

        // child node / newly added node's index
        int childNodeIndex = this.lastUsedIndex;

        // move from bottom to top , swaping nodes if needed to maintain the min heap property
        while(childNodeIndex>1){
         
            int parentNodeIndex = childNodeIndex/2;
         
            if(this.arr[parentNodeIndex]>this.arr[childNodeIndex]){
                int temp = this.arr[parentNodeIndex];
                this.arr[parentNodeIndex] = this.arr[childNodeIndex];
                this.arr[childNodeIndex] = temp;
            }
         
            childNodeIndex = parentNodeIndex;
        }
    }
 
    // method to do a level order traversal in binary heap
    // Time Complexity - O(n) , Space Complexity - O(1)
    public void levelOrderTraversal(){

        // if binary min heap is empty
        if(this.lastUsedIndex==0 || this.arr==null){
            System.out.println("Binary heap is empty, can't traverse");
            return;
        }
        // else if binary min heap is not empty
        System.out.println("Level Order Traversal...");
        for(int i=1;i<=this.lastUsedIndex;i++){
            System.out.print(this.arr[i]+" ");
        }
        System.out.println();
    }
 
 
    // method to extract the top node having minimum value from the binary min heap
    // we can only extract the root node from any heap. NO OTHER NODES CAN BE EXTRACTED.
    // Time Complexity - O(log n) , Space Complexity - O(1)
    public void extractMinFromHeap(){

        // if binary min heap is empty
        if(this.lastUsedIndex==0 || this.arr==null){
            System.out.println("Binary heap is empty, can't extract");
            return;
        }
        // else if binary min heap is not empty
        System.out.println("Extracted node from binary heap is: "+this.arr[1]);

        // copy data of ddepest node into the root node
        this.arr[1] = this.arr[this.lastUsedIndex];

        // delete the deepest node
        this.arr[this.lastUsedIndex] = Integer.MIN_VALUE;

        // update lastUsedIndex
        this.lastUsedIndex--;
     
        heapifyTopToBottom();
    }
 
    // helper method to heapify from top to bottom of heap. Done after extraction of node from any heap
    // Time Complexity - O(log n) , Space Complexity - O(1)
    private void heapifyTopToBottom(){

        // Start from the root node and move to the bottom, swapping nodes if needed to maintain the min-heap property
        int parentNodeIndex = 1;
     
        while(parentNodeIndex<this.lastUsedIndex){
            int childNodeIndex=-1;
         
            int leftChildIndex = 2*parentNodeIndex;
            int rightChildIndex = (2*parentNodeIndex)+1;
         
            // if current parent node has no child
            if(leftChildIndex>this.lastUsedIndex && rightChildIndex>this.lastUsedIndex){
                break;
            }
            // if current parent node has both child
            else if(leftChildIndex<=this.lastUsedIndex && rightChildIndex <= this.lastUsedIndex){
                if(this.arr[leftChildIndex]<=this.arr[rightChildIndex]){
                    childNodeIndex = leftChildIndex;
                }
                else{
                    childNodeIndex =  rightChildIndex;
                }
            }
            // else if current parent node has only left child
            else if(leftChildIndex<=this.lastUsedIndex && rightChildIndex>this.lastUsedIndex){
                childNodeIndex=leftChildIndex;
            }
            // else if current parent node has only right child
            else{
                childNodeIndex=rightChildIndex;
            }
         
            // now as we have childnode index, compare child with parent and swap if needed
            if(this.arr[childNodeIndex]<this.arr[parentNodeIndex]){
                int temp = this.arr[parentNodeIndex];
                this.arr[parentNodeIndex]=this.arr[childNodeIndex];
                this.arr[childNodeIndex]=temp;
            }
            parentNodeIndex = childNodeIndex;
        }
    }
 
    // wrapper method to do an inorder traversal on binary min heap - LEFT ROOT RIGHT
    // Time Complexity - O(n) , Space Complexity - O(n) [As recursion is used, so internal stack will be maintained]
    public void inOrderTraversal(){

        // if binary min heap is empty
        if(this.lastUsedIndex==0 || this.arr==null){
            System.out.println("Binary heap is empty, can't traverse");
            return;
        }
        // else if binary min heap is not empty
        System.out.println("Inorder Traversal...");
        inOrderTraversal(1);
        System.out.println();
    }
 
    // overloaded method to do inOrderTraversal recursively
    private void inOrderTraversal(int currentNodeIndex){
        if(currentNodeIndex>this.lastUsedIndex){
            return;
        }
        inOrderTraversal(2*currentNodeIndex);
        System.out.print(this.arr[currentNodeIndex]+" ");
        inOrderTraversal((2*currentNodeIndex)+1);
    }
 
    // wrapper method to do a preOrder traversal on binary min heap - ROOT LEFT RIGHT
    // Time Complexity - O(n) , Space Complexity - O(n) [As recursion is used, so internal stack will be maintained]
    public void preOrderTraversal(){

          // if binary min heap is empty
        if(this.lastUsedIndex==0 || this.arr==null){
            System.out.println("Binary heap is empty, can't traverse");
            return;
        }
        // else if binary min heap is not empty
        System.out.println("preOrder Traversal...");
        preOrderTraversal(1);
        System.out.println();
    }
 
     // overloaded method to do preOrderTraversal recursively
    private void preOrderTraversal(int currentNodeIndex){
         if(currentNodeIndex>this.lastUsedIndex){
            return;
        }
        System.out.print(this.arr[currentNodeIndex]+" ");
        preOrderTraversal(2*currentNodeIndex);
        preOrderTraversal((2*currentNodeIndex)+1);
    }
 
    // wrapper method to do a postOrder traversal on binary min heap - LEFT RIGHT ROOT 
    // Time Complexity - O(n) , Space Complexity - O(n) [As recursion is used, so internal stack will be maintained]
    public void postOrderTraversal(){

         // if binary min heap is empty
        if(this.lastUsedIndex==0 || this.arr==null){
            System.out.println("Binary heap is empty, can't traverse");
            return;
        }
         // else if binary min heap is not empty
        System.out.println("postOrder Traversal...");
        postOrderTraversal(1);
        System.out.println();
    }
 
    // overloaded method to do postOrderTraversal recursively
    private void postOrderTraversal(int currentNodeIndex){
         if(currentNodeIndex>this.lastUsedIndex){
            return;
        }
        postOrderTraversal(2*currentNodeIndex);
        postOrderTraversal((2*currentNodeIndex)+1);
        System.out.print(this.arr[currentNodeIndex]+" ");
    }
 
    // method to get the root node of binary min-heap, I am not returning the node here, just printing it
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void peekBinaryHeap(){

        // if binary min heap is empty
         if(this.lastUsedIndex==0 || this.arr==null){
            System.out.println("Binary heap is empty, can't peek");
            return;
        }
        // else if binary min heap is not empty
        System.out.println("Root node of binary heap: "+this.arr[1]);
    }
 
    // method to delete complete binary min heap
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void deleteCompleteBinaryHeap(){

        // if binary min heap is empty
        if(this.lastUsedIndex==0 || this.arr==null){
            System.out.println("Binary heap is empty, can't delete");
            return;
        }
        // else if binary min heap is not empty
        this.arr = null;
        this.lastUsedIndex=0;
        System.out.println("Binary heap deleted successfully");
    }
 
    public static void main(String[] args) throws Exception {
        BinaryMinHeap binaryMinHeap = new BinaryMinHeap(15);
        binaryMinHeap.initializeArray();
        binaryMinHeap.addNode(3);
        binaryMinHeap.addNode(5);
        binaryMinHeap.addNode(8);
        binaryMinHeap.addNode(17);
        binaryMinHeap.addNode(19);
        binaryMinHeap.addNode(36);
        binaryMinHeap.addNode(40);
        binaryMinHeap.addNode(25);
        binaryMinHeap.addNode(100);
        binaryMinHeap.addNode(1);
        binaryMinHeap.levelOrderTraversal();
        binaryMinHeap.extractMinFromHeap();
        binaryMinHeap.levelOrderTraversal();
        binaryMinHeap.extractMinFromHeap();
        binaryMinHeap.levelOrderTraversal();
        binaryMinHeap.inOrderTraversal();
        binaryMinHeap.preOrderTraversal();
        binaryMinHeap.postOrderTraversal();
        binaryMinHeap.peekBinaryHeap();
        binaryMinHeap.deleteCompleteBinaryHeap();
        binaryMinHeap.levelOrderTraversal();
        binaryMinHeap.deleteCompleteBinaryHeap();
     
    }
}

/*===================================== OUTPUT ===========================================

Level Order Traversal...
1 3 8 17 5 36 40 25 100 19 
Extracted node from binary heap is: 1
Level Order Traversal...
3 5 8 17 19 36 40 25 100 
Extracted node from binary heap is: 3
Level Order Traversal...
5 17 8 25 19 36 40 100 
Inorder Traversal...
100 25 17 19 5 36 8 40 
preOrder Traversal...
5 17 25 100 19 8 36 40 
postOrder Traversal...
100 25 19 17 36 40 8 5 
Root node of binary heap: 5
Binary heap deleted successfully
Binary heap is empty, can't traverse
Binary heap is empty, can't delete

=================================================================*/
