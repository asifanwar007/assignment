package ProjectManagement;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import Trie.*;
import RedBlack.*;
import PriorityQueue.*;

public class Scheduler_Driver extends Thread implements SchedulerInterface {


    public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
        scheduler_driver.execute();
    }

    public void execute() throws IOException {


        File file;
        URL url = Scheduler_Driver.class.getResource("INP");
        file = new File(url.getPath());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found");
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

            switch (cmd[0]) {
                case "PROJECT":
                    handle_project(cmd);
                    break;
                case "JOB":
                    handle_job(cmd);
                    break;
                case "USER":
                    handle_user(cmd[1]);
                    break;
                case "QUERY":
                    handle_query(cmd[1]);
                    break;
                case "":
                    handle_empty_line();
                    break;
                case "ADD":
                    handle_add(cmd);
                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }


        run_to_completion();

        print_stats();

    }




    @Override
    public void run() {
        // till there are JOBS
        schedule();
    }

    public boolean execution_cycle = false;
    @Override
    public void run_to_completion() {
        while (job_count > 0){
            // System.out.println(i + "heloo");
            execution_cycle = true;
            this.handle_empty_line();
            System.out.println("System execution completed");
        }


    }

    public Trie<Project> rb_project = new Trie<Project>();
    @Override
    public void handle_project(String[] cmd) {
        System.out.println("Creating project");
        String name = cmd[1];
        int priority = Integer.parseInt(cmd[2]);
        int budget = Integer.parseInt(cmd[3]);
        Project project = new Project(name, priority, budget);
        name = name + " ";
        rb_project.insert(name, project);
    }

    public MaxHeap<Job> job_maxHeap= new MaxHeap<Job>();
    @Override
    public void handle_job(String[] cmd) {
        System.out.println("Creating job");
        String name = cmd[1]; 
        String project_name = cmd[2]; 
        String user = cmd[3];
        int runningTime = Integer.parseInt(cmd[4]);
        String temp_project_name = project_name + " ";
        TrieNode<Project> project = rb_project.search(temp_project_name);

        // System.out.println(user_array.contains(user));
        if(!user_array.contains(user)){
            System.out.println("No such user exists: " + user);
        } else if(project == null){
            System.out.println("No such project exists. " + project_name);
        } else {
            int p = project.getValue().priority;
            Job job = new Job(name, project_name, user, runningTime, p);
            job_count++;
            job_maxHeap.insert(job);
            unfinished_job_names.add(name);
        }

    }

    public ArrayList<String> user_array = new ArrayList<String>();

    @Override
    public void handle_user(String name) {
        // String name = cmd[1];
        System.out.println("Creating user");
        User user = new User(name);
        user_array.add(name);
    }

    @Override
    public void handle_query(String key) {
        System.out.println("Querying");
        if(finished_job_names.contains(key)){
            System.out.println(key + ": COMPLETED" );
        } else if(unfinished_job_names.contains(key)){
            System.out.println(key + ": NOT FINISHED" );
        } else{
            System.out.println(key + ": NO SUCH JOB");
        }



    }

    ArrayList<String> unfinished_job = new ArrayList<String>();
    ArrayList<Job> unfinished_job_for_exec = new ArrayList<Job>();
    ArrayList<String> finished_job = new ArrayList<String>();
    ArrayList<String> finished_job_names= new ArrayList<String>();
    ArrayList<String> unfinished_job_names = new ArrayList<String>();

    public int globalTime = 0;
    public int totalJobsDone = 0;
    public int totalNoOfUnfinishedJob = 0;
    public int job_count = 0;
    @Override
    public void handle_empty_line() {
        System.out.println("Running code");
        System.out.println("Remaining jobs: " + job_count);
        boolean jobNotExecuted = true;
        while(jobNotExecuted && job_count > 0){
            Job j = job_maxHeap.extractMax();
            if(j == null){
                break;
            }
            int t = j.runningTime;
            String temp_project_name = j.project_name + " ";
            TrieNode<Project> p = rb_project.search(temp_project_name);
            int b = p.getValue().budget;

            if(b >= t){
                System.out.println("Executing: " + j.name + " from: " + j.project_name);
                b = b-t;
                p.value.budget = b;
                System.out.println("Project: " + j.project_name + " budget remaining: " + b);
                globalTime = globalTime + t;
                job_count = job_count - 1;
                totalJobsDone = totalJobsDone + 1;
                String f_job= "Job{user='" + j.user + "', project='" + j.project_name +"', jobstatus=COMPLETED, execution_time=" + t + ", end_time=" + globalTime + ", name='"+j.name + "'}";
                finished_job.add(f_job);
                jobNotExecuted = false;
                finished_job_names.add(j.name);

            } else{
                unfinished_job_for_exec.add(j);
                String f_job= "Job{user='" + j.user + "', project='" + j.project_name +"', jobstatus=REQUESTED, execution_time=" + t + ", end_time=" + "null" + ", name='"+j.name + "'}";
                unfinished_job.add(f_job);
                totalNoOfUnfinishedJob = totalNoOfUnfinishedJob + 1;
                unfinished_job_names.add(j.name);
                System.out.println("Executing: " + j.name + " from: " + j.project_name);
                System.out.println("Un-sufficient budget.");
                job_count = job_count -1;

            }
        }
        if(!execution_cycle){
            System.out.println("Execution cycle completed");
        }


    }

    @Override
    public void handle_add(String[] cmd) {
        System.out.println("ADDING Budget");
        String project_name = cmd[1];
        int budget = Integer.parseInt(cmd[2]);
        String temp_project_name = project_name + " ";
        TrieNode<Project> p = rb_project.search(temp_project_name);
        p.value.budget = p.value.budget+ budget;
        int i = 0;
        ArrayList<Integer> arrr = new ArrayList<Integer>();
        for(Job e: unfinished_job_for_exec){
            if(e.project_name.equals(project_name)){
                job_maxHeap.insert(e);
                job_count++;
                arrr.add(i);
            }
            i++;
        }
        for(int j = arrr.size(); j > 0; j-- ){
            int k = arrr.get(j-1);
                unfinished_job_for_exec.remove(k);
                unfinished_job.remove(k);
                unfinished_job_names.remove(k);
            }



    }

    @Override
    public void print_stats() {
        System.out.println("--------------STATS---------------");
        System.out.println("Total jobs done: " + totalJobsDone);
        for(String c : finished_job){System.out.println(c);}
        System.out.println("------------------------");
        System.out.println("Unfinished jobs: ");
        for(String c: unfinished_job){System.out.println(c);}
        System.out.println("Total unfinished jobs: " + unfinished_job.size());
        System.out.println("--------------STATS DONE---------------");

    }

    @Override
    public void schedule() {

    }
}
