// Do not run this code in Online IDE as it may not work in an online IDE due to the use of Thread.sleep() here

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianOnTheGo {
	
	// the min-heap and the max-heap
	PriorityQueue<Integer> minHeap;
	PriorityQueue<Integer> maxHeap;
	
        // constructor
	MedianOnTheGo(){
		this.minHeap = new PriorityQueue<Integer>(); // store right half of stream of numbers

                // By default PriorityQueue in Java implements min-heap.
                // So, to implement max-heap, we need to write our own comparator by overriding the compare method
		this.maxHeap = new PriorityQueue<Integer>( new Comparator<Integer>() {
			@Override
		    public int compare(Integer a, Integer b) {
		        return b - a; 
		    }
		});  // store left half of stream of numbers
	}
	
        //method to insert a random number x into stream of numbers
	public void insert(Integer x) throws InterruptedException {
		
                // This is used just to simulate the continious flow of numbers and median calculation at every step. 
		Thread.sleep(3000);

		System.out.println("Inserting " + x +" ...");

                // If both the heaps is empty - Inserting into minHeap
		if(this.minHeap.isEmpty() && this.maxHeap.isEmpty()) {
			this.minHeap.offer(x);
		}
                // If minHeap is not empty and maxHeap is empty - Inserting into maxHeap if x is less than the root of minHeap, else inserting into minHeap
		else if(!this.minHeap.isEmpty() && this.maxHeap.isEmpty()) {
			if(x>minHeap.peek()) {
				int extractMin = this.minHeap.poll();
				this.maxHeap.offer(extractMin);
				this.minHeap.offer(x);
			}
			else {
				this.maxHeap.offer(x);
			}
		}
                // If minHeap and maxHeap both are non-empty - If x is greater than max-heap root value then it means 'x' will come in later half, so put it in minHeap. Otherwise put 'x' in maxHeap
		else {
			if(x>=this.maxHeap.peek()) {
				this.minHeap.offer(x);
			}
			else {
				this.maxHeap.offer(x);
			}
		}
                
                // Now we have to rebalance both the heaps so that their size are either equal of differ only by 1
		rebalance();
		System.out.println("Max-Heap: " + this.maxHeap);
    	        System.out.println("Min-Heap:" + this.minHeap);
                
                // calling method to calculate median after every insertion
    	        calculateMedian();
    	        System.out.println();
	}
	
        // Method to rebalance both the heaps so that their size are either equal of differ only by 1
	private void rebalance() {
                // if total number of elements are even in total,we can make their size equal
		if((this.minHeap.size() + this.maxHeap.size()) % 2 == 0) {
			if(this.maxHeap.size()>this.minHeap.size()) {
				while(this.maxHeap.size()!=this.minHeap.size()) {
					this.minHeap.offer(this.maxHeap.poll());
				}
			}
			else if(this.maxHeap.size()<this.minHeap.size()) {
				while(this.minHeap.size() != this.maxHeap.size()) {
					this.maxHeap.offer(this.minHeap.poll());
				}
			}
		}
		
	}

        // Method to calculate the median at every insert
	private void calculateMedian() {
		if(this.minHeap.size()==this.maxHeap.size()) {
			System.out.println("Median: " + (this.maxHeap.peek() + this.minHeap.peek())/2.0);
		}
		else if(this.maxHeap.size()>this.minHeap.size()) {
			System.out.println("Median: " + this.maxHeap.peek());
		}
		else System.out.println("Median: " + this.minHeap.peek());
	}
	
    public static void main(String[] args) throws Exception {
    	
    	MedianOnTheGo m = new MedianOnTheGo();
    	m.insert(10);
    	m.insert(3);
    	m.insert(5);

    	m.insert(2);
    	m.insert(1);
    	m.insert(2);
    	m.insert(3);
    	
    	m.insert(5);m.insert(15);m.insert(1);m.insert(3);
    	
    	m.insert(2);m.insert(8);m.insert(7);m.insert(9);
    	
    	m.insert(10);m.insert(6);m.insert(11);m.insert(4);
        
    }
}

/*=============================OUTPUT===============================

Inserting 10 ...
Max-Heap: []
Min-Heap:[10]
Median: 10

Inserting 3 ...
Max-Heap: [3]
Min-Heap:[10]
Median: 6.5

Inserting 5 ...
Max-Heap: [3]
Min-Heap:[5, 10]
Median: 5

Inserting 2 ...
Max-Heap: [3, 2]
Min-Heap:[5, 10]
Median: 4.0

Inserting 1 ...
Max-Heap: [3, 2, 1]
Min-Heap:[5, 10]
Median: 3

Inserting 2 ...
Max-Heap: [2, 2, 1]
Min-Heap:[3, 10, 5]
Median: 2.5

Inserting 3 ...
Max-Heap: [2, 2, 1]
Min-Heap:[3, 3, 5, 10]
Median: 3

Inserting 5 ...
Max-Heap: [3, 2, 1, 2]
Min-Heap:[3, 5, 5, 10]
Median: 3.0

Inserting 15 ...
Max-Heap: [3, 2, 1, 2]
Min-Heap:[3, 5, 5, 10, 15]
Median: 3

Inserting 1 ...
Max-Heap: [3, 2, 1, 2, 1]
Min-Heap:[3, 5, 5, 10, 15]
Median: 3.0

Inserting 3 ...
Max-Heap: [3, 2, 1, 2, 1]
Min-Heap:[3, 5, 3, 10, 15, 5]
Median: 3

Inserting 2 ...
Max-Heap: [3, 2, 2, 2, 1, 1]
Min-Heap:[3, 5, 3, 10, 15, 5]
Median: 3.0

Inserting 8 ...
Max-Heap: [3, 2, 2, 2, 1, 1]
Min-Heap:[3, 5, 3, 10, 15, 5, 8]
Median: 3

Inserting 7 ...
Max-Heap: [3, 2, 3, 2, 1, 1, 2]
Min-Heap:[3, 5, 5, 7, 15, 10, 8]
Median: 3.0

Inserting 9 ...
Max-Heap: [3, 2, 3, 2, 1, 1, 2]
Min-Heap:[3, 5, 5, 7, 15, 10, 8, 9]
Median: 3

Inserting 10 ...
Max-Heap: [3, 3, 3, 2, 1, 1, 2, 2]
Min-Heap:[5, 7, 5, 9, 15, 10, 8, 10]
Median: 4.0

Inserting 6 ...
Max-Heap: [3, 3, 3, 2, 1, 1, 2, 2]
Min-Heap:[5, 6, 5, 7, 15, 10, 8, 10, 9]
Median: 5

Inserting 11 ...
Max-Heap: [5, 3, 3, 3, 1, 1, 2, 2, 2]
Min-Heap:[5, 6, 8, 7, 11, 10, 15, 10, 9]
Median: 5.0

Inserting 4 ...
Max-Heap: [5, 4, 3, 3, 3, 1, 2, 2, 2, 1]
Min-Heap:[5, 6, 8, 7, 11, 10, 15, 10, 9]
Median: 5

===================================================================*/
