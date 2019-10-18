package ProjectManagement;


public class Project {
	String name;
	int priority;
	int budget;
	public Project(String name, int priority, int budget){
		this.name = name;
		this.budget = budget;
		this.priority = priority;
	}
	// public int compareTo(String proj){
	// 	return this.name.compareTo(proj);
	// }
}