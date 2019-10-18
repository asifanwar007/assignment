package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;

    public Student(String trim, int parseInt) {
        this.name = trim;
        this.marks = parseInt;
    }


    @Override
    public int compareTo(Student student) {
        return this.marks.compareTo(student.marks);
    }

    public String getName() {
        return name;
    }
    public String toString(){
        return "Student{name=" + "'" + this.name + "'" + ", " + "marks=" + this.marks + "}";
    }
    // public static void main(String[] args) {
    //     Student s = new Student("Asif", 100);
    //     Student s1 = new Student("Anwar", 120);
    //     System.out.println(s.compareTo(s1));
    // }
}
