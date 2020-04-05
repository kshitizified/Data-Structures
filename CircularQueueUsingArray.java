
public class CircularQueueUsingArray {
    // Array to implement Circular Queue
    int[] arr;
    
    // Variables to maintain the start and end of the circular queue
    int startOfQueue;
    int endOfQueue;
    
    // Creating a queue using constructor
    // Time Complexity - O(1), Space Complexity - O(n) where n = size
    CircularQueueUsingArray(int size){
        this.arr = new int[size];
        this.startOfQueue=-1;
        this.endOfQueue=-1;
    }
    
    // function to enqueue an element 'x' into the circular queue (at end end of queue)
    // Time Complexity - O(1), Space Complexity - O(1)
    public void enQueue(int x){
        // if there is no queue present in memory
        if(this.arr==null){
            System.out.println("No Queue exists, please create one first");
            return;
        }
        // check if the queue is full 
        if(this.isQueueFull()){
            System.out.println("Queue is full, can't enqueue");
            return;
        }
        // else if queue is present and not full:
        
        // initialize start of queue to 0, if we do not do this, our start will remain at -1 , same as endOfQueue, and we get our queue as full even if it is empty
        this.initializeStartOfQueue();
        
        
        // if the array is filled towards the end of queue side but there is vacant space towards the start of queue side
        if(this.endOfQueue==this.arr.length-1){
            this.endOfQueue=0;
        }
        // else if empty space is there towards the end of queue side
        else {
            // simply increment endOfQueue by 1
            this.endOfQueue++;
        }
        
        // finally, after the new position of endOfQueue is fixed, just put the data into array 
        this.arr[this.endOfQueue]=x;
        
    }
    
    // function to initialize the StartOfQueue
    // if we do not do this, our start will remain at -1 , same as endOfQueue, and we get our queue as full even if it is empty
    // Time Complexity - O(1), Space Complexity - O(1)
    private void initializeStartOfQueue(){
        if(this.startOfQueue==-1){
            this.startOfQueue=0;
        }
    }
    
    // function to check if the queue is full or not
    // Time Complexity - O(1), Space Complexity - O(1)
    private boolean isQueueFull(){
        // normal case, if start is at 0th index and end is at last index of array
        if(this.startOfQueue==0 && this.endOfQueue==this.arr.length-1){
            return true;
        }
        // if one cycle is completed i.e. if endOfQueue is just before the startOfQueue
        else if(this.endOfQueue+1 == this.startOfQueue){
            return true;
        }
        
        // If the above two conditions don't met, it means the queue is not full
        else return false;
    }
    
    // function to check if the queue is empty or not
    // Time Complexity - O(1), Space Complexity - O(1)
    private boolean isQueueEmpty(){
        if(this.endOfQueue==-1){
            return true;
        }
        else return false;
    }
    
    // function to dequeue an element from the start of a circular queue
    // Time Complexity - O(1), Space Complexity - O(1)
    public void deQueue(){
        // check if the queue is present in memory or not
        if(this.arr==null){
            System.out.println("No Queue exists, please create one first");
            return;
        }
        // if queue is present in memory, check if it is empty 
        if(this.isQueueEmpty()){
            System.out.println("Can't Dequeue as queue is already empty");
            return;
        }
        
        // else if queue is present and not empty, we perform dequeue operation
        System.out.println("Dequeuing "+this.arr[this.startOfQueue]+" ...");
        this.arr[this.startOfQueue]=Integer.MIN_VALUE;
        
        // NOW WE HAVE TO ADJUST THE POSITION OF START : 
        // if there is only one element in queue
        if(this.startOfQueue == this.endOfQueue){
            this.startOfQueue = -1;
            this.endOfQueue = -1;
        }
        
        // else if we have more than one element in queue, check the current position of start
        // if startOfQueue is already at last cell of array, we move the startOfQueue to the first cell after dequeing
        else if(this.startOfQueue == this.arr.length-1){
            this.startOfQueue=0;
        }
        
        // else if startOfQueue is not at the last cell of the array, we just increment start by 1
        else{
            this.startOfQueue++;
        }
    }
    
    
    // function to get the element from the start of circular Queue
    // Time Complexity - O(1), Space Complexity - O(1)
    public void peek(){
        // check if the queue is present in memory or not
        if(this.arr==null){
            System.out.println("No Queue exists, please create one first");
            return;
        }
        // if queue is present in memory, check if it is empty 
        if(this.isQueueEmpty()){
            System.out.println("Can't peek as queue is already empty");
            return;
        }
        // if queue is present in memory and is not empty, we just print the element at start of circular queue
        System.out.println("Element at start of queue is: "+this.arr[this.startOfQueue]);
    }
    
