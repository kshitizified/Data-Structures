import java.util.*;

// the class task.
// It will contain start and end time of the individual tasks.
class Task{
    int startTime;
    int endTime;
    
    // constructor
    Task(int s, int e){
        this.startTime = s;
        this.endTime = e;
    }
}

public class ActivitySelectionGreedy {
    // the list to contain all tasks
    ArrayList<Task> taskList;
    
    // constructor
    ActivitySelectionGreedy(){
        this.taskList = new ArrayList<>();    
    }
    
    // helper method to sort the tasks by their end time
    private void sortTasksByEndTime(){
        // we have to write the comparator to sort these Task by the end time
        // It will tell how to compare the Task objects
        Comparator<Task> comparator = new Comparator<Task>(){
            
            // overriding COMPARE method.
            @Override
            public int compare(Task t1, Task t2){
                return t1.endTime - t2.endTime;
            }
        };
        
        // finally sorting in non-decreasing order
        Collections.sort(this.taskList,comparator);
        
    }
    
    // method to perform activity selection on given tasks list.
    public void activitySelection(){
        // first sort all the tasks in the tasks List
        sortTasksByEndTime();
        // picking the first task
        System.out.println("Task with start time: " + this.taskList.get(0).startTime + " endTime: " + this.taskList.get(0).endTime);
        
        int previousActivityEndTime = this.taskList.get(0).endTime;
        // count maximum number of tasks that can be done
        int totalActivities = 1;
        
        // for all other tasks select whih task to select
        for(int i=1;i<this.taskList.size();i++){
            Task currentTask = this.taskList.get(i);
            if(currentTask.startTime > previousActivityEndTime){
                System.out.println("Task with start time: " + currentTask.startTime + " endTime: "+ currentTask.endTime);
                previousActivityEndTime = currentTask.endTime;
                totalActivities++;
            }
        }
        
        System.out.println("Maximum number of activities that can be scheduled: " + totalActivities);
    }
    
    public static void main(String[] args) throws Exception {
        ActivitySelectionGreedy activity = new ActivitySelectionGreedy();
        
        // tasks start time
        int[] startTime = {0,3,1,5,5,8};
        // tasks end time
        int[] endTime = {6,4,2,8,7,9};
        
        // adding tasks to taskList
        for(int i=0;i<startTime.length;i++){
            activity.taskList.add(new Task(startTime[i],endTime[i]));
        }
        
        activity.activitySelection();
        
    }
}

/*===========================OUTPUT=============================

Task with start time: 1 endTime: 2
Task with start time: 3 endTime: 4
Task with start time: 5 endTime: 7
Task with start time: 8 endTime: 9
Maximum number of activities that can be scheduled: 4

===============================================================*/
