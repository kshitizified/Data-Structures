// the single linked list node
class Node{
    int data;
    Node next;
    Node(int x){
        this.data=x;
        this.next=null;
    }
}

public class SingleLinkedList {
 
    // head and tail of linked list
    // we are maintaining a tail pointer too, as it helps us in inserting at the last of linked list very //easy
    Node head;
    Node tail;
 
    // function to add a new node in linked list
   // Time Complexity - O(1) , Space Complexity - O(1) 
    public void addNode(int x){
        // create a new node with data x, Time Complexity - O(1) , Space Complexity - O(1) 
        Node newNode = new Node(x);
     
        // if linked list is empty
        if(head==null){
            head=newNode;
            tail = newNode;
        }
        // if linked list is not empty
        else{
            tail.next=newNode;
            tail=newNode;
        }
    }
 
    // function to traverse the linked list
// Time Complexity - O(n) , Space Complexity - O(1)
    public void traverse(){
        // if linked list is empty
        if(head==null){
            System.out.println("Linked List is empty");
            return;
        }
     
        // else if the linked list is not empty, we take a temp pointer to head and traverse the linked list using temp pointer as we don't want to alter the head pointer
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.println();
    }
 
    // function to search for a value in linked list
// Time Complexity - O(n) , Space Complexity - O(1)
    public void search(int data){
        // if linked list is empty
        if(head==null){
            System.out.println("Linked List is empty, can't search");
            return;
        }
     
        // else if linked list is not empty
     
        // take a temp pointer
        Node temp=head;
     
        // take a found variable and assign -1 to it. -1 indicates "not found"
        int found=-1;
     
        // iterate through the linked list
        while(temp!=null){
            // if given data is present in linked list
            if(temp.data==data){
                found=0;
                break;
            }
            // if data is not found in present node then move temp pointer to next node
            temp = temp.next;
        }
     
        // if found variable's value becomes 0 , means given data is present in linked list
        if(found==0){
            System.out.println("found "+data+" in linked list");
        }
     
        // if found variab;e's value is still -1 means "not found" in linked list
        else{
            System.out.println("Not found "+data+" in linked list");
        }
     
    }
 
    // function to count number of nodes in linked list
// Time Complexity - O(n) , Space Complexity - O(1)
    public int nodeCount(){
        // take a temp pointer to head
        Node temp=head;
        // count variable stores the count the no of nodes in linked list
        int count=0;
        while(temp!=null){
            // if current node is not null, we increment count and move to next node , if any
            count++;
            temp=temp.next;
        }
     
        // finally we return the value of count
        return count;
    }
 
    // function to insert a value at given location
// Time Complexity - O(n) , Space Complexity - O(1)
    public void insertNodeByLoc(int data, int loc){
        // making a new node with given data
        Node newNode = new Node(data);
     
        // take a temp pointer to head
        Node temp=head;
     
        // count no of nodes in linked list
        int noOfNodes = nodeCount();
     
        // check if the given loc is a valid location to insert or not
        if(loc>noOfNodes+1 || loc<=0){ // greater than noOfNodes+1 because loc can be just after //last node which if noOfNodes+1.
            System.out.println("Not a valid location to insert");
            return;
        }
     
        // if we have to insert at start
        if(loc==1){
            newNode.next=head;
            head=newNode;
        }
        // if we have to insert at end
        else if(loc==noOfNodes+1){
            tail.next=newNode;
            tail=newNode;
        }
        // if we have to insert anywhere in between
        else{
        // we first bring the temp pointer just before the desired loc 
        for(int i=0;i<loc-2;i++){
            temp = temp.next;
        }
     
        // now we do the new linking
        newNode.next=temp.next;
        temp.next=newNode;
        }
     
    }
 
    // function to delete a node in linked list from the given location
// Time Complexity - O(n) , Space Complexity - O(1)
    public void deleteNodeByLoc(int loc){
        // if linked list is empty
        if(head==null){
            System.out.println("Linked list is empty, can't delete");
            return;
        }
        // else if linked list is not empty
     
        // take a temp pointer to head
        Node temp = head;
     
        // count the no. of nodes in linked list
        int noOfNodes = nodeCount();
     
        // now check the validation of given location
        if(loc>noOfNodes || loc<=0){
            // if it is not a valid location, return from this function
            System.out.println("Not a valid location to delete");
            return;
        }
     
        // if it is a valid location
     
        // if we have to delete starting node
        if(loc==1){
            head=head.next;
        }
     
        // if we have to delete any other node
        else{
            // we bring the temp pointer just before the node to delete
        for(int i=0;i<loc-2;i++){
            temp=temp.next;
        }
        // now we check if it is last node of linked list, as we have maintained a tail pointer, we have to modify tail's reference if we delete the last node
        if(loc==noOfNodes){
            temp.next=null;
            temp=tail;
        }
        // if it is not last node and any node in between
        else{
        temp.next=temp.next.next;
        }
        }
    }
 
    // function to delete a node of linked list having the given value
// Time Complexity - O(n) , Space Complexity - O(1)
    public void deleteNodeByValue(int value){
        // if linked list is already empty
        if(head==null){
            System.out.println("Linked list is empty, can't delete");
            return;
        }
     
        // else if linked list is not empty
     
        // take a temp pointer to head     
        Node temp= head;
        // take a previous node which always remains one node behind temp
        Node previousNode=null;
     
        // take a found valiable. -1 indicates "not found"
        int found=-1;
        // search for the given value and if found bring the temp pointer to the node to be deleted and previousNode pointer to one node before temp
        while(temp!=null){
            if(temp.data==value){
                found=0;
                break;
            }
            previousNode=temp;
            temp=temp.next;
        }
        // if given node not found in linked list
        if(found==-1){
            System.out.println("Node not present in linked list, hence can't delete");
            return;
        }
        // if given node found at head
        if(temp.data==head.data){
            head=head.next;
            return;
        }
       // else if given node found anywhere else, do the linking and unlinking
        previousNode.next=temp.next;
        // one extra check, if given node is found at tail we also have to modify tail's reference after we delete the given node
         if(temp.data==tail.data){
            previousNode=tail;
        }
    }
 
    // function to delete complete linked list
// Time Complexity - O(1) , Space Complexity - O(1)
    public void deleteCompleteLinkedList(){
        // if linked list is already empty
        if(head==null){
            System.out.println("Linked list is already empty");
            return;
        }
        // if linked list is not empty
        head=null;
        tail=null;
    }
 
    public static void main(String[] args) throws Exception {
        SingleLinkedList ll = new SingleLinkedList();
        ll.addNode(1);
        ll.traverse();
        ll.addNode(2);
        ll.traverse();
        ll.insertNodeByLoc(5,3);
        ll.insertNodeByLoc(15,3);
        ll.traverse();
        ll.search(1);
        ll.search(10);
        ll.deleteNodeByLoc(1);
        ll.traverse();
        ll.deleteNodeByValue(54);
        ll.traverse();
        ll.deleteCompleteLinkedList();
        ll.traverse();
    }
}
 /*  ======= Output ==============
 
1 
1 2 
1 2 15 5 
found 1 in linked list
Not found 10 in linked list
2 15 5 
Node not present in linked list, hence can't delete
2 15 5 
Linked List is empty

 ===================================== /*
