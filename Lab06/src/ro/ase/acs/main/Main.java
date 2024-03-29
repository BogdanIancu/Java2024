package ro.ase.acs.main;

import ro.ase.acs.classes.Student;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        System.out.print("Grade: ");
        float grade = scanner.nextFloat();

        Student s1 = new Student(name, age, grade);
        System.out.println(s1);
        scanner.close();

        try {
            FileOutputStream fos = new FileOutputStream("Student.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(s1.getName());
            bw.write(System.lineSeparator());
            bw.write(Integer.toString(s1.getAge()));
            bw.write(System.lineSeparator());
            bw.write(Float.toString(s1.getGrade()));
            bw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = null;
        try {
            FileInputStream fis = new FileInputStream("Student.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String n = br.readLine();
            int a = Integer.parseInt(br.readLine());
            float f = Float.parseFloat(br.readLine());
            Student s2 = new Student(n, a, f);
            System.out.println(s2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        try(FileOutputStream fos = new FileOutputStream("Student.bin");
        DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeUTF(s1.getName());
            dos.writeInt(s1.getAge());
            dos.writeFloat(s1.getGrade());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try(FileInputStream fis = new FileInputStream("Student.bin");
            DataInputStream dis = new DataInputStream(fis)) {
            Student s3 = new Student();
            s3.setName(dis.readUTF());
            s3.setAge(dis.readInt());
            s3.setGrade(dis.readFloat());
            System.out.println(s3);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try(FileOutputStream fos = new FileOutputStream("Student.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(s1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(FileInputStream fis = new FileInputStream("Student.dat");
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            Student s4 = (Student)ois.readObject();
            System.out.println(s4);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}