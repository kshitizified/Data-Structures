// The Double linked list node / circular double linked list node
class Node{
    int data;
    Node next;
    Node prev;
    Node(int x){
        this.data=x;
        this.prev=null;
        this.next=null;
    }
}

public class CircularDoubleLinkedList {
    // head and tail references of circular double linked list
    Node head;
    Node tail;
 
    // function to add a node to the last of circular double linked list
// Time Complexity - O(1) , Space Complexity - O(1)
    public void addNode(int x){
        // create a new node
        Node newNode = new Node(x);
     
        // if linked list is empty
        if(head==null){
            head=newNode;
            tail=newNode;
            head.prev=tail;
            tail.next=head;
            return;
        }
     
        // else if linked list is not empty
        tail.next=newNode;
        newNode.prev=tail;
        tail=newNode;
        newNode.next=head;
        head.prev=tail;
     
    }
 
    // function to traverse a circular double linked list from left to right
// Time Complexity - O(n) , Space Complexity - O(1)
    public void traverseForward(){
        // if circular double linked list is empty
        if(head==null){
            System.out.println("Linked List is empty, can't traverse");
            return;
        }
        // else if circular double linked list is not empty
        Node temp=head;
        do{
            System.out.print(temp.data+" ");
            temp=temp.next;
        }while(temp!=head);
        System.out.println();
    }
 
    // function to traverse a circular double linked list from right to left
// Time Complexity - O(n) , Space Complexity - O(1)
    public void traverseBackward(){
        // if circular double linked list is empty
        if(head==null){
            System.out.println("Linked List is empty, can't traverse");
            return;
        }
        // else if circular double linked list is not empty
        Node temp=tail;
        do{
            System.out.print(temp.data+" ");
            temp=temp.prev;
        }while(temp!=tail);
        System.out.println();
    }
 
    // function to count the total number of nodes present in a circular double linked list
// Time Complexity - O(n) , Space Complexity - O(1)
    private int getCountOfNodes(){
        int count=0;
        if(head!=null){
            Node temp=head;
            do{
                count++;
                temp=temp.next;
            }while(temp!=head);
        }
        return count;
    }
 
    // function to insert a new node with given 'data' at the specified 'loc' in a circular double linked list
// Time Complexity - O(n) , Space Complexity - O(1)
    public void insertNode(int data, int loc){
        int totalNoOFNodes = getCountOfNodes();
        Node newNode = new Node(data);
     
        // check validity of loc
        if(loc<=0 || loc>totalNoOFNodes+1){
            System.out.println("Not a valid location to insert");
            return;
        }
     
        // if loc is valid
     
        // if specified location is start of circular double linked list
        if(loc==1){
            newNode.next=head;
            head.prev=newNode;
            head=newNode;
            head.prev=tail;
            tail.next=head;
            return;
        }
        // if specified location is end of circular double linked list
        else if(loc==totalNoOFNodes+1){
            newNode.prev=tail;
            newNode.next=head;
            tail.next=newNode;
            tail=newNode;
            head.prev=tail;
            return;
        }
        // if specified location is anywhere between head node and tail node of circular double linked list
        else{
            Node temp=head;
         
            // bring the temp pointer to node before the loc node
            for(int i=0;i<loc-2;i++){
                temp=temp.next;
            }
            Node nextNode=temp.next;
            // linking and unlinking
            newNode.prev=temp;
            newNode.next=nextNode;
            temp.next=newNode;
            nextNode.prev=newNode;
        }
    }
 
