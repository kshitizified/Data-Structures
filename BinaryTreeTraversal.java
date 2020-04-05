// this represents a tree node
class Node{
 
    int data;
    Node left;
    Node right;
    Node(int x){
        this.data=x;
        this.left=null;
        this.right=null;
    }
 
}

public class BinaryTreeTraversal {
 
    // this represents a node of a linked list that is used to implement the queue

// Instead of making a queueNode linked list to implement a queue, we can simply use the Queue from Java Collections too. But, I am coding the queue here.

    public class queueNode{
        Node treeNode;
        queueNode next;
        queueNode(Node x){
            this.treeNode=x;
            this.next=null;
        }
    }
 
    // following two belongs to linked list implementation of the queue
    queueNode head;
    queueNode tail;
 
    // this belongs to the tree
    Node root;
 
    // enqueue operation of the queue using linked list implementation
   // Time Complexity - O(1) , Space Complexity - O(1)
    public void enqueue(Node n){
        queueNode newNode = new queueNode(n);
        if(head==null){
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
        return;
    }
 
    // dequeue operation of queue using linked list implentation
   // Time Complexity - O(1) , Space Complexity - O(1)
    public void dequeue(){
        if(head==null){
            System.out.println("queue is already empty, can't dequeue");
            return;
        }
        head = head.next;
        return;
    }
 
    // can be used to traverse the queue
 // Time Complexity - O(n) , Space Complexity - O(1)
    public void traverseQueue(){
        if(head==null){
            System.out.println("queue is empty, can't traverse");
            return;
        }
        queueNode temp = head;
        while(temp!=null){
            System.out.print(temp.treeNode.data+" ");
            temp = temp.next;
        }
        System.out.println();
        System.out.println();
    }
 
    // inorder Traversal of the tree - LEFT ROOT RIGHT
 // Time Complexity - O(n) , Space Complexity - O(n) [As recursion uses internal stack]
    public void inorderTraversal(Node temp){
        if(temp==null){
            return;
        }
        inorderTraversal(temp.left);
        System.out.print(temp.data+" ");
        inorderTraversal(temp.right);
    }
 
    // preorder traversal of the tree - ROOT LEFT RIGHT
// Time Complexity - O(n) , Space Complexity - O(n) [As recursion uses internal stack]
    public void preorderTraversal(Node temp){
        if(temp==null){
            return;
        }
        System.out.print(temp.data+" ");
        preorderTraversal(temp.left);
        preorderTraversal(temp.right);
    }
 
    // postorder traversal of the tree - LEFT RIGHT ROOT
// Time Complexity - O(n) , Space Complexity - O(n) [As recursion uses internal stack]
    public void postorderTraversal(Node temp){
        if(temp==null){
            return;
        }
        postorderTraversal(temp.left);
        postorderTraversal(temp.right);
        System.out.print(temp.data+" ");
    }
 
    // level order traversal of the tree - implemented by using queue
// Time Complexity - O(n) , Space Complexity - O(n) 
    public void levelOrderTraversal(Node temp){
        if(temp==null){
            return;
        }
        enqueue(temp); // first enqueue the root
     
        // 5.  repeat this process till queue becomes empty
        while(head!=null){
            System.out.print(head.treeNode.data+" "); //1.  print first element of queue
            if(head.treeNode.left!=null){  // 2. if left and right child are present for first element of queue, then add them to queue
                enqueue(head.treeNode.left);
            }
            if(head.treeNode.right!=null){  // 3. if left and right child are present for first element of queue, then add them to queue
                enqueue(head.treeNode.right);
            }
            dequeue();  // 4. dequeue the queue
        }
     
    }
 
    public static void main(String[] args) throws Exception {
       BinaryTreeTraversal tree = new BinaryTreeTraversal();
       tree.root= new Node(20);
       tree.root.left=new Node(100);
       tree.root.left.left = new Node(50);
       tree.root.left.right = new Node(15);
       tree.root.left.left.left = new Node(222);
       tree.root.right = new Node(3);
       tree.root.right.left = new Node(250);
       tree.root.right.right = new Node(35);
       System.out.println("Inorder traversal ...");
       tree.inorderTraversal(tree.root);
       System.out.println();
       System.out.println("Preorder traversal ...");
       tree.preorderTraversal(tree.root);
       System.out.println();
       System.out.println("Postorder traversal ...");
       tree.postorderTraversal(tree.root);
       System.out.println();
       System.out.println("Level order traversal ...");
       tree.levelOrderTraversal(tree.root);
       System.out.println();
    }
}

/* ============================ OUTPUT ================================

Inorder traversal ...
222 50 100 15 20 250 3 35 
Preorder traversal ...
20 100 50 222 15 3 250 35 
Postorder traversal ...
222 50 15 100 250 35 3 20 
Level order traversal ...
20 100 3 50 15 250 35 222 

=======================================================================*/
