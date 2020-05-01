import java.util.*;
// The node of the AVL tree
// NOTE THAT here we also maintain the height of each node so as to make height adjustments easier after 
// the rotation in AVL tree
class TreeNode{
    int data;
    int height;
    TreeNode left;
    TreeNode right;
// creating a node of AVL tree using constructor
// Time Complexity - O(1) , Space Complexity - O(1)
    TreeNode(int x){
        this.data=x;
        this.height=0;
        this.left=null;
        this.right=null;
    }
}

public class AVLTree {

// Root of AVL tree
    TreeNode root;

// Function to do a level order Traversal of AVL tree
// Time Complexity - O(n) , Space Complexity - O(n) [As queue is used]
   public void levelOrderTraversal() {
//if AVL tree is empty
  if (root == null) {
   System.out.println("Tree does not exists !");
   return;
  }

// queue to do level order traversal
  Queue<TreeNode> queue = new LinkedList<TreeNode>();
  queue.add(root);

  System.out.println("Printing Level order traversal of AVL Tree...");

  while (!queue.isEmpty()) {
// dequeue
   TreeNode presentNode = queue.remove();
// print present node
   System.out.print(presentNode.data + " ");

// if present node has left child, add it to queue
   if (presentNode.left != null)
    queue.add(presentNode.left);

// if present node has right child, add it to queue
   if (presentNode.right != null)
    queue.add(presentNode.right);
  }
  
  System.out.println();

}// end of method

// Wrapper function to add a new node to AVL tree
// Time Complexity - O(log n) , Space Complexity - O(log n) [As recursion is used]
    public void addNode(int x){
        root = addNode(root,x);
    }

// Overloaded method of addNode
    private TreeNode addNode(TreeNode currentNode, int x){
       // if no element in tree
        if(currentNode==null){
            currentNode = new TreeNode(x);
        }
        else if(x<=currentNode.data){
            currentNode.left = addNode(currentNode.left,x);
        }
        else if(x>currentNode.data){
            currentNode.right = addNode(currentNode.right,x);
        }
        // from here AVL tree starts
// we check the balance at the current node first, if the balance is not right, we find whether the //left subtree is heavier 
// (left condition) or the right subtree is heavier [This logic is written in checkBalance method ]

        int balance = checkBalance(currentNode.left,currentNode.right);

// if the left node is overloaded / overcrowded - this means either tree has LL condition or LR //condition
 // Notice here is balance > 1 , as we have to check the balance at current node
        if(balance>1){

            // check if tree has LL condition
                           // Note here below we are checking with 0, as we are now not checking for the
 // disbalanced node. We already have got the disbalance node from the above check. We are 
// just trying to confirm which part of the left child of the current node is heavier, the left
 // subtree of // left child or the right subtree of left child. This is not actual balance checking,
 // where we find the disbalanced node if the node has height >1 or height < -1

            if(checkBalance(currentNode.left.left,currentNode.left.right)>0){
                currentNode= rightRotate(currentNode);
            }
            // else LR condition
            else{
                currentNode.left = leftRotate(currentNode.left);
                currentNode = rightRotate(currentNode);
            }
        }

        // if right node is overloaded or overcrowded
        else if(balance<-1){  // Notice checking with -1
            // now we have to check if it is RR condition or RL condition
            //if RR condition
            // Notice checking with 0

            if(checkBalance(currentNode.right.left,currentNode.right.right) < 0){
                currentNode = leftRotate(currentNode);
            }
            // else RL condition is there
            else{
                currentNode.right = rightRotate(currentNode.right);
                currentNode = leftRotate(currentNode);
            }
        }

        // set height of left of current node
        if(currentNode.left != null){
            currentNode.left.height = calculateHeight(currentNode.left);
        }

        // set height of right of current node
        if(currentNode.right != null){
            currentNode.right.height = calculateHeight(currentNode.right);
        }

        // set height of current node
        currentNode.height = calculateHeight(currentNode);

        return currentNode;

    }

// helper method for left rotate
    private TreeNode leftRotate(TreeNode cdn){
        TreeNode newRoot = cdn.right;
        cdn.right = newRoot.left;
        newRoot.left =cdn;

        // now some height adjustments
        cdn.height=calculateHeight(cdn);
        newRoot.height=calculateHeight(newRoot);

        return newRoot;
    }

// helper method for right rotate
    private TreeNode rightRotate(TreeNode cdn){
        TreeNode newRoot = cdn.left;
        cdn.left = newRoot.right;
        newRoot.right =cdn;

        // now some height adjustments
        cdn.height=calculateHeight(cdn);
        newRoot.height=calculateHeight(newRoot);

        return newRoot;
    }

// helper method to calculate the height of a given node. This helps in doing height adjustments
    private int calculateHeight(TreeNode currentNode){
        if(currentNode==null){
            return 0;
        }
     // calculate the MAX(left child height, right child height)
    // if left child is null its height is -1 . Same for right child

        return 1 + Math.max(((currentNode.left!=null) ? currentNode.left.height : -1),((currentNode.right!=null) ? currentNode.right.height : -1));
    }

// helper method to calculate the balance at a given node by passing it's left and right child in
 // arguments of this method. This helps in finding the disbalanced node
    private int checkBalance(TreeNode leftNode, TreeNode rightNode){
       if(leftNode==null && rightNode == null){
           return 0;
       }
       else if(leftNode==null){
           return -1 * (rightNode.height+1); // as balance will be -1-(height of right node) => -1 *[1+height of right node]
       }
       else if(rightNode==null){
           return 1 + leftNode.height;          // height of left node - (-1) => height of left node + 1
       }
       else{
           return leftNode.height - rightNode.height;
       }
    }

// Wrapper Method to delete a node having value 'x' from AVL tree
// Time Complexity - O(log n) , Space Complexity - O(log n) [As recursion is used]
    public void deleteNode(int x){
        if(root==null){
            System.out.println("AVL Tree is already empty, hence can't delete");
            return;
        }
        if(searchNode(root,x)==null){
            System.out.println("Given data= "+x+" is not present in AVL tree, Hence can't delete");
            return;
        }
        root = deleteNode(root,x);
        System.out.println("Successfully deleted: "+x+" from AVL tree");
        System.out.println();
    }

