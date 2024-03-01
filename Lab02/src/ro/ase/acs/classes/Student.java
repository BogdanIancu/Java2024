package ro.ase.acs.classes;

import java.util.Arrays;

public class Student {
    private String name;
    private float[] grades;
    private TuitionType tuitionType;

    public Student() {
        name = "Anonymous";
        grades = null;
    }

    public Student(String name, float[] grades) {
        this.name = name;
        if(grades != null) {
            this.grades = new float[grades.length];
            for(int i = 0; i < grades.length; i++) {
                this.grades[i] = grades[i];
            }
        } else {
            this.grades = null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float[] getGrades() {
        if(grades != null) {
            return Arrays.copyOf(grades, grades.length);
        } else {
            return null;
        }
    }

    public void setGrades(float[] grades) {
        if(grades != null) {
            this.grades = new float[grades.length];
            System.arraycopy(grades, 0, this.grades, 0, grades.length);
        } else {
            this.grades = null;
        }
    }

    public TuitionType getTuitionType() {
        return tuitionType;
    }

    public void setTuitionType(TuitionType tuitionType) {
        this.tuitionType = tuitionType;
    }

    @Override
    public Object clone() {
        Student copy = new Student();
        copy.name = name;
        copy.tuitionType = tuitionType;
        copy.setGrades(grades);
        return copy;
    }
}