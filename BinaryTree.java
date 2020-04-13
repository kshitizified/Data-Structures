import java.util.LinkedList;
import java.util.Queue;

// This class represent a node of binary tree
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
 
    TreeNode(int x){
        this.data = x;
        this.left = null;
        this.right = null;
    }
 
}


public class BinaryTree {
 
    // root of binary tree
    TreeNode root;
 
    // queue used to do level order traversal, searching, finding deepest Node and deletion of a node in a binary tree
    Queue<TreeNode> q = new LinkedList<>();
 
    // function to add a new node in binary tree at the first empty space in level order traversal of binary tree
    // Time Complexity - O(n) , Space Complexity - O(n) [As we are maintiaing a queue to hold nodes]
    public void addNode(int x){
        // make a new tree node with given data 'x'
        TreeNode newNode = new TreeNode(x);
     
        // check if binary tree is empty
        if(root==null){
            // if binary tree is empty, we make newNode as root of binary tree
            root = newNode;
            return;
        }
     
        // else if binary tree is not empty, we  perform level order traversal and find the first empty place to insert 
     
        TreeNode currentNode;
        // clear the queue first, so as it does not contain any node from previous operations
        q.clear();
        // add the root node to the queue
        q.add(root);
        // we iterate till the queue is not empty
        while(q.size()!=0){
            // dequeue 
            currentNode = q.remove();
            // check if the currentNode has a left child or not, if it has left child, enqueue the left child 
            if(currentNode.left!=null){
                q.add(currentNode.left);
            }
            // else if currentNode does not have left child, Congratulations!  we have found our empty space to insert
            else if(currentNode.left==null){
                // insert new node to left and break out of loop
                currentNode.left = newNode;
                break;
            }
         
            // check if the currentNode has a right child or not, if it has right child, enqueue the right child 
            if(currentNode.right!=null){
                q.add(currentNode.right);
            }
         
             // else if currentNode does not have right child, Congratulations!  we have found our empty space to insert
            else if(currentNode.right==null){
                currentNode.right = newNode;
                break;
            }
         
        }
     
    }
 
    // function to traverse the binary tree in InOrder fashion -> LEFT ROOT RIGHT
    public void inOrderTraversal(){
        // if binary tree is empty
        if(root==null){
            System.out.println("Binary Tree is empty, can't traverse");
            return;
        }
        // else if binary tree is not empty
        System.out.println("InOrder traversal...");
        inOrderTraversal(root);
        System.out.println();
    }
 
    // overloaded function of inorderTraversal
    // Time Complexity - O(n) , Space Complexity - O(n) [As internal stack is used in recursion]
    private void inOrderTraversal(TreeNode temp){
        if(temp==null){
            return;
        }
        inOrderTraversal(temp.left);
        System.out.print(temp.data+" ");
        inOrderTraversal(temp.right);
    }
 
     // function to traverse the binary tree in PreOrder fashion -> ROOT LEFT RIGHT
    public void preOrderTraversal(){
        // if binary tree is empty
        if(root==null){
            System.out.println("Binary Tree is empty, can't traverse");
            return;
        }
        // else if binary tree is not empty
        System.out.println("PreOrder traversal...");
        preOrderTraversal(root);
        System.out.println();
    }
 
     // overloaded function of preOrderTraversal
    // Time Complexity - O(n) , Space Complexity - O(n) [As internal stack is used in recursion]
    private void preOrderTraversal(TreeNode temp){
        if(temp==null){
            return;
        }
        System.out.print(temp.data+" ");
        preOrderTraversal(temp.left);
        preOrderTraversal(temp.right);
     
    }
 
    // function to traverse the binary tree in PostOrder fashion ->  LEFT RIGHT ROOT
    public void postOrderTraversal(){
        // if binary tree is empty
        if(root==null){
            System.out.println("Binary Tree is empty, can't traverse");
            return;
        }
        // else if binary tree is not empty
        System.out.println("PostOrder traversal...");
        postOrderTraversal(root);
        System.out.println();
    }
 
