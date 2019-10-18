package ProjectManagement;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import Trie.*;
import RedBlack.*;
import PriorityQueue.*;

public class Scheduler_Driver extends Thread implements SchedulerInterface {


    public static void main(String[] args) throws IOException {
//

        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
        File file;
        if (args.length == 0) {
            URL url = Scheduler_Driver.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File commandFile) throws IOException {


        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(commandFile));

            String st;
            while ((st = br.readLine()) != null) {
                String[] cmd = st.split(" ");
                if (cmd.length == 0) {
                    System.err.println("Error parsing: " + st);
                    return;
                }
                String project_name, user_name;
                Integer start_time, end_time;

                long qstart_time, qend_time;

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
                    case "": // HANDLE EMPTY LINE
                        handle_empty_line();
                        break;
                    case "ADD":
                        handle_add(cmd);
                        break;
                    //--------- New Queries
                    case "NEW_PROJECT":
                    case "NEW_USER":
                    case "NEW_PROJECTUSER":
                    case "NEW_PRIORITY":
                        timed_report(cmd);
                        break;
                    case "NEW_TOP":
                        qstart_time = System.nanoTime();
                        timed_top_consumer(Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    case "NEW_FLUSH":
                        qstart_time = System.nanoTime();
                        timed_flush( Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    default:
                        System.err.println("Unknown command: " + cmd[0]);
                }

            }


            run_to_completion();
            print_stats();

        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. " + commandFile.getAbsolutePath());
        } catch (NullPointerException ne) {
            ne.printStackTrace();

        }
    }

