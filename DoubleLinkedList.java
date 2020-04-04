 // The Double linked list node
class Node{
    int data;
    Node next;
    Node prev;
    
    Node(int x){
        this.data=x;
        this.next=null;
        this.prev=null;
    }
}

public class DoubleLinkedList {
    
    // the head and tail of the linked list
    Node head;
    Node tail;
    
    // function to add a new node to the last of linked list
// Time Complexity - O(1), Space Complexity - O(1)
    public void addNode(int x){
        Node newNode = new Node(x);
        // if linked list is empty
        if(head==null){
            head=newNode;
            tail=newNode;
            return;
        }
        // else if linked list is not empty
        tail.next=newNode;
        newNode.prev=tail;
        tail=newNode;
    }
    
    // function to traverse the linked list from left to right
// Time Complexity - O(n), Space Complexity - O(1)
    public void traverseForward(){
        // if linked list is empty
        if(head==null){
            System.out.println("Linked list is empty");
            return;
        }
        // else if linked list is not empty
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.println();
    }
    
    // function to traverse the linked list from right to left
// Time Complexity - O(n), Space Complexity - O(1)
    public void traverseBackward(){
        // if linked list is empty
        if(head==null){
            System.out.println("Linked list is empty");
            return;
        }
        // if linked list is not empty
        Node temp=tail;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.prev;
        }
        System.out.println();
    }
    
    // function to get total no of nodes present in linked list
// Time Complexity - O(n), Space Complexity - O(1)
    private int getCountOfNodes(){
        int count=0;
        Node temp=head;
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        return count;
    }
    
    // function to insert a new node with value as 'data' in linked list at specified location 'loc'
// Time Complexity - O(n), Space Complexity - O(1)
    public void insertNodeByLoc(int data, int loc){
        
        int countOfNodes = getCountOfNodes();
        // check validity of loc
        if(loc<=0 || loc>countOfNodes+1){
            System.out.println("Not a valid location to insert");
            return;
        }
        
        // if loc is valid:
        
        Node newNode = new Node(data);
        
        
        // if insert at start
        if(loc==1){
            newNode.next=head;
            head.prev=newNode;
            head=newNode;
            return;
        }
        // if insert at end
        else if(loc==countOfNodes+1){
            tail.next=newNode;
            newNode.prev=tail;
            tail=newNode;
            return;
        }
        // if insert anywhere in between
        else{
            Node temp=head;
            // bring temp to one node before loc node
            for(int i=0;i<loc-2;i++){
                temp=temp.next;
            }
            // do the linking and unlinking
            newNode.next=temp.next;
            temp.next.prev=newNode;
            temp.next=newNode;
            newNode.prev=temp;
        }
        
    }
    
    // function to delete a node at specified location
// Time Complexity - O(n), Space Complexity - O(1)
    public void deleteNodeByLoc(int loc){
        // if linked list is empty
        if(head==null){
            System.out.println("Linked List is empty");
            return;
        }
        
        // else if linked list is not empty
        
        int countOfNodes = getCountOfNodes();
        // check if given loc is valid is not
        if(loc<=0 || loc>countOfNodes+1){
            System.out.println("Not a valid location to delete");
            return;
        }
        // if node to be deleted is head node
        if(loc==1){
            head=head.next;
            head.prev=null;
            return;
        }
        // if node to be deleted is tail node
        else if(loc==countOfNodes){
            tail=tail.prev;
            tail.next=null;
        }
        // if node to be deleted is anywhere in between
        else{
            // bring the temp node to one node before the node to be deleted
            Node temp=head;
            for(int i=0;i<loc-2;i++){
                temp=temp.next;
            }
            // here currentNode is the node to be deleted
            Node currentNode = temp.next;
            // do the linking and unlinking
            temp.next=currentNode.next;
            currentNode.next.prev=temp;
            return;
        }
        
    }
    
    // function to search for a node with given data 'x' and return that node if present
// Time Complexity - O(n), Space Complexity - O(1)
    public Node searchNode(int x){
        // if linked list is empty
        if(head==null){
            System.out.println("Linked List is empty");
            return null;
        }
        // else if linked list is not empty
        
        Node temp=head;
        // traverse through the linked list and see if the node with given data is there or not
        while(temp!=null){
            // if found then return that node
            if(temp.data==x){
                return temp;
            }
            temp=temp.next;
        }
        // if not found return null
        return null;
    }
    
    // function to delete a node only on the basis of the value given 
// Time Complexity - O(n), Space Complexity - O(1)
    public void deleteNodeByValue(int x){
        // if linked list is empty
        if(head==null){
            System.out.println("Linked list is empty");
            return;
        }
        // else if linked list is not empty
        
        // search for the node with given value
        Node nodeToDelete = searchNode(x);
        // if node with given value is not found in linked list
        if(nodeToDelete==null){
            System.out.println("Node with given data is not present in linked list");
            return;
        }
        // if node with given value is found in linked list:
        
        // if node to be deleted is head node
        if(nodeToDelete==head){
            head=head.next;
            head.prev=null;
            return;
        }
        
        // if node to be deleted is tail node
        else if(nodeToDelete==tail){
            tail = tail.prev;
            tail.next=null;
            return;
        }
        // if node to be deleted is anywhere in between head and tail node
        else{
            Node previousNode=nodeToDelete.prev;
            previousNode.next=nodeToDelete.next;
            nodeToDelete.next.prev=previousNode;
            return;
        }
        
        
    }
    
    // function to delete  complete linked list
// Time Complexity - O(n), Space Complexity - O(1)
    public void deleteCompleteLinkedList(){
        // if linked list is empty
        if(head==null){
            System.out.println("Linked List is already empty, hence can't delete Linked List");
            return;
        }
        // first we remove all the prev links
        Node temp=head;
        while(temp!=null){
            temp.prev=null;
            temp=temp.next;
        }
        // Now we make the head null adn tail null
        head=null;
        tail=null;
    }
    
    public static void main(String[] args) throws Exception {
       DoubleLinkedList ll = new DoubleLinkedList();
       ll.addNode(1);
       ll.addNode(2);
       ll.addNode(3);
       ll.addNode(4);
       ll.traverseForward();
       ll.traverseBackward();
       ll.insertNodeByLoc(0,5);
       ll.traverseForward();
       ll.traverseBackward();
       ll.deleteNodeByLoc(3);
       ll.traverseForward();
       ll.traverseBackward();
       System.out.println(ll.searchNode(1));
       ll.deleteNodeByValue(0);
       ll.traverseForward();
       ll.traverseBackward();
       ll.deleteCompleteLinkedList();
       ll.traverseForward();
       ll.traverseBackward();
       
    }
}

/* ============================== OUTPUT ===================================

1 2 3 4 
4 3 2 1 
1 2 3 4 0 
0 4 3 2 1 
1 2 4 0 
0 4 2 1 
Node@6b884d57
1 2 4 
4 2 1 
Linked list is empty
Linked list is empty

==============================================================================*/