    // overloaded function of postOrderTraversal
    // Time Complexity - O(n) , Space Complexity - O(n) [As internal stack is used in recursion]
    private void postOrderTraversal(TreeNode temp){
        if(temp==null){
            return;
        }
        postOrderTraversal(temp.left);
        postOrderTraversal(temp.right);
        System.out.print(temp.data+" ");
    }
 
    // function to tarverse a binary tree in level order fashion
    // Time Complexity - O(n) , Space Complexity - O(n) [As we are maintiaing a queue to hold nodes]
    public void levelOrderTraversal(){
        // if binary tree is empty
        if(root==null){
            System.out.println("Binary Tree is empty, can't traverse");
            return;
        }
        // else if binary tree is not empty:
     
        // first clear the queue, as it may contain nodes from any previous operation performed
        q.clear();
     
        // a tree node to maintain the current node being traversed
        TreeNode currentNode;
        // first add the root node to queue
        q.add(root);
        System.out.println("Level Order Traversal...");
        // then traverse the queue until it is empty
        while(q.size()!=0){
            // dequeue
            currentNode = q.remove();
         
            // check if currentNode has any left child, if yes, add them to queue 
            if(currentNode.left!=null){
                q.add(currentNode.left);
            }
         
            // check if currentNode has any right child, if yes, add them to queue
            if(currentNode.right!=null){
                q.add(currentNode.right);
            }
         
            // print the currentNode
            System.out.print(currentNode.data+" ");
        }
     
        // clear the queue used for next operation
        q.clear();
        System.out.println();
    }
 
    // function to search for a node with given data 'x' in binary tree
    // Time Complexity - O(n) , Space Complexity - O(n) [As we are maintiaing a queue to hold nodes]
    public void searchNode(int x){
        // if binary tree is empty
        if(root == null){
            System.out.println("Binary tree is empty, can't search");
            return;
        }
        // else if binary tree is not empty
        TreeNode foundNode = searchNode(root,x);
        if(foundNode==null){
            System.out.println("Node with given data : "+x+" is not present in binary tree");
            return;
        }
        System.out.println("Node with given data : "+x+" is present in binary tree");
    }
 
    // overloaded function of searchNode
    // Time Complexity - O(n) , Space Complexity - O(n) [As we are maintiaing a queue to hold nodes]
    private TreeNode searchNode(TreeNode temp, int x){
        // node to store the current node
        TreeNode currentNode;
        // add the root to the queue
        q.add(temp);
        while(q.size()!=0){
            // dequeue
            currentNode = q.remove();
            // check if the currentNode has the data you are searching for, if yes, return from here
            if(currentNode.data==x){
                return currentNode;
            }
            // else, add the left and right child, if any, of the currentNode to the queue
            else{
                if(currentNode.left!=null){
                    q.add(currentNode.left);
                }
                if(currentNode.right!=null){
                    q.add(currentNode.right);
                }
            }
         
        }
        // if not found
        return null;
    }
 
