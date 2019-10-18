package ProjectManagement;

public class Job implements Comparable<Job> {
	String name; 
	String project_name; 
	String user;
	int runningTime;
	int priority;
	public Job(String name, String project_name, String user, int runningTime, int priority){
		this.project_name = project_name;
		this.name = name;
		this.user = user;
		this.runningTime = runningTime;
		this.priority = priority;
	}

    @Override
    public int compareTo(Job job) {
    	if(this.priority > job.priority){
    		return 1;
    	} 
    	if(this.priority < job.priority){
    		return -1;
    	}
        return 0;
    }
}