 // Method to search for a given node in AVL tree
// Time Complexity - O(log n) , Space Complexity - O(log n) [As recursion is used]
    public TreeNode searchNode(TreeNode currentNode,int x){
        if(currentNode==null){
            return null;
        }
        if(x==currentNode.data){
            return currentNode;
        }
        else if(x<currentNode.data){
            return searchNode(currentNode.left,x);
        }
        else if(x>currentNode.data){
            return searchNode(currentNode.right,x);
        }
        return currentNode;
    }

// overloaded method to delete a node
    private TreeNode deleteNode(TreeNode currentNode, int x){
        if(currentNode==null){
            return null;
        }
        else if(x < currentNode.data){
            currentNode.left = deleteNode(currentNode.left,x);
        }
        else if(x > currentNode.data){
            currentNode.right = deleteNode(currentNode.right,x);
        }
        // if currentNode had the data to delete
        else{
            // if currentNode has two children
            if((currentNode.left!=null) && (currentNode.right!=null)){
                TreeNode minNodeFromRightSubtree = findMinimumNodeFromRightSubtree(currentNode.right);
                currentNode.data = minNodeFromRightSubtree.data;
                currentNode.right = deleteNode(currentNode.right,minNodeFromRightSubtree.data);
            }
            // if currentNode has one children
            else if(currentNode.left!=null && currentNode.right==null){
                currentNode=currentNode.left;
            }
            else if(currentNode.right!=null && currentNode.left==null){
                currentNode = currentNode.right;
            }
            // if currentNode has no children
            else{
                currentNode=null;
                return currentNode; //  return if the currentNode is leaf node. As it is a leaf node, there is no need to balance it.
            }
        }

        //As the node has been deleted, same as any BST [SEE Binary Search Tree, if you didn't understand above steps], Now we will see if the tree is balanced or not.
        // if tree is not balanced, we will balance it
        int balance = checkBalance(currentNode.left,currentNode.right);

        // if tree has any left condition (if left subtree is overcrowded )
        if(balance>1){

            // check for LL
            if(checkBalance(currentNode.left.left,currentNode.left.right)>0){
                currentNode=rightRotate(currentNode);
            }

            // LR condition
            else{
                currentNode.left = leftRotate(currentNode.left);
                currentNode = rightRotate(currentNode);
            }
        }

        // if right subtree is overcrowded
        else if(balance<-1){
            // check for RR condition
            if(checkBalance(currentNode.right.left,currentNode.right.right) < 0){
                currentNode=leftRotate(currentNode);
            }

            // RL condition
            else{
                currentNode.right = rightRotate(currentNode.right);
                currentNode=leftRotate(currentNode);
            }
        }

        // now height adjustments
        if(currentNode.left!=null){
            currentNode.left.height=calculateHeight(currentNode.left);
        }

        if(currentNode.right!=null){
            currentNode.right.height=calculateHeight(currentNode.right);
        }

        currentNode.height=calculateHeight(currentNode);

        return currentNode;

    }

 

// helper method to find the node with minimum value in right subtree of given node.
    private TreeNode findMinimumNodeFromRightSubtree(TreeNode node){
        if(node==null){
            return null;
        }

        while(node.left!=null){
            node = node.left;
        }
        return node;
    }

// method to delete complete AVL tree
// Time Complexity - O(1) , Space Complexity - O(1) 
    public void deleteCompleteAVLTree(){
        if(root==null){
            System.out.println("AVL Tree doesn't exists, hence can't delete");
            return;
        }
        root=null;
        System.out.println("Successfully deleted complete AVL tree");
    }

    public static void main(String[] args) throws Exception {
        AVLTree avlTree = new AVLTree();
        avlTree.addNode(30);
        avlTree.addNode(10);
        avlTree.addNode(5);
        avlTree.addNode(3);
        avlTree.addNode(4);
        avlTree.addNode(50);
        avlTree.addNode(65);
        avlTree.addNode(1);
        avlTree.levelOrderTraversal();
        avlTree.deleteNode(4);
        avlTree.levelOrderTraversal();
        avlTree.deleteNode(65);
        avlTree.addNode(70);
        avlTree.addNode(80);
        avlTree.levelOrderTraversal();
        System.out.println();
        System.out.println("Searching...");
        System.out.println(AVLTree.searchNode(AVLTree.root,80).data);
        avlTree.deleteCompleteAVLTree();
        avlTree.deleteCompleteAVLTree();
        avlTree.levelOrderTraversal();

    }

}

/* =============================================== OUTPUT =================================================

Printing Level order traversal of AVL Tree...
10 4 50 3 5 30 65 1 
Successfully deleted: 4 from AVL tree

Printing Level order traversal of AVL Tree...
10 3 50 1 5 30 65 
Successfully deleted: 65 from AVL tree

Printing Level order traversal of AVL Tree...
10 3 50 1 5 30 70 80 

Searching...
80
Successfully deleted complete AVL tree
AVL Tree doesn't exists, hence can't delete
Tree does not exists !

===========================================================================================================*/