    // I have created this function just to see, what is the status of the array used.
    // This function do not print the elements in the ordering of queue
    // Time Complexity - O(n), Space Complexity - O(1)
    public void traverseArrayUsed(){
        // check if the array is present in memory or not
        if(this.arr==null){
            System.out.println("No Queue exists, please create one first");
            return;
        }
        // if array is present 
        System.out.println("Start: "+this.startOfQueue+" End: "+this.endOfQueue);
        for(int i=0;i<this.arr.length;i++){
            System.out.print(this.arr[i]+" ");
        }
        System.out.println();
    }
    
    // function to print the queue
    // Time Complexity - O(n), Space Complexity - O(1)
    public void traverseQueue(){
         // check if the queue is present in memory or not
        if(this.arr==null){
            System.out.println("No Queue exists, please create one first");
            return;
        }
        // if queue is present in memory, check if it is empty 
        if(this.isQueueEmpty()){
            System.out.println("Can't traverse queue as queue is already empty");
            return;
        }
        // if queue is present in memory and is not empty
        System.out.println("Traversing Queue...");
        
        // start from the startOfQueue and keep on moving to next index. If we encounter the endOfQueue, just break out of the loop.
        int i = this.startOfQueue;
        while(true){
            System.out.print(this.arr[i]+" ");
            if(i == this.endOfQueue){
                break;
            }
            i++;
            // if we reached the last cell of array but haven't encountered the endOfQueue yet, then we start from the first cell of array
            if(i==this.arr.length){
                i=0;
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) throws Exception {
        CircularQueueUsingArray circularQueue = new CircularQueueUsingArray(5);
        circularQueue.enQueue(1);
        circularQueue.enQueue(2);
        circularQueue.enQueue(3);
        circularQueue.enQueue(4);
        circularQueue.enQueue(5);
        circularQueue.traverseArrayUsed();
        circularQueue.deQueue();
        circularQueue.deQueue();
        circularQueue.deQueue();
        circularQueue.deQueue();
        circularQueue.traverseQueue();
        circularQueue.deQueue();
        circularQueue.deQueue();
        circularQueue.peek();
        circularQueue.enQueue(1);
        circularQueue.enQueue(2);
        circularQueue.enQueue(3);
        circularQueue.enQueue(4);
        circularQueue.enQueue(5);
        circularQueue.deQueue();
        circularQueue.enQueue(6);
        circularQueue.enQueue(111);
        circularQueue.deQueue();
        circularQueue.deQueue();
        circularQueue.deQueue();
        circularQueue.enQueue(111);
        circularQueue.deQueue();
        circularQueue.traverseArrayUsed();
        circularQueue.traverseQueue();
    }
}

/* ==================================== OUTPUT =================================

Start: 0 End: 4
1 2 3 4 5 
Dequeuing 1 ...
Dequeuing 2 ...
Dequeuing 3 ...
Dequeuing 4 ...
Traversing Queue...
5 
Dequeuing 5 ...
Can't Dequeue as queue is already empty
Can't peek as queue is already empty
Dequeuing 1 ...
Queue is full, can't enqueue
Dequeuing 2 ...
Dequeuing 3 ...
Dequeuing 4 ...
Dequeuing 5 ...
Start: 0 End: 1
6 111 -2147483648 -2147483648 -2147483648 
Traversing Queue...
6 111 

================================================================================*/
