import java.util.Queue;
import java.util.LinkedList;

// the binary search tree node
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
 
    // Creating a binary search tree node using constructor
    // Time Complexity - O(1) , Space Complexity - O(1)
    TreeNode(int x){
        this.data = x;
        this.left=null;
        this.right=null;
    }
}

public class BinarySearchTree {
 
    // the root of BST
    TreeNode root;
 
    // queue to do level order traversal
    Queue<TreeNode> q = new LinkedList<>();
 
    // wrapper method to add a new node with value 'data' in BST
    // Time Complexity - O(log n)  [as it traverses the BST one level at a time] , Space Complexity - O(log n) [As recursion is used and it traverses tree level wise]
    public void addNode(int data){
        root = addNode(root,data); 
    }
 
    // overloaded method to add a new node to BST
    private TreeNode addNode(TreeNode currentNode,int data){
        // if currentNode is null
        if(currentNode==null){
            // make a new node and assign it to currentNode
            currentNode = new TreeNode(data);
        }
        // if data to be inserted has value less than or equal to currentNode, we move to left of currentNode so as to maintain the property of BST
        else if(data<=currentNode.data){
            currentNode.left = addNode(currentNode.left,data);
        }
        // if data to be inserted has value more than currentNode, we move to right of currentNode so as to maintain the property of BST
        else{
            currentNode.right = addNode(currentNode.right,data);
        }
        return currentNode;
    }
 
    // wrapper method for inOrderTraversal of BST - LEFT ROOT RIGHT
    // Time Complexity - O(n) , Space Complexity - O(n) [As recursion is used, so internal stack is maintained]
    public void inOrderTraversal(){
        // if BST is empty
        if(this.root==null){
            System.out.println("Binary search Tree is empty, can't traverse");
            return;
        }
        // else if BST is not empty
        System.out.println("Inorder Traversal...");
        inOrderTraversal(root);
        System.out.println();
    }
 
    // overloaded method for inOrderTraversal of BST
    private void inOrderTraversal(TreeNode currentNode){
        if(currentNode==null){
            return;
        }
        inOrderTraversal(currentNode.left);
        System.out.print(currentNode.data+" ");
        inOrderTraversal(currentNode.right);
    }
 
     // wrapper method for preOrderTraversal of BST - ROOT LEFT RIGHT
    // Time Complexity - O(n) , Space Complexity - O(n) [As recursion is used, so internal stack is maintained]
    public void preOrderTraversal(){
        // if BST is empty
         if(this.root==null){
            System.out.println("Binary search Tree is empty, can't traverse");
            return;
        }
        // if BST is not empty
        System.out.println("PreOrder Traversal...");
        preOrderTraversal(root);
        System.out.println();
    }
 
     // overloaded method for preOrderTraversal of BST
    private void preOrderTraversal(TreeNode currentNode){
        if(currentNode==null){
            return;
        }
        System.out.print(currentNode.data+" ");
        preOrderTraversal(currentNode.left);
        preOrderTraversal(currentNode.right);
    }
 
     // wrapper method for postOrderTraversal of BST -  LEFT RIGHT ROOT
    // Time Complexity - O(n) , Space Complexity - O(n) [As recursion is used, so internal stack is maintained]
    public void postOrderTraversal(){
        // if BST is empty
         if(this.root==null){
            System.out.println("Binary search Tree is empty, can't traverse");
            return;
        }
        // if BST is not empty
        System.out.println("PostOrder Traversal...");
        postOrderTraversal(root);
        System.out.println();
    }
 
    // overloaded method for postOrderTraversal of BST
    private void postOrderTraversal(TreeNode currentNode){
        if(currentNode==null){
            return;
        }
        postOrderTraversal(currentNode.left);
        postOrderTraversal(currentNode.right);
        System.out.print(currentNode.data+" ");
    }
 
    // method to do a level order traversal on BST
    // Time Complexity - O(n) , Space Complexity - O(n) [As queue is used]
    public void levelOrderTraversal(){
        // if BST is empty
        if(root==null){
            System.out.println("Binary Search Tree is empty, can't traverse");
            return;
        }
        // else if BST is not empty:
     
        // clear the queue first, so as to reove nodes from ay previous operation
        q.clear();
     
        // add the root to queue
        TreeNode currentNode=root;
        q.add(currentNode);
        System.out.println("Level Order Traversal...");
        while(q.size()!=0){
            // dequeue
            currentNode = q.remove();
            // check if currentNode has left child, if yes, add it to queue
            if(currentNode.left!=null){
                q.add(currentNode.left);
            }
            // check if currentNode has right child, if yes, add it to queue
            if(currentNode.right!=null){
                q.add(currentNode.right);
            }
            // finally print the current node
            System.out.print(currentNode.data+" ");
        }
     
        System.out.println();
 
    }
 
