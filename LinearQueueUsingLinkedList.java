// Linked list node to maintain the queue
class Node{
    int data;
    Node next;
 
    // Cretaion of a queue node
    // Time Complexity - O(1) , Space Complexity - O(1)
    Node(int x){
        this.data = x;
        this.next = null;
    }
}

public class LinearQueueUsingLinkedList {
 
    // head and tail of the linked list
    Node head;
    Node tail;
 
    // function to enqueue an element 'x' to the end of queue
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void enQueue(int x){
        // create a new linked list node
        Node newNode = new Node(x);
     
        // if linked list / queue is empty
        if(head==null){
            head=newNode;
            tail=newNode;
            return;
        }
     
        // else if linked list / queue is not empty
        tail.next = newNode;
        tail = newNode;
    }
 
    // function to deQueue an element from the start of queue
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void deQueue(){
        // if queue is empty
        if(head==null){
            System.out.println("Queue is empty, can't dequeue");
            return;
        }
        // else if queue is not empty, we just update the head node to its next
        System.out.println("Dequeuing "+head.data+" ...");
        head=head.next;
    }
 
    // function to get the element at the start of queue
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void peek(){
        // if queue is empty
         if(head==null){
            System.out.println("Queue is empty, can't peek");
            return;
        }
        // else if queue is not empty
        System.out.println("Element at Start of queue is: "+head.data);
    }
 
    // function to traverse the queue from start (as left) to end (as right)
    // Time Complexity - O(n) , Space Complexity - O(1)
    public void traverseQueue(){
        // if linked list is empty
         if(head==null){
            System.out.println("Queue is empty, can't traverse");
            return;
        }
        // else if linked list is not empty
        System.out.println("Traversing Queue...");
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
        System.out.println();
    }
 
    // function to delete the entire queue
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void deleteQueue(){
        // if queue is already empty
        if(head==null){
            System.out.println("Queue is empty, can't delete");
            return;
        }
        // else if linked list is not empty;
        head=null;
        tail=null;
        System.out.println("Queue successfully deleted");
    }
 
    public static void main(String[] args) throws Exception {
        LinearQueueUsingLinkedList queue = new LinearQueueUsingLinkedList();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.traverseQueue();
        queue.deQueue();
        queue.deQueue();
        queue.peek();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.traverseQueue();
        queue.deleteQueue();
        queue.enQueue(1);
        queue.traverseQueue();
        queue.deleteQueue();
        queue.traverseQueue();
    }
}

/* ======================= OUTPUT =========================

Traversing Queue...
1 2 3 4 5 
Dequeuing 1 ...
Dequeuing 2 ...
Element at Start of queue is: 3
Dequeuing 3 ...
Dequeuing 4 ...
Dequeuing 5 ...
Queue is empty, can't dequeue
Queue is empty, can't traverse
Queue is empty, can't delete
Traversing Queue...
1 
Queue successfully deleted
Queue is empty, can't traverse

======================================================*/
