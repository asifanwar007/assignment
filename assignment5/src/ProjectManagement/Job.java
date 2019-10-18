package ProjectManagement;

public class Job implements Comparable<Job> , JobReport_{
	String name; 	//name of this jobs
	String project_name; 	//project name of this job
	String user;		//user of this job
	int runningTime;	//running time of this job
	int priority;		//prioroty of this job
	public int job_budget; 
	public int arrival_time; //global time when job starts execute
	public int completion_time; //global time when job ends excution
	public Job(String name, String project_name, String user, int runningTime, int priority){
		this.project_name = project_name;
		this.name = name;
		this.user = user;
		this.runningTime = runningTime;
		this.priority = priority;
	}
	public String user(){
		return this.user;
	}
	public String project_name(){
		return  this.project_name;
	}
	public int budget(){
		return  this.job_budget;
	}
	public int arrival_time(){
		return  this.arrival_time;
	}
	public int completion_time(){
		return this.completion_time;
	}
	@Override
	public String toString(){
		return this.name + " " + this.project_name + " " + this.user + " ";
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