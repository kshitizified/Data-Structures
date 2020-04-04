// the node of linked list used to implement stack
class Node{
    int data;
    Node next;
    Node(int x){
        this.data=x;
        this.next=null;
    }
}

public class StackUsingLinkedList {
    Node head;
 
    // Function to push an element into stack
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void push(int data){
        Node newNode = new Node(data);
     
        // if linked list is empty
        if(head==null){
            head=newNode;
            return;
        }
     
        // else if linked list is not empty, we will add a new node to the start of linked list
        newNode.next=head;
        head=newNode;
     
    }
 
    // Function to pop an element from top of stack
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void pop(){
        // if linked list is empty
        if(head==null){
            System.out.println("Stack is already empty, can't pop");
            return;
        }
        // else if linked list is not empty, we remove the head node and update the new head
        System.out.println("Pop Operation Successfull - "+head.data+" popped from stack");
        head=head.next;
    }
 
    // Function to peek the top of the stack
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void peek(){
         // if linked list is empty
        if(head==null){
            System.out.println("Stack is already empty, can't peek");
            return;
        }
     
        // if linked list is not empty
        System.out.println("Top of the stack is : "+head.data);
    }
 
    // function to delete the complete stack
    // Time Complexity - O(1) , Space Complexity - O(1)
    public void deleteStack(){
        // if linked list is empty
        if(head==null){
            System.out.println("Stack is empty, can't delete");
            return;
        }
        // else if linked list is not empty
        head=null;
    }
 
    // function to traverse the stack from top to bottom as left to right in linked list
    // Time Complexity - O(n) , Space Complexity - O(1)
    public void traverseStack(){
         // if linked list is empty
        if(head==null){
            System.out.println("Stack is empty, can't traverse");
            return;
        }
        // else if linked list is not empty
        Node temp=head;
        System.out.println("Traversing Stack from top as left and bottom as right...");
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.println();
    }
 
    public static void main(String[] args) throws Exception {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        stack.pop();
        stack.peek();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.traverseStack(); 
        stack.pop();
        stack.traverseStack();
        stack.peek();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.traverseStack();
        stack.deleteStack();
        stack.push(1000);
        stack.traverseStack();
        stack.deleteStack();
        stack.traverseStack();
    }
}

/* ================ OUTPUT =========================

Stack is already empty, can't pop
Stack is already empty, can't peek
Traversing Stack from top as left and bottom as right...
5 4 3 2 1 
Pop Operation Successfull - 5 popped from stack
Traversing Stack from top as left and bottom as right...
4 3 2 1 
Top of the stack is : 4
Pop Operation Successfull - 4 popped from stack
Pop Operation Successfull - 3 popped from stack
Pop Operation Successfull - 2 popped from stack
Pop Operation Successfull - 1 popped from stack
Stack is empty, can't traverse
Stack is empty, can't delete
Traversing Stack from top as left and bottom as right...
1000 
Stack is empty, can't traverse

=====================================================*/
