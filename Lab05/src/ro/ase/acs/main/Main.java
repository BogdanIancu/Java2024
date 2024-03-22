package ro.ase.acs.main;

import ro.ase.acs.classes.Student;

import java.util.*;

public class Main {
    static class Int {
        public int value;
    }

    public static void main(String[] args) {
        Student s1 = new Student("Maria",10);
        Student s2 = new Student("Matei",9);
        Student s3 = new Student("Maria",8);
        Student s4 = new Student("Maria",10);
        //System.out.println(s1);
        Set<Student> set = new TreeSet<>();
        set.add(s1);
        set.add(s2);
        set.add(s4);
        set.add(s3);
        for(Student s: set){
            System.out.println(s);
        }
        System.out.println();

        Set<Student> hashSet = new HashSet<>();
        hashSet.add(s1);
        hashSet.add(s2);
        hashSet.add(s4);
        hashSet.add(s3);
        for (Student s: hashSet) {
            System.out.println(s);
        }

        Map<String, Student> map = new HashMap<>();
        map.put("Mary", s1);
        map.put("Matthew", s2);
        map.put("Mary", s3);

        System.out.println(map.get("Mary").getGrade());
        for(String key: map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key));
        }

        Int x = new Int();
        x.value = 5;
        Int y = new Int();
        y.value = 9;
        interchange(x, y);
        System.out.println(x.value);
        System.out.println(y.value);

        System.out.println(sum(3));
        System.out.println(sum(3, 4));
        System.out.println(sum(3, 4, 5));
    }

    private static void interchange(Int x, Int y) {
        int aux = x.value;
        x.value = y.value;
        y.value = aux;
    }

    private static long sum(int... values) {
        long s = 0;
        for (int i: values) {
            s += i;
        }
        return s;
    }
}
