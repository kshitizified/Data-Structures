public class LinearQueueUsingArray {
    // Array to implement Linear queue
    int[] arr;
    // variables to maintain indexes of start and end of queue
    int startOFQueue;
    int endOfQueue;
 
    // constructor
    // Creating a queue - Time Complexity - O(1), Space Complexity - O(n) , where n = size
    LinearQueueUsingArray(int size){
        this.arr=new int[size];
        this.startOFQueue=0;
        this.endOfQueue=-1;
    }
 
    // function to initialize the queue array 
    //Time Complexity - O(n), Space Complexity - O(1)
    public void initializeArray(){
        if(this.startOFQueue==0 && this.endOfQueue==-1){
            for(int i=0; i<arr.length; i++){
                this.arr[i]=Integer.MIN_VALUE;
            }
        }
    }
 
    // function to enqueue an element 'x' at the end of queue
    //Time Complexity - O(1), Space Complexity - O(1)
    public void enQueue(int x){
        // if there is no queue present in memory
        if(this.arr==null){
            System.out.println("No Queue found, Please create one first");
            return;
        }
        // if queue is full
        if(this.endOfQueue==(this.arr.length-1)){
            System.out.println("Linear queue is full, can't enqueue more");
            return;
        }
        // if queue is present and not full
        System.out.println("Enqueuing "+x+" ...");
        this.endOfQueue++;
        this.arr[this.endOfQueue]=x;
    }
 
    // function to dequeue an element from the start of queue
    //Time Complexity - O(1), Space Complexity - O(1)
    public void deQueue(){
        // if there is no queue present in memory
        if(this.arr==null){
            System.out.println("No Queue found, Please create one first");
            return;
        }
        // if queue is empty
        if(this.endOfQueue==-1 || this.startOFQueue>this.endOfQueue){
            System.out.println("Linear queue is empty, can't dequeue");
            return;
        }
        // if queue is present and not empty
        System.out.println("Dequeing "+this.arr[this.startOFQueue]+" ...");
        this.arr[this.startOFQueue]=Integer.MIN_VALUE;
        this.startOFQueue++;
    }
 
    // function to traverse the queue
    //Time Complexity - O(n), Space Complexity - O(1)
    public void traverseQueue(){
        // if there is no queue present in memory
        if(this.arr==null){
            System.out.println("No Queue found, Please create one first");
            return;
        }
        // If queue is empty
     
        // System.out.println("Start: "+this.startOFQueue+" End: "+this.endOfQueue);
        if(this.endOfQueue==-1 || this.startOFQueue>this.endOfQueue){
             System.out.println("Linear queue is empty, can't traverse");
            return; 
        }
        // else if queue is present and not empty
        System.out.println("Printing queue ...");
        for(int i=this.startOFQueue;i<=this.endOfQueue;i++){
            System.out.print(this.arr[i]+" ");
        }
        System.out.println();
    }
 
    // function to delete a queue 
    //Time Complexity - O(1), Space Complexity - O(1)
    public void deleteQueue(){
        // if there is no queue present in memory
        if(this.arr==null){
            System.out.println("No Queue Found, Please create one first");
            return;
        }
        // else if queue is present in memory
        this.arr=null;
        this.startOFQueue=0;
        this.endOfQueue=-1;
        System.out.println("Queue Deleted Successfully");
    }
 
    // function to peek the start OF Queue
    //Time Complexity - O(1), Space Complexity - O(1)
    public void peek(){
        // if there is no queue present in memory
        if(this.arr==null){
            System.out.println("No Queue found, Please create one first");
            return;
        }
        // If queue is empty
     
        // System.out.println("Start: "+this.startOFQueue+" End: "+this.endOfQueue);
        if(this.endOfQueue==-1 || this.startOFQueue>this.endOfQueue){
             System.out.println("Linear queue is empty, can't peek");
            return; 
        }
     
        // else if queue is present in memory and is not empty
        System.out.println("Top of the queue is: "+this.arr[this.startOFQueue]);
    }
 
 
    public static void main(String[] args) throws Exception {
       LinearQueueUsingArray queue = new LinearQueueUsingArray(10);
       queue.initializeArray();
       queue.peek();
       queue.enQueue(1);
       queue.enQueue(2);
       queue.enQueue(3);
       queue.traverseQueue();
       queue.deQueue();
       queue.traverseQueue();
       queue.deQueue();
       queue.traverseQueue();
       queue.enQueue(100);
       queue.traverseQueue();
       queue.deQueue();
       queue.traverseQueue();
       queue.peek();
       queue.deQueue();
       queue.traverseQueue();
       queue.deQueue();
       queue.deleteQueue();
       queue.traverseQueue();
    }
}

/* ========================= OUTPUT ============================

Linear queue is empty, can't peek
Enqueuing 1 ...
Enqueuing 2 ...
Enqueuing 3 ...
Printing queue ...
1 2 3 
Dequeing 1 ...
Printing queue ...
2 3 
Dequeing 2 ...
Printing queue ...
3 
Enqueuing 100 ...
Printing queue ...
3 100 
Dequeing 3 ...
Printing queue ...
100 
Top of the queue is: 100
Dequeing 100 ...
Linear queue is empty, can't traverse
Linear queue is empty, can't dequeue
Queue Deleted Successfully
No Queue found, Please create one first

====================================================================*/
