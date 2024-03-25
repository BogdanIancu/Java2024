package ro.ase.acs.classes;

import ro.ase.acs.interfaces.Learnable;

import java.util.Arrays;
import java.util.Objects;

public final class Student extends Person implements Learnable, Comparable<Student> {
    private final static int MIN_GRADE = 5;
    private final String UNIVERSITY = "ASE";
    private String name;
    private float admissionGrade;
    private int[] marks;
    private TuitionType tuitionType = TuitionType.TAX;

    public Student() {
        name = "Anonymous";
        admissionGrade = 1;
    }

    public Student(String name, float admissionGrade, int age) {
        super(age);
        this.name = name;
        this.admissionGrade = admissionGrade;
    }

    public Student(String name, float admissionGrade, int[] marks, TuitionType tuitionType) {
        this.name = name;
        this.admissionGrade = admissionGrade;
        if(marks != null) {
            this.marks = new int[marks.length];
            for(int i = 0; i < marks.length; i++) {
                this.marks[i] = marks[i];
            }
        }
        this.tuitionType = tuitionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAdmissionGrade() {
        return admissionGrade;
    }

    public void setAdmissionGrade(float admissionGrade) {
        this.admissionGrade = admissionGrade;
    }

    public TuitionType getTuitionType() {
        return tuitionType;
    }

    public void setTuitionType(TuitionType tuitionType) {
        this.tuitionType = tuitionType;
    }

    public int[] getMarks() {
        if(marks != null) {
            return Arrays.copyOf(marks, marks.length);
        } else {
            return null;
        }
    }

    public void setMarks(int[] marks) {
        if(marks != null) {
            this.marks = new int[marks.length];
            System.arraycopy(marks, 0, this.marks, 0, marks.length);
        }
    }

    public Student myClone() {
        Student copy = new Student();
        copy.name = name;
        copy.admissionGrade = admissionGrade;
        copy.tuitionType = tuitionType;
        copy.marks = getMarks();
        copy.setAge(this.getAge());
        return copy;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Student copy = (Student) super.clone();
        copy.name = name;
        copy.admissionGrade = admissionGrade;
        copy.tuitionType = tuitionType;
        copy.marks = getMarks();
        return copy;
    }

    @Override
    public void learn() {
        System.out.println("The student learns at the university");
    }

    @Override
    public final float getIncome() {
        if(tuitionType == TuitionType.BUDGET){
            return 1000;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", admissionGrade=").append(admissionGrade);
        sb.append(", marks=").append(Arrays.toString(marks));
        sb.append(", tuitionType=").append(tuitionType);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Student o) {
        return Float.compare(this.admissionGrade, o.admissionGrade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Float.compare(student.admissionGrade, admissionGrade) == 0 && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, admissionGrade);
    }


}
