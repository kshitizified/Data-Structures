public class BinaryTreeUsingArray {
    // Array to store binary tree elements
    int[] arr;
    // Variable to maintain the information of last used index of array arr
    int lastUsedIndex;
 
    // constructor creating a empty binary tree
    // Time Complexity - O(1) , Space Complexity - O(n)
    BinaryTreeUsingArray(int size){
        this.arr = new int[size];
        this.lastUsedIndex = 0;
    }
 
    // function to initialize the array with Integer.MIN_VALUE only if it is empty
    // Time Complexity - O(n) , Space Complexity - O(1)
    public void initializeArray(){
        if(this.lastUsedIndex==0){
            for(int i=0;i<this.arr.length;i++){
                this.arr[i] = Integer.MIN_VALUE;
            }
        }
    }
 
    // function to add a new element 'x' to the binary Tree
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void addNode(int x){
        // check if array/biary tree is full
        if(this.lastUsedIndex == this.arr.length-1){
            System.out.println("Binary tree is already full, can't add more node");
            return;
        }
        // if binary tree is not full, we just increment the lastUsedIndex and put the new element 'x' at index 'lastUsedIndex'
        this.lastUsedIndex++;
        this.arr[this.lastUsedIndex]=x;
     
    }
 
    // function to traverse a binary tree in level order fashion
    // Time Complexity - O(n) , Space Complexity - O(1)
    public void levelOrderTraversal(){
        // if binary tree is empty
        if(this.lastUsedIndex==0){
            System.out.println("Binary tree is empty, can't traverse");
            return;
        }
        // else if binary tree is not empty
        System.out.println("Level Order traversal...");
        for(int i=1;i<=this.lastUsedIndex;i++){
            if(this.arr[i] != Integer.MIN_VALUE){
                System.out.print(this.arr[i]+" ");
            }
        }
        System.out.println();
    }
 
     // function to traverse a binary tree in inOrder fashion - LEFT ROOT RIGHT
    // Time Complexity - O(n) , Space Complexity - O(n) - [As we are using recursion, so internal stack will be used]
    public void inOrderTraversal(){
        // if binary tree is empty
        if(this.lastUsedIndex==0){
            System.out.println("Binary tree is empty,can't traverse");
            return;
        }
        // else if binary tree is not empty
        System.out.println("InOrder Traversal...");
        inOrderTraversal(1); 
        System.out.println();
    }
 
    // overloaded function of inOrderTraversal
    private void inOrderTraversal(int currentIndex){
        if(currentIndex>this.lastUsedIndex){
            return;
        }
        inOrderTraversal(currentIndex*2);
        if(this.arr[currentIndex] != Integer.MIN_VALUE){
            System.out.print(this.arr[currentIndex]+" ");
        }
        inOrderTraversal((currentIndex*2)+1);
        return;
    }
 
    // function to traverse a binary tree in postOrder fashion - LEFT RIGHT ROOT
    // Time Complexity - O(n) , Space Complexity - O(n) - [As we are using recursion, so internal stack will be used]
    public void postOrderTraversal(){
        // if binary tree is empty
        if(this.lastUsedIndex==0){
            System.out.println("Binary tree is empty,can't traverse");
            return;
        }
         // else if binary tree is not empty
        System.out.println("Post Order Traversal...");
        postOrderTraversal(1);
        System.out.println();
    }
 
    // overloaded function of postOrderTraversal
    private void postOrderTraversal(int currentIndex){
        if(currentIndex>this.lastUsedIndex){
            return;
        }
        postOrderTraversal(2*currentIndex);
        postOrderTraversal((2*currentIndex)+1);
        if(this.arr[currentIndex] != Integer.MIN_VALUE){
            System.out.print(this.arr[currentIndex]+" ");
        }
        return;
    }
 
     // function to traverse a binary tree in preOrder fashion - ROOT LEFT RIGHT
    // Time Complexity - O(n) , Space Complexity - O(n) - [As we are using recursion, so internal stack will be used]
    public void preOrderTraversal(){
        // if binary tree is empty
        if(this.lastUsedIndex==0){
            System.out.println("Binary tree is empty,can't traverse");
            return;
        }
        // else if binary tree is not empty
        System.out.println("Pre Order Traversal...");
        preOrderTraversal(1);
        System.out.println();
    }
 
    // overloaded function of preOrderTraversal
    private void preOrderTraversal(int currentIndex){
        if(currentIndex>this.lastUsedIndex){
            return;
        }
        if(this.arr[currentIndex] != Integer.MIN_VALUE){
            System.out.print(this.arr[currentIndex]+" ");
        }
        preOrderTraversal(2*currentIndex);
        preOrderTraversal((2*currentIndex)+1);
    }
 
    // function to search for an element in binary tree with value 'x'
    // Time Complexity - O(n) , Space Complexity - O(1)
    public void searchNode(int x){
        // if binary tree is empty
        if(this.lastUsedIndex==0){
            System.out.println("Binary tree is empty,can't traverse");
            return;
        }
        // else if binary tree is not empty, we search if the node with given value exists in binary tree or not
     
        int foundNodeIndex = search(x);
        // if not found in binary tree
        if(foundNodeIndex==-1){
            System.out.println("Node with given value: "+x+" is not present in binary tree");
            return;
        }
        // if found
        System.out.println("Node with given value: "+x+" is found in binary tree");
    }
 
    // function to search for an element with given value 'x' and return its index
    // Time Complexity - O(n) , Space Complexity - O(1)
    private int search(int x){
        for(int i=1;i<=this.lastUsedIndex;i++){
            if(this.arr[i]==x){
                return i;
            }
        }
        return -1;
    }
 
    // function to delete a node from a binary tree
 
    /* Algorithm to delete a node from binary tree [Applicable to both linked list and array implementation]:
         1. If node to be deleted is a leaf node:
                [linked list implementation]:
             a) Check if leaf node is a root node, if yes delete root
             b) For other leaf nodes, fnd the parent node of leaf node and delete link between parent and leaf node to be deleted
                [Array Implementation]
            a) Simply store Integer.MIN_VALUE in leaf node to be deleted
     
        2. If node to be deleted is not a leaf node:
            [Both array and linked list implementation]
            a) Find the deepest rightmost element
            b) copy data of deepest rightmost element found into the node to be deleted
            c) finally, delete the deepest rightmost node found
         
             */
           
    // Time Complexity - O(n) , Space Complexity - O(1)     
    public void deleteNode(int x){
        // if binary tree is empty
        if(this.lastUsedIndex==0){
            System.out.println("Binary tree is empty,can't delete node");
            return;
        }
     
        // else if binary tree is not empty:
     
        // find the index of node to be deleted, if present
        int nodeToDeleteIndex = search(x);
     
        // if node to be deleted is not present in binary tree
        if(nodeToDeleteIndex==-1){
            System.out.println("Node with given value: "+x+" is not present in binary tree, hence can't delete");
            return;
        }
     
        // else if node to be deleted is present in binary tree:
     
        // check if it is a leaf node
        // ( 2*nodeToDeleteIndex) - index of left child of node to delete
        // ( 2*nodeToDeleteIndex+1) - index of right child of node to delete
        if(( 2*nodeToDeleteIndex) > this.lastUsedIndex && ( 2*nodeToDeleteIndex+1) > this.lastUsedIndex){
            // it is a leaf node
            this.arr[nodeToDeleteIndex]=Integer.MIN_VALUE;
            if(nodeToDeleteIndex==this.lastUsedIndex){
                this.lastUsedIndex--;
            }
            System.out.println("Deletion of node with value: "+x+" is successfull");
            return;
        }
        // if node to delete is not a leaf node
        else{
            // copying data to deepest rightmost node into node to delete
            // here deepest rightmost node is at index 'lastUsedIndex'
            this.arr[nodeToDeleteIndex] = this.arr[this.lastUsedIndex];
            // deleting the deepest rightmost node
            this.arr[lastUsedIndex]=Integer.MIN_VALUE;
            // finally updating lastUsedIndex
            this.lastUsedIndex--;
            System.out.println("Deletion of node with value: "+x+" is successfull");
        }
    }
 
 
    // function to delete complete binary tree
// Time Complexity - O(1) , Space Complexity - O(1) 
    public void deleteBinaryTree(){
        // if binary tree is empty
         if(this.lastUsedIndex==0){
            System.out.println("Binary tree is empty,can't delete");
            return;
        }
        // else if binary tree is not empty
        this.arr=null;
        this.lastUsedIndex=0;
        System.out.println("Binary Tree Deleted Successfully");
    }
 
    public static void main(String[] args) throws Exception {
        BinaryTreeUsingArray bTree = new BinaryTreeUsingArray(20);
        bTree.initializeArray();
     
        bTree.addNode(20);
        bTree.addNode(100);
        bTree.addNode(3);
        bTree.addNode(50);
        bTree.addNode(15);
        bTree.addNode(250);
        bTree.addNode(35);
        bTree.addNode(222);
        bTree.inOrderTraversal();
        bTree.preOrderTraversal();
        bTree.postOrderTraversal();
        bTree.levelOrderTraversal();
        bTree.searchNode(50);
        bTree.deleteNode(15);
        bTree.deleteNode(15);
        bTree.levelOrderTraversal();
        bTree.deleteNode(20);
        bTree.levelOrderTraversal();
        bTree.deleteBinaryTree();
        bTree.deleteBinaryTree();
     
    }
}

/*================================= OUTPUT ====================================

InOrder Traversal...
222 50 100 15 20 250 3 35 
Pre Order Traversal...
20 100 50 222 15 3 250 35 
Post Order Traversal...
222 50 15 100 250 35 3 20 
Level Order traversal...
20 100 3 50 15 250 35 222 
Node with given value: 50 is found in binary tree
Deletion of node with value: 15 is successfull
Node with given value: 15 is not present in binary tree, hence can't delete
Level Order traversal...
20 100 3 50 250 35 222 
Deletion of node with value: 20 is successfull
Level Order traversal...
222 100 3 50 250 35 
Binary Tree Deleted Successfully
Binary tree is empty,can't delete

===================================================================================*/
