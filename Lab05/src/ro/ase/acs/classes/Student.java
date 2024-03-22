package ro.ase.acs.classes;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private String name;
    private float grade;

    public Student() {
    }

    public Student(String name, float grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", grade=").append(grade);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public int compareTo(Student student) {
        if(this.name!=null) {
            if (this.name.equals(student.name)) {
                return Float.compare(this.grade,student.grade);
            }
            else{
                return this.name.compareTo(student.name);
            }
        }
        else{
            if(student.name==null){
                return 0;
            }
            else{
                return -1;
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Float.compare(grade, student.grade) == 0 && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, grade);
    }


}
