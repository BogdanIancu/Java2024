package ro.ase.acs.main;

import ro.ase.acs.classes.Person;
import ro.ase.acs.classes.Student;
import ro.ase.acs.classes.Sum;
import ro.ase.acs.classes.TuitionType;
import ro.ase.acs.interfaces.Computable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // PART #1
        System.out.println("Hello, world!");

        Student s1 = new Student();
        Student s2 = new Student("John", 9.5f, 22);

        int x = 5;
        int y = x;
        y++;

        Student s3 = s2.myClone();
        s3.setAdmissionGrade(7);
        System.out.println(s2.getAdmissionGrade());

        // PART #2
        int[] marks = new int[] {9, 10, 7};
        Student s4 = new Student("Maria", 9.8f,
                marks, TuitionType.BUDGET);
        marks[0] = 5;

        int[] s4Marks = s4.getMarks();
        for(int mark : s4Marks) {
            System.out.println(mark);
        }

        byte[][] matrix = new byte[2][3];
        matrix[0][0] = 1;
        matrix[1][2] = 1;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // PART #3
        s4.learn();
        s4.setTuitionType(TuitionType.BUDGET);
        System.out.println(s4.getIncome());
        Person p = s4;

        Student s5 = null;
        try {
            s5 = (Student) s4.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(s5 == null) {
                s5 = new Student();
            }
        }

        // PART #4
        List<Integer> list = new Vector<>();
        list.add(4);
        list.add(5);
        list.add(2);

        int z = 5;
        Integer w = z;
        int q = w;
        System.out.println();
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        List<Student> studentList = new LinkedList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s4);

        for(Student s : studentList) {
            System.out.println(s);
        }

        Set<Student> set = new TreeSet<>();
        set.add(s1);
        set.add(s2);
        System.out.println(set);

        Set<Student> otherSet = new HashSet<>();
        otherSet.add(s4);
        otherSet.add(s5);
        System.out.println(otherSet);
        System.out.println(s4.equals(s5));

        Map<String, Student> map = new HashMap<>();
        map.put("Johnny", s2);
        map.put("Mary", s4);
        map.put("Mary", s5);

        System.out.println(map.get("Johnny"));
        System.out.println(map);

        System.out.println(eq(s4, s5));

        // PART #6
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Your name: ");
//        String name = scanner.nextLine();
//        System.out.println("Hello, " + name + "!");
        //scanner.close();

        // PART #7
        Computable computable = new Sum();
        float result = computable.compute(4, 5);
        System.out.println(result);

        new Student("Joe", 7.8f, 25).learn();

        computable = new Computable() {
            @Override
            public float compute(float a, float b) {
                return a * b;
            }
        };
        result = computable.compute(4, 5);
        System.out.println(result);

        computable = (a, b) -> a - b;
        result = computable.compute(4, 5);
        System.out.println(result);

        studentList.stream().filter(s -> s.getAdmissionGrade() > 5).sorted().
                forEach(System.out::println);
        result = studentList.stream().map(s -> s.getAdmissionGrade()).
                reduce((f1, f2) -> f1 + f2).get();
        System.out.println(result);
    }

    static <T> boolean eq(T value1, T value2) {
        return value1.equals(value2);
    }
}