    @Override
    public ArrayList<JobReport_> timed_report(String[] cmd) {
        long qstart_time, qend_time;
        ArrayList<JobReport_> res = null;
        switch (cmd[0]) {
            case "NEW_PROJECT":
                qstart_time = System.nanoTime();
                res = handle_new_project(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_USER":
                qstart_time = System.nanoTime();
                res = handle_new_user(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));

                break;
            case "NEW_PROJECTUSER":
                qstart_time = System.nanoTime();
                res = handle_new_projectuser(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_PRIORITY":
                qstart_time = System.nanoTime();
                res = handle_new_priority(cmd[1]);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
        }

        return res;
    }

    @Override
    public ArrayList<UserReport_> timed_top_consumer(int top) {
        ArrayList<UserReport_> ans_arr1 = new ArrayList<UserReport_>();
        ArrayList<User> ans_arr = userType_array;
        for(int i = 0; i < ans_arr.size(); i++){
            // System.out.println("hiello world");
            User c = ans_arr.get(i);
            if(c.consumed == 0)
                ans_arr.remove(i);
            if(c.consumed != 0){
                for(int  j = i+1; j<ans_arr.size(); j++){
                    User v = ans_arr.get(j);
                    if(c.consumed < v.consumed) {
                        User temp = ans_arr.get(i);
                        ans_arr.set(i, ans_arr.get(j));
                        ans_arr.set(j, temp);
                    } else if(c.consumed == v.consumed){
                        if(c.latest_jobs_completion_time < v.latest_jobs_completion_time){
                            User temp = ans_arr.get(i);
                            ans_arr.set(i, ans_arr.get(j));
                            ans_arr.set(j, temp);  
                        }
                    }
                }
            }

        }
        for(User c: ans_arr)
            ans_arr1.add(c);
            // System.out.println(c.toString());}

        return ans_arr1;
    }



    @Override
    public void timed_flush(int waittime) { //todo 
        // ArrayList<Job> jobWhichisNotExecuted = new ArrayList<Job>();
        // ArrayList<Job> jobWhichisExecuted = new ArrayList<Job>();
        ArrayList<String> ans_arr = new ArrayList<String>();
        MaxHeap<Job> projectExecuted = new MaxHeap<Job>();
        MaxHeap<Job> projectUnexecuted = new MaxHeap<Job>();
        Job waited_job = job_maxHeap.extractMax();
        int no_of_waited_of_job = 0;
        // System.out.println("timed fiadfljka;ldf");
        int k = 0;
        while(waited_job != null){
            // System.out.println(waited_job);
            // job_count--;
            // System.out.println(k++);
            int waited_time = globalTime - waited_job.arrival_time;
            if(waited_time >= waittime){
                String temp_user_name = waited_job.user;
                // System.out.println("helo");
                TrieNode<Project> temp_project = rb_project.search(temp_user_name);
                if(temp_project != null && temp_project.value.budget >= waited_job.runningTime){
                    // waited_job.job_budget = 9999;
                    projectExecuted.insert(waited_job);
                    no_of_waited_of_job++;
                //     job_count--;

                // } else{projectUnexecuted.insert(waited_job); }

            } else{projectUnexecuted.insert(waited_job);}
        }else{
            projectUnexecuted.insert(waited_job);
        }
            waited_job = job_maxHeap.extractMax();
        }
        int i_fJobL = finished_job.size();
        int un_fJobL = unfinished_job.size();
        // waited_job = projectExecuted.extractMax();
        waited_job = projectExecuted.extractMax();
        while(waited_job != null){
            Job j = waited_job;
            int t = j.runningTime;
            String temp_project_name = j.project_name;
            TrieNode<Project> p = rb_project.search(temp_project_name);
                job_count = job_count - 1;
            if(p == null){
                break;
            }
            int b = p.getValue().budget;

            if(b >= t){
                // System.out.println("Executing: " + j.name + " from: " + j.project_name);
                b = b-t;
                p.value.budget = b;
                // System.out.println("Project: " + j.project_name + " budget remaining: " + b);
                globalTime = globalTime + t;
                j.completion_time = globalTime;

                String usr = j.user;
                for(User t_user : userType_array){
                    if(t_user.name.equals(usr)){
                        t_user.consumed = t_user.consumed + t; 
                        t_user.latest_jobs_completion_time = t; break;

                    }

                }

                totalJobsDone = totalJobsDone + 1;
                String f_job= "Job{user='" + j.user + "', project='" + j.project_name +"', jobstatus=COMPLETED, execution_time=" + t + ", end_time=" + globalTime + ", name='"+j.name + "'}";
                finished_job.add(f_job);
                finished_job_executed.add(j);
                finished_job_names.add(j.name);

            } else{
                unfinished_job_for_exec.add(j);
                String f_job= "Job{user='" + j.user + "', project='" + j.project_name +"', jobstatus=REQUESTED, execution_time=" + t + ", end_time=" + "null" + ", name='"+j.name + "'}";
                unfinished_job.add(f_job);
                totalNoOfUnfinishedJob = totalNoOfUnfinishedJob + 1;
                unfinished_job_names.add(j.name);
                // System.out.println("Executing: " + j.name + " from: " + j.project_name);
                // System.out.println("Un-sufficient budget.");

            }
        
        }
        // System.out.println(" hello brother");
        job_maxHeap = projectUnexecuted;
        // for (int i = i_fJobL; i < finished_job.size(); i++){
        //     ans_arr.add(finished_job.get(i));
        // }
        // for (int i = un_fJobL; i < unfinished_job.size(); i++){
        //     ans_arr.add(unfinished_job.get(i));
        // }
        // for(String c: finished_job) System.out.println(c);
        // for(String c: unfinished_job) System.out.println(c);
        


        // Job neief= projectUnexecuted.extractMax();
        // System.out.println("0-------------------------------------------");
        // while(neief != null){
        //     System.out.println(neief);
        //     // System.out.println("heklasldfjao");
        //     neief = projectExecuted.extractMax();
        // }
        // System.out.println("0-------------------------------------------");
        }
    

       


/*---------------------------------------------------------*/
/*------------------------*/
/*---------------------*/
/*--------------*/
    private ArrayList<JobReport_> handle_new_priority(String s) {
        int t2 = Integer.parseInt(s);
        ArrayList<JobReport_> ans_arr = new ArrayList<JobReport_>();
        for(Job c: unfinished_job_for_exec){
            int t_priority = c.priority;
            if(t2 <= t_priority){
                ans_arr.add(c);
            }
        }


        return ans_arr;
    }

    public void sortJob(ArrayList<Job> arr) {
        int n = arr.size(); 
        for (int i = n / 2 - 1; i >= 0; i--) 
            sortJobRecHeap(arr, n, i); 
  
        for (int i=n-1; i>=0; i--) {
            Job temp = arr.get(0); 
            arr.set(0, arr.get(i)); 
            arr.set(i, temp); 
            sortJobRecHeap(arr, i, 0); 
        } 
    } 
  
    public void sortJobRecHeap(ArrayList<Job> arr, int n, int i) 
    { 
        int largest = i; 
        int l = 2*i + 1;  
        int r = 2*i + 2;  
        if (l < n && arr.get(l).completion_time > arr.get(largest).completion_time) 
            largest = l; 
  
        if (r < n && arr.get(l).completion_time > arr.get(largest).completion_time) 
            largest = r; 
  
        if (largest != i) {
            Job swap = arr.get(i); 
            arr.set(i, arr.get(largest)); 
            arr.set(largest, swap); 
  
            sortJobRecHeap(arr, n, largest); 
        } 
    } 

    private ArrayList<JobReport_> handle_new_projectuser(String[] cmd) { //still to do
        String p = cmd[1];
        String p_user = cmd[2];
        int t1 = Integer.parseInt(cmd[3]);
        int t2 = Integer.parseInt(cmd[4]);
        ArrayList<JobReport_> ans_arr1 = new ArrayList<JobReport_>();
        ArrayList<Job> ans_arr = new ArrayList<Job>();
        for(Job c: finished_job_executed){
            String t_user = c.user;
            String t_proj = c.project_name;
            int t_arrival_time = c.arrival_time;
            if(t_user.equals(p_user) && p.equals(t_proj) && t_arrival_time >= t1 && t_arrival_time <= t2){
                ans_arr.add(c);
            }
        }
        this.sortJob(ans_arr);
        for(Job c: ans_arr)
            ans_arr1.add(c);
        for(Job c: unfinished_job_for_exec){
            String t_user = c.user;
            String t_proj = c.project_name;
            int t_arrival_time = c.arrival_time;
            if(t_user.equals(p_user) && p.equals(t_proj) && t_arrival_time >= t1 && t_arrival_time <= t2){
                ans_arr1.add(c);
            }
        }


        return ans_arr1;
    }

    private ArrayList<JobReport_> handle_new_user(String[] cmd) {
        String p_user = cmd[1];
        int t1 = Integer.parseInt(cmd[2]);
        int t2 = Integer.parseInt(cmd[3]);
        ArrayList<JobReport_> ans_arr = new ArrayList<JobReport_>();
        for(Job c: total_job){
            String t_user = c.user;
            String t_proj = c.project_name;
            int t_arrival_time = c.arrival_time;
            if(t_user.equals(p_user) && t_arrival_time >= t1 && t_arrival_time <= t2){
                ans_arr.add(c);
            }
        }
        return ans_arr;
    }

    private ArrayList<JobReport_> handle_new_project(String[] cmd) {
        String p_user = cmd[1];
        int t1 = Integer.parseInt(cmd[2]);
        int t2 = Integer.parseInt(cmd[3]);
        ArrayList<JobReport_> ans_arr = new ArrayList<JobReport_>();
        for(Job c: total_job){
            String t_user = c.user;
            String t_proj = c.project_name;
            int t_arrival_time = c.arrival_time;
            if(t_proj.equals(p_user) && t_arrival_time >= t1 && t_arrival_time <= t2){
                ans_arr.add(c);
            }
        }
        return ans_arr;
    }
    
/*----------Starting here for timed Code------------------*/
    /*------------------------------------------------*/
        /*---------------------------------------*/
            /*----------------------------*/
    // public ArrayList<String> user_array9 = new ArrayList<String>();
    // public ArrayList<User> userType_array9 = new ArrayList<User>();
    public void timed_handle_user(String name){

        User user = new User(name);
        user_array.add(name);
        userType_array.add(user);

        // System.out.println("inside user ");

    }

    // public MaxHeap<Job> job_maxHeap9= new MaxHeap<Job>();
    // public ArrayList<Job> total_job9 = new ArrayList<Job>();
    public void timed_handle_job(String[] cmd){
        String name = cmd[1]; 
        String project_name = cmd[2]; 
        String user = cmd[3];
        int runningTime = Integer.parseInt(cmd[4]);
        String temp_project_name = project_name;
        TrieNode<Project> project = rb_project.search(temp_project_name);

        // System.out.println(user_array.contains(user));
        boolean user_present = false;
        for(User c: userType_array){
            if(c.name.equals(user)){
                user_present = true;
                break;
            }

        }
        if(user_present){
            // System.out.println("No such user exists: " + user);
        } else if(project == null){
            // System.out.println("No such project exists. " + project_name);
        } else {
            int p = project.getValue().priority;
            int budget_to_store_in_job = project.getValue().budget;
            Job job = new Job(name, project_name, user, runningTime, p);
            job.arrival_time = globalTime;
            job.job_budget = budget_to_store_in_job;
            job_count++;
            job_maxHeap.insert(job);
            total_job.add(job);
            unfinished_job_names.add(name);
        }
    }
    // ArrayList<String> unfinished_job9 = new ArrayList<String>();

    // ArrayList<Job> unfinished_job_for_exec9 = new ArrayList<Job>();
    // ArrayList<Job> finished_job_executed9 = new ArrayList<Job>();

    // ArrayList<String> finished_job9 = new ArrayList<String>();
    // ArrayList<String> finished_job_names9 = new ArrayList<String>();
    // ArrayList<String> unfinished_job_names9 = new ArrayList<String>();

    // public int globalTime9 = 0;
    // public int totalJobsDone9 = 0;
    // public int totalNoOfUnfinishedJob9 = 0;
    // public int job_count9 = 0;

    // public Trie<Project> rb_project9 = new Trie<Project>();
    public void timed_handle_project(String[] cmd){
        // System.out.println("Creating project");
        String name = cmd[1];
        int priority = Integer.parseInt(cmd[2]);
        // System.out.println("inside timed project");
        int budget = Integer.parseInt(cmd[3]);
        Project project = new Project(name, priority, budget);
        name = name;
        rb_project.insert(name, project);

    }
    public void timed_run_to_completion(){
        boolean jobNotExecuted = true;
        // System.out.println(job_count + " hdfkjahdkahfkadshfasl");
        while(jobNotExecuted && job_count > 0){
            Job j = job_maxHeap.extractMax();
            if(j == null){
                job_count = 0;
                break;
            }
            // System.out.println("helo");
            int t = j.runningTime;
            String temp_project_name = j.project_name;
            TrieNode<Project> p = rb_project.search(temp_project_name);
            if(p == null){
                break;
            }
            int b = p.getValue().budget;

            if(b >= t){
                // System.out.println("Executing: " + j.name + " from: " + j.project_name);
                b = b-t;
                p.value.budget = b;
                // System.out.println("Project: " + j.project_name + " budget remaining: " + b);
                globalTime = globalTime + t;
                j.completion_time = globalTime;

                String usr = j.user;
                for(User t_user : userType_array){
                    if(t_user.name.equals(usr)){
                        t_user.consumed = t_user.consumed + t; 
                        t_user.latest_jobs_completion_time = t; break;

                    }

                }

                job_count = job_count - 1;
                totalJobsDone = totalJobsDone + 1;
                String f_job= "Job{user='" + j.user + "', project='" + j.project_name +"', jobstatus=COMPLETED, execution_time=" + t + ", end_time=" + globalTime + ", name='"+j.name + "'}";
                finished_job.add(f_job);
                finished_job_executed.add(j);
                jobNotExecuted = false;
                finished_job_names.add(j.name);

            } else{
                unfinished_job_for_exec.add(j);
                String f_job= "Job{user='" + j.user + "', project='" + j.project_name +"', jobstatus=REQUESTED, execution_time=" + t + ", end_time=" + "null" + ", name='"+j.name + "'}";
                unfinished_job.add(f_job);
                totalNoOfUnfinishedJob = totalNoOfUnfinishedJob + 1;
                unfinished_job_names.add(j.name);
                // System.out.println("Executing: " + j.name + " from: " + j.project_name);
                // System.out.println("Un-sufficient budget.");
                job_count = job_count -1;

            }
        }
        

    
    }

                    /*-----------*/
        /*-------------------------------------*/
    /*----------------------------------------------*/
/*--------------------------------------------------------*/
/*----------------------Ending----------------------------*/
/*----------------------------------------------------------*/






    public void schedule() {
            execute_a_job();
    }

    public boolean execution_cycle = false;
    public void run_to_completion() {
        while (job_count > 0){
            // System.out.println(i + "heloo");
            execution_cycle = true;
            this.handle_empty_line();
            System.out.println("System execution completed");
        }

    }

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

    public void handle_add(String[] cmd) {
        System.out.println("ADDING Budget");
        String project_name = cmd[1];
        int budget = Integer.parseInt(cmd[2]);
        String temp_project_name = project_name;
        TrieNode<Project> p = rb_project.search(temp_project_name);
        if(p != null){
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

    }

    public void handle_empty_line() {
       schedule();
    }


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

    public ArrayList<String> user_array = new ArrayList<String>();
    public ArrayList<User> userType_array = new ArrayList<User>();
    public void handle_user(String name) {
        System.out.println("Creating user");
        User user = new User(name);
        user_array.add(name);
        userType_array.add(user);

    }

    public MaxHeap<Job> job_maxHeap= new MaxHeap<Job>();
    public ArrayList<Job> total_job = new ArrayList<Job>();
    public void handle_job(String[] cmd) {
        System.out.println("Creating job");
        String name = cmd[1]; 
        String project_name = cmd[2]; 
        String user = cmd[3];
        int runningTime = Integer.parseInt(cmd[4]);
        String temp_project_name = project_name;
        TrieNode<Project> project = rb_project.search(temp_project_name);

        // System.out.println(user_array.contains(user));
        if(!user_array.contains(user)){
            System.out.println("No such user exists: " + user);
        } else if(project == null){
            System.out.println("No such project exists. " + project_name);
        } else {
            int p = project.getValue().priority;
            // int budget_to_store_in_job = project.getValue().budget;
            Job job = new Job(name, project_name, user, runningTime, p);
            job.arrival_time = globalTime;
            // job.job_budget = budget_to_store_in_job;
            job_count++;
            job_maxHeap.insert(job);
            total_job.add(job);
            unfinished_job_names.add(name);
        }

    }               

    public Trie<Project> rb_project = new Trie<Project>();
    public void handle_project(String[] cmd) {
        System.out.println("Creating project");
        String name = cmd[1];
        int priority = Integer.parseInt(cmd[2]);
        int budget = Integer.parseInt(cmd[3]);
        Project project = new Project(name, priority, budget);
        name = name;
        rb_project.insert(name, project);

    }


    ArrayList<String> unfinished_job = new ArrayList<String>();

    ArrayList<Job> unfinished_job_for_exec = new ArrayList<Job>();
    ArrayList<Job> finished_job_executed = new ArrayList<Job>();

    ArrayList<String> finished_job = new ArrayList<String>();
    ArrayList<String> finished_job_names= new ArrayList<String>();
    ArrayList<String> unfinished_job_names = new ArrayList<String>();

    public int globalTime = 0;
    public int totalJobsDone = 0;
    public int totalNoOfUnfinishedJob = 0;
    public int job_count = 0;
    public void execute_a_job() {
        System.out.println("Running code");
        System.out.println("Remaining jobs: " + job_count);
        boolean jobNotExecuted = true;
        // System.out.println(job_count + " ehehehehe");
        while(jobNotExecuted && job_count > 0){
            Job j = job_maxHeap.extractMax();
            if(j == null){
                job_count = 0;
                break;
            }
            // System.out.println("helo");
            int t = j.runningTime;
            String temp_project_name = j.project_name;
            TrieNode<Project> p = rb_project.search(temp_project_name);
            if(p == null){
                break;
            }
            int b = p.getValue().budget;

            if(b >= t){
                System.out.println("Executing: " + j.name + " from: " + j.project_name);
                b = b-t;
                p.value.budget = b;
                System.out.println("Project: " + j.project_name + " budget remaining: " + b);
                globalTime = globalTime + t;
                j.completion_time = globalTime;

                String usr = j.user;
                for(User t_user : userType_array){
                    if(t_user.name.equals(usr)){
                        t_user.consumed = t_user.consumed + t; 
                        t_user.latest_jobs_completion_time = t; break;

                    }

                }

                job_count = job_count - 1;
                totalJobsDone = totalJobsDone + 1;
                String f_job= "Job{user='" + j.user + "', project='" + j.project_name +"', jobstatus=COMPLETED, execution_time=" + t + ", end_time=" + globalTime + ", name='"+j.name + "'}";
                finished_job.add(f_job);
                finished_job_executed.add(j);
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
}