    // wrapper method to search for a node having value 'x' in BST
    // Time Complexity - O(log n)  [as it traverses the BST one level at a time] , Space Complexity - O(log n) [As recursion is used and it traverses tree level wise]
    public void searchNode(int x){
        // if BST is empty
        if(root==null){
            System.out.println("Binary Search Tree is empty, can't search given node");
            return;
        }
        // if BST is not empty, search whether the node is present in BST or not
        TreeNode foundNode = searchNode(root,x);
        // if node is not in BST
        if(foundNode==null){
            System.out.println("Node with given value: "+x+" not found in binary search tree");
            return;
        }
        // else if node found in BST
        System.out.println("Node with given value: "+x+" found in binary search tree");
        System.out.println("Data: "+foundNode.data);
    }
 
    // TO REVISE - VERY IMPORTANT
    // overloaded method to search node in BST
    private TreeNode searchNode(TreeNode currentNode, int x){
        if(currentNode==null){
            return null;
        }
        else if(currentNode.data == x){
            return currentNode;
        }
        else if(x > currentNode.data){
            currentNode = searchNode(currentNode.right,x);
        }
        else {
            currentNode = searchNode(currentNode.left,x);
        }
        return currentNode;
    }
 
    // wrapper method to delete a node with given value 'x' from BST
     // Time Complexity - O(log n)  [as it traverses the BST one level at a time] , Space Complexity - O(log n) [As recursion is used and it traverses tree level wise]
    public void deleteNode(int x){
        // if BST is empty
        if(root==null){
            System.out.println("Binary Search Tree is empty, can't delete given node");
            return;
        }
        // if BST is not empty , search if the nodeToDelete is even present in the BST or not
        TreeNode nodeToDelete = searchNode(root,x);
     
        // if node to delete is not present in BST
        if(nodeToDelete==null){
            System.out.println("Node with given value: "+x+" is not present in binary search tree, hence can't delete");
            return;
        }
     
        // else if node to delete is present in BST
        root = deleteNode(root,x);
        System.out.println("Node with given data: "+x+" deleted successfully from binary search tree");
    }
 
    // TO REVISE - VERY IMPORTANT
    // overloaded method to delete a node from BST
    private TreeNode deleteNode(TreeNode currentNode, int x){
        // if value to delete is smaller than currentNode's data
        if(x < currentNode.data){
            currentNode.left = deleteNode(currentNode.left,x);
        }
        // else if value to delete is bigger than currentNode's data
        else if(x > currentNode.data){
            currentNode.right = deleteNode(currentNode.right,x);
        }
        // else if the currentNode has the data to delete
        else{
            // if currentNode / nodeToDelete has 2 childrens
            if(currentNode.left!=null && currentNode.right!=null){
                // find the minimum element from th right subtree of currentNode
                TreeNode minNodeRightSubtree = findMinFromRightSubtree(currentNode.right);
                // copy data of minNodeRightSubtree to nodeToDelete / currentNode
                currentNode.data = minNodeRightSubtree.data;
                // finally delete the minNodeRightSubtree from the right subtree of currentNode
                currentNode.right = deleteNode(currentNode.right, minNodeRightSubtree.data);
            }
            // if currentNode / nodeToDelete has only 1 children
                // if it has only left child
            else if(currentNode.left!=null && currentNode.right==null){
                currentNode = currentNode.left;
            }
                // if it has only right child
            else if(currentNode.right!=null && currentNode.left==null){
                currentNode = currentNode.right;
            }
            // if currentNode / nodeToDelete has no children
            else{
                currentNode=null;
            }
        }
        return currentNode;
    }
 
    // helper method to find the node with minimum value in right subtree
    // Time Complexity - O(log n) , Space Complexity - O(1)
    private TreeNode findMinFromRightSubtree(TreeNode currentNode){
        while(currentNode.left!=null){
            currentNode = currentNode.left;
        }
        return currentNode;
    }
 
    // method to delete the comple BST
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void deleteBinarySearchTree(){
        if(root==null){
            System.out.println("Binary search tree doesn't exists, hence can't delete it");
            return;
        }
        root = null;
        System.out.println("Binary Search Tree deleted successfully");
    }
 
    public static void main(String[] args) throws Exception {
        BinarySearchTree bst = new BinarySearchTree();
        bst.addNode(100);
        bst.addNode(80);
        bst.addNode(200);
        bst.addNode(70);
        bst.addNode(90);
        bst.addNode(150);
        bst.addNode(300);
        bst.addNode(50);
        bst.addNode(160);
        bst.addNode(400);
        bst.addNode(40);
        bst.addNode(60);
        bst.addNode(155);
        bst.addNode(170);
        bst.addNode(407);
        bst.inOrderTraversal();
        bst.preOrderTraversal();
        bst.postOrderTraversal();
        bst.levelOrderTraversal();
        bst.searchNode(4);
        bst.searchNode(24);
        bst.searchNode(170);
        bst.deleteNode(3);
        bst.levelOrderTraversal();
        bst.deleteNode(100);
        bst.levelOrderTraversal();
        bst.deleteBinarySearchTree();
        bst.deleteBinarySearchTree();
    }
}