    // function to delete a node with given value 'x' in binary tree
    // Time Complexity - O(n) , Space Complexity - O(n) [As we are maintiaing a queue to hold nodes]
    public void deleteNode(int x){
        // if binary tree is empty 
        if(root==null){
            System.out.println("Binary tree is empty, can't delete");
            return;
        }
        // else if binary tree is not empty:
        
        // search for the node with given data x, whether it is present in tree or not
        TreeNode nodeToDelete = searchNode(root,x);
     
        // if node with given data is not present in binary tree
        if(nodeToDelete==null){
            System.out.println("Node with given data: "+x+" is not present in Binary Tree, hence can't delete");
            return;
        }
     
        // else if node with given data 'x' is present in binary tree:
        
        // first, we check if nodeToDelete is a leaf node
        if(nodeToDelete.left==null && nodeToDelete.right==null){
            // if only root node is present in binary tree
            if(nodeToDelete.data==root.data){
                root=null;
                System.out.println("Deletion of node with data: "+x+" is successful");
                return;
            }
         
            // else for other leaf node first we have to find the parent of this leaf node and mark the parent's link to leaf node to be deleted as null
            TreeNode parentNodeOfLeaf = root;
            q.clear();
            q.add(parentNodeOfLeaf);
            while(q.size()!=0){
                //dequeue
                parentNodeOfLeaf = q.remove();
                // check if the currentNode /parentNodeOfLeaf has any left child
                if(parentNodeOfLeaf.left !=null){
                    // if it has left child, check if its left child has the data to delete
                    if(parentNodeOfLeaf.left.data==nodeToDelete.data){
                        // if the left child has the data to delete, Hurray, we got out parent node.
                        // So, we just make the link to parent node to its left child as null inorder to delete the left child 
                        parentNodeOfLeaf.left=null;
                         break;
                    }
                    else{
                        // if the left child does not have our data to delete, we just add it to queue and go to check right child
                        q.add(parentNodeOfLeaf.left);
                    }
                }
             
                // checking right child in a similar manner
             
                // check if the currentNode /parentNodeOfLeaf has any right child
                if(parentNodeOfLeaf.right !=null){
                    // if it has right child, check if its right child has the data to delete
                    if(parentNodeOfLeaf.right.data == nodeToDelete.data){
                        // if the right child has the data to delete, Hurray, we got out parent node.
                        // So, we just make the link to parent node to its right child as null inorder to delete the right child
                        parentNodeOfLeaf.right=null;
                        break;
                    }
                    else{
                        // if the right child does not have our data to delete, we just add it to queue and continue our next iteration
                        q.add(parentNodeOfLeaf.right);
                    }
                }
             
             
            }
        }
     
        // if nodeToDelete is not a leaf node
        else{
            // We find the deepest and rightmost node of the binary tree
            TreeNode deepestNode = findDeepestNode(root);
            // we copy the data of deepestNode into nodeToDelete and then we delete the deepestNode
            int temp = deepestNode.data;
            deleteNode(deepestNode.data);
            nodeToDelete.data = temp;
            System.out.println("Deletion of node with data: "+x+" is successful");
        }   
    }
 
    // function to find the deepest rightmost node in binary tree
    // Time Complexity - O(n) , Space Complexity - O(n) [As we are maintiaing a queue to hold nodes]
    private TreeNode findDeepestNode(TreeNode temp){
        // clear the queue first, if not sure why, read above comments carefully
        q.clear();
     
        TreeNode currentNode = null;
        q.add(temp);
        while(q.size()!=0){
            // dequeue
            currentNode = q.remove();
            // add the left child if any
            if(currentNode.left!=null){
                q.add(currentNode.left);
            }
            // add the right child if any
            if(currentNode.right!=null){
                q.add(currentNode.right);
            }
         
            // when there is only the deepest node in the queue, for that iteration we will pop it as above and then the size of queue becomes 0
            // so we check if we get the size of queue as 0, we have our deepestNode in currentNode for that iteration. Right?
            if(q.size()==0){
                break;
            }
        }
        return currentNode;
    }
 
    // function to delete complete binary tree
    // Time Complexity - O(1) , Space Complexity - O(1) 
    public void deleteBinaryTree(){
        // if binary tree is empty
        if(root==null){
            System.out.println("Binary Tree is empty, can't delete");
            return;
        }
        // else if binary tree is not empty, we just make the root as null and rest all nodes will be collected by garbage collector one by one.
        root=null;
        System.out.println("Binary Tree Deleted Successfully");
    }
 
    public static void main(String[] args) throws Exception {
        BinaryTree bTree = new BinaryTree();
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

/* ============================================ OUTPUT ===========================================

InOrder traversal...
222 50 100 15 20 250 3 35 
PreOrder traversal...
20 100 50 222 15 3 250 35 
PostOrder traversal...
222 50 15 100 250 35 3 20 
Level Order Traversal...
20 100 3 50 15 250 35 222 
Node with given data : 50 is present in binary tree
Deletion of node with data: 15 is successful
Node with given data: 15 is not present in Binary Tree, hence can't delete
Level Order Traversal...
20 100 3 50 250 35 222 
Deletion of node with data: 20 is successful
Level Order Traversal...
222 100 3 50 250 35 
Binary Tree Deleted Successfully
Binary Tree is empty, can't delete

=======================================================================================*/
