public class StackUsingArray {
    // The Array used to implement stack
    int[] arr;
    // topOfStack is used to maintain the index of the topmost element present in stack. If not present then it will store -1
    int topOfStack;
 
    // constructor
    Main(int size){
        this.arr = new int[size];
        this.topOfStack=-1;
    }
 
    // used to initialize the array for stack
    // Time Complexity - O(n), Space Complexity - O(n)
    public void initializeArray(){
        if(topOfStack==-1){
            for(int i=0;i<this.arr.length;i++){
                this.arr[i]=Integer.MIN_VALUE;
            }
        }
    }
 
    // function to push element to top of stack
    // Time Complexity - O(1), Space Complexity - O(1)
    public void push(int value){
        // check if there is stack present or not
        if(this.arr==null){
            System.out.println("No stack present, first create a stack");
            return;
        }
        // check if stack is full or not
        if(this.topOfStack==(this.arr.length-1)){
            System.out.println("Stack is full, Can't push into stack");
            return;
        }
        // if stack is empty
        this.topOfStack++;
        this.arr[this.topOfStack]=value;
    }
 
    // function to pop an element from the top of stack
    // Time Complexity - O(1), Space Complexity - O(1)
    public void pop(){
        // check if there is any stack present or not
        if(this.arr==null){
            System.out.println("No stack present, first create a stack");
            return;
        }
        // check if stack is empty
        if(this.topOfStack==-1){
            System.out.println("Stack is empty, can't pop from stack");
            return;
        }
        // if stack is not empty
        this.arr[this.topOfStack]=Integer.MIN_VALUE;
        this.topOfStack--;
    }
 
    // function to see the topmost element of stack
    // Time Complexity - O(1), Space Complexity - O(1)
    public void peek(){
        // check if there is any stack present or not
        if(this.arr==null){
            System.out.println("No stack present, first create a stack");
            return;
        }
        // check if stack is empty
        if(topOfStack==-1){
            System.out.println("Stack is empty, can't peek");
            return;
        }
        // if stack is not empty
        System.out.println("Top of the stack: "+this.arr[this.topOfStack]);
    }
 
    // function to traverse the stack array
    // Time Complexity - O(n), Space Complexity - O(1)
    public void traverseStack(){
        // check if there is any stack present or not
        if(this.arr==null){
            System.out.println("No stack present, first create a stack");
            return;
        }
        // check if stack is empty
        if(this.topOfStack==-1){
            System.out.println("Stack is empty, can't traverse");
            return;
        }
        // if stack is not empty
        System.out.println("Printing stack...");
        for(int i=0;i<=this.topOfStack;i++){
            System.out.print(this.arr[i]+" ");
        }
        System.out.println();
    }
 
    // function to delete whole stack
    // Time Complexity - O(1), Space Complexity - O(1)
    public void deleteStack(){
        // check if there is any stack present or not
        if(this.arr==null){
            System.out.println("No stack present, first create a stack");
            return;
        }
        // if stack is present
        topOfStack=-1;
        arr=null;
        System.out.println("Stack deleted successfully");
    }
 
    public static void main(String[] args) throws Exception {
        StackUsingArray stack = new StackUsingArray(5);
        stack.initializeArray();
        stack.peek();
        stack.pop();
        stack.traverseStack();
        stack.push(1);
        stack.traverseStack();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(2);
        stack.push(6);
        stack.traverseStack();
        stack.pop();
        stack.traverseStack();
        stack.peek();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.traverseStack();
        stack.deleteStack();
        stack.push(1);
        stack.traverseStack();
    }

}

/* ================== OUTPUT ====================

Stack is empty, can't peek
Stack is empty, can't pop from stack
Stack is empty, can't traverse
Printing stack...
1 
Stack is full, Can't push into stack
Printing stack...
1 2 3 4 2 
Printing stack...
1 2 3 4 
Top of the stack: 4
Stack is empty, can't pop from stack
Stack is empty, can't traverse
Stack deleted successfully
No stack present, first create a stack
No stack present, first create a stack

==================================================*/
