package Trie;

public class Person {
	String name;
	String phone_number;

    public Person(String name, String phone_number) {
    	this.name = name;
    	this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }
    public String getPhone(){
    	return phone_number;
    }
    public String toString(){
    	return "[Name: "+ this.name + ", " +  "Phone=" + this.phone_number + "]";
    }
}
