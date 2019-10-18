package ProjectManagement;

public class User implements Comparable<User> ,UserReport_ {
	String name;
	public int consumed;
	public int latest_jobs_completion_time;
	public User(String name){
		this.name = name;
	}
	public String user(){
		return  this.name;
	}
	public int consumed(){ 	//to be done
		return consumed;
	}
	@Override
	public String toString(){
		return this.name + " " + this.consumed ;
	}
    @Override
    public int compareTo(User user) {
        return this.name.compareTo(user.name);
    }
}
