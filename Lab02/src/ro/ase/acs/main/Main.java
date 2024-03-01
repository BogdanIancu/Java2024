package ro.ase.acs.main;

import ro.ase.acs.classes.Student;
import ro.ase.acs.classes.TuitionType;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("George");
        System.out.println(s1.getTuitionType());
        s1.setTuitionType(TuitionType.TAX);

        float[] array = new float[] { 8, 7, 9 };

        Student s2 = new Student("John", array);
        array[0] = 4;

        Student copy = (Student) s2.clone();
        System.out.println(copy.getName());
    }
}