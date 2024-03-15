package ro.ase.acs.classes;

public final class Student {
    private String name;
    private float grade;

    private static final int MINIMUM_PASSING_GRADE = 5;

    private final String university;

    public Student() {
        university = "ASE";
    }

    public Student(String name, float grade) {
        university = "POLITEHNICA";
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
    public final String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