    // function to delete a node in circular double linked list on the basis of specified location 'loc'
// Time Complexity - O(n) , Space Complexity - O(1)
    public void deleteNodeByLoc(int loc){
        // if circular double linked list is empty
        if(head==null){
            System.out.println("Linked List is empty, can't delete");
            return;
        }
        // else if circular double linked list is not empty:
     
        // check validity of loc
        int totalNoOFNodes = getCountOfNodes();
     
        // if loc is invalid
        if(loc<=0 || loc>totalNoOFNodes){
            System.out.println("Invalid location to delete");
            return;
        }
     
        // if loc is valid:
     
        // if head node is to be deleted
        if(loc==1){
            head=head.next;
            head.prev=tail;
            tail.next=head;
            return;
        }
        // else if tail node is to be deleted
        else if(loc==totalNoOFNodes){
            tail=tail.prev;
            tail.next=head;
            head.prev=tail;
            return;
        }
        // else if node to be deleted is anywhere between head node and tail node
        else{
            Node temp = head;
            for(int i=0;i<loc-2;i++){
                temp=temp.next;
            }
            Node current = temp.next;
            temp.next=current.next;
            current.next.prev=temp;
        }
    }
 
    // function to search for a node in the circular double linked list having data 'x' and return that node if present
// Time Complexity - O(n) , Space Complexity - O(1)
    private Node searchNode(int x){
        // if circular double linked list is empty
        if(head==null){
            return null;
        }
        // else if circular double linked list is not empty
        Node temp=head;
        // iterate through the circular double linked list
        do{
            // if found while iteratiing the circular double linked list
            if(temp.data==x){
                return temp;
            }
            temp = temp.next;
        }while(temp!=head);
        // if not found
        return null;
    }
 
    // function to delete a node containing value 'val' from circular double linked list
// Time Complexity - O(n) , Space Complexity - O(1)
    public void deleteNodeByValue(int val){
        // if circular double linked list is empty
        if(head==null){
            System.out.println("Linked List is empty, can't delete");
            return;
        }
        // else if circular double linked list is not empty:
        Node nodeToDelete = searchNode(val);
        // if node to delete is not present in circular double linked list
        if(nodeToDelete==null){
            System.out.println("Node with given value is not present in linked list, hence can't delete");
            return;
        }
        // if node to delete is present in linked list
        // if node to delete is head node
        if(nodeToDelete==head){
            head=head.next;
            head.prev=tail;
            tail.next=head;
            return;
        }
        // else if node to delete is tail node
        else if(nodeToDelete==tail){
            tail=tail.prev;
            tail.next=head;
            head.prev=tail;
            return;
        }
        // else if node to delete is any node between head node and tail node
        else{
            Node previousNode = nodeToDelete.prev;
            previousNode.next=nodeToDelete.next;
            nodeToDelete.next.prev=previousNode;
        }
    }

// function to delete complete double circular linked list
    public void deleteCompleteLinkedList(){
        // if circular double linked list is already empty
        if(head==null){
            System.out.println("Linked List is already empty, can't delete");
            return;
        }
        // else if circular double linked list is not empty
     
        // delete all the previous links
        Node temp = head;
        do{
            temp.prev=null;
            temp=temp.next;
        }while(temp!=head);
     
        // unlink the tail node with head, this is done to ensure garbage collection of all nodes of the linked list.
        // If we don't do this, the last node will always point to the first node. So garbage collector will not remove the nodes
        tail.next=null;
     
        // finally mark head and tail reference as null
        head=null;
        tail=null;
     
    }
 
    public static void main(String[] args) throws Exception {
        CircularDoubleLinkedList ll = CircularDoubleLinkedList Main();
        ll.addNode(1);
        ll.addNode(2);
        ll.addNode(3);
        ll.addNode(4);
        ll.traverseForward();
        ll.traverseBackward();
        ll.insertNode(0,5);
        ll.traverseForward();
        ll.traverseBackward();
        ll.deleteNodeByLoc(3);
        ll.traverseForward();
        ll.traverseBackward();
        ll.deleteNodeByValue(2);
        ll.traverseForward();
        ll.traverseBackward();
     
    }
}

/* ======================= OUTPUT ==============================

1 2 3 4 
4 3 2 1 
1 2 3 4 0 
0 4 3 2 1 
1 2 4 0 
0 4 2 1 
1 4 0 
0 4 1 
Linked List is empty, can't traverse
Linked List is empty, can't traverse

===================================================================*/
