// the linked list node
class Node{
    int data;
    Node next;
    Node(int x){
        this.data=x;
        this.next=null;
    }
}

public class CircularSingleLinkedList {
   // head and tail of the linked list
    Node head;
    Node tail;
 
// function to add a new node at last of linked list
// Time Complexity - O(1) , Space Complexity - O(1)
    public void addNode(int x){
        Node newNode = new Node(x);
     
        if(head==null){
            head=newNode;
            tail=newNode;
            tail.next=head;
            return;
        }
        // else
        tail.next=newNode;
        tail=newNode;
        tail.next=head;
    }
 
// function to traverse the linked list
// Time Complexity - O(n) , Space Complexity - O(1)
    public void traverse(){
        if(head==null){
            System.out.println("Linked list is empty");
            return;
        }
        Node temp= head;
        do {
            System.out.print(temp.data+" ");
            temp=temp.next;
        }while(temp!=head);
        System.out.println();
        System.out.println("Next node of tail or head is : "+tail.next.data);
    }
 
// function to get the total no. of nodes present in linked list
// Time Complexity - O(n) , Space Complexity - O(1)
    public int getCountOfNodes(){
        int count=0;
        if(head==null){
            return count;
        }
     
        Node temp=head;
        do{
            count++;
            temp=temp.next;
        }while(temp!=head);
     
        return count;
    }
 
// function to insert a new node on the given loc
// Time Complexity - O(n) , Space Complexity - O(1)
    public void insertNodeByLoc(int data, int loc){
        if(head==null){
            System.out.println("Linked List is empty");
            return;
        }
        // else if linked list is not empty
     
        // Validate the loc
        int noOfNodes = getCountOfNodes();
     
        if(loc<=0 || loc>noOfNodes+1){
            System.out.println("Not a valid location to insert");
            return;
        }
     
        // if loc is valid
        Node temp=head;
        Node newNode = new Node(data);
     
        // if loc is start
        if(loc==1){
            head=newNode;
            newNode.next=temp;
            tail.next=head;
            return;
        }
        // if loc is end
        else if(loc==noOfNodes+1){
            addNode(data);
        }
        // if loc is anywhere between
        else{
            // bring the temp pointer to previous node
            for(int i=0;i<loc-2;i++){
                temp = temp.next;
            }
            newNode.next=temp.next;
            temp.next=newNode;
        }
     
    }
 
// function to delete a node at the given loc
// Time Complexity - O(n) , Space Complexity - O(1)
    public void deleteNodeByLoc(int loc){
        if(head==null){
            System.out.println("Linked List is empty");
            return;
        }
     
        int noOfNodes = getCountOfNodes();
     
        // check the validity of loc
     
        if(loc<=0 || loc>noOfNodes){
            System.out.println("Not a valid location to delete");
            return;
        }
     
        // if loc is a valid location to delete
     
        // if we have to delete the head node
     
        if(loc==1){
            head=head.next;
            tail.next=head;
            return;
        }
     
     
        Node temp=head;
        Node previous = null;
        // bring temp to node to be deleted and previous to one node behind the node to be deleted
        for(int i=0;i<loc-1;i++){
            previous=temp;
            temp=temp.next;
        }
     
        previous.next=temp.next;
        if(loc==noOfNodes){
            tail=previous;
        }
    }
 
// function to delete a node having given value
// Time Complexity - O(n) , Space Complexity - O(1)
    public void deleteNodeByValue(int data){
        if(head==null){
            System.out.println("Linked list is already empty");
            return;
        }
        Node temp = head;
        Node previous = null;
     
       do{
           // if data to be deleted found in linked list
           if(temp.data==data){
               break;
           }
           // if data to be deleted is not in current node
           previous=temp;
           temp = temp.next;
       }while(temp!=head);
     
       //check if the data to be deleted is at head node
       if(head.data==temp.data && temp.data==data){
           // this means we have to delete head node
           head=head.next;
           tail.next=head;
           return;
       }
     
       // if node to be deleted is last node
       else if(temp==tail){
           previous.next=tail.next;
           tail=previous;
           return;
       }
       // if the node to be deleted is nay node in between
       else{
           previous.next=temp.next;
           return;
       }
     
     
    }
 
// function to delete complete linked list
// Time Complexity - O(1) , Space Complexity - O(1)
    public void deleteCompleteLinkedList(){
        if(head==null){
            System.out.println("Linked list is empty, can't delete");
            return;
        }
        head=null;
        tail=null;
    }

// function to search for a node with the given value in Linked List
    public void searchNode(int x){
        if(head==null){
            System.out.println("Linked List is empty, can't search");
            return;
        }
        Node temp = head;
        int found=-1;
       do{
           if(temp.data==x){
               found=0;
               break;
           }
           temp=temp.next;
       }while(temp!=head);
     
       if(found==0){
           System.out.println("Node with data: "+x+" found in Linked List");
       }
       else{
           System.out.println("Node with data: "+x+" not found in Linked List");
       }
    }
 
    public static void main(String[] args) throws Exception {
        CircularSingleLinkedList ll = new CircularSingleLinkedList();
        ll.addNode(1);
        ll.addNode(2);
        ll.addNode(3);
        ll.addNode(4);
        ll.traverse();
        System.out.println(ll.getCountOfNodes());
        ll.insertNodeByLoc(0,3);
        ll.traverse();
        ll.deleteNodeByValue(0);
        ll.traverse();
        ll.deleteNodeByLoc(3);
        ll.traverse();
        ll.searchNode(3);
        ll.searchNode(4);
        ll.deleteCompleteLinkedList();
        ll.traverse();
    }
}


/*  =========================== OUTPUT ==============================

1 2 3 4 
Next node of tail or head is : 1
4
1 2 0 3 4 
Next node of tail or head is : 1
1 2 3 4 
Next node of tail or head is : 1
1 2 4 
Next node of tail or head is : 1
Node with data: 3 not found in Linked List
Node with data: 4 found in Linked List
Linked list is empty

=========================================================================*/
