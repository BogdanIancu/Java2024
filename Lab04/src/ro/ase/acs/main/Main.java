package ro.ase.acs.main;

import ro.ase.acs.classes.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println(eq("abc", "abc"));
        System.out.println(lt("abc", "def"));

        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(7);

        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        Student s1 = new Student("John", 9);
        Student s2 = new Student("Maria", 10);

        List<Student> studentList = new Vector<>();
        studentList.add(s1);
        studentList.add(s2);

        for(Student s : studentList) {
            System.out.println(s);
        }

        System.out.println();
        for(Iterator<Student> it = studentList.iterator(); it.hasNext(); ){
            System.out.println(it.next());
        }
    }

    public static <T> boolean eq(T value1, T value2) {
        return value1.equals(value2);
    }

    public static <T extends Comparable<T>> boolean lt(T value1, T value2) {
        return value1.compareTo(value2) < 0;
    }
}
