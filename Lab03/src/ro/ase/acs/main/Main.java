package ro.ase.acs.main;

import ro.ase.acs.classes.Car;
import ro.ase.acs.classes.Vehicle;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = new int[3][2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        int[][] raggedArray = new int[][]{{1, 2}, {1, 2, 3}, {4}, {5, 6}};

        String s = "abc";
        String t = "abc";
        s += "def";
        String z = new String(t);

        Car c1 = new Car("blue", "Audi", 40000);
        System.out.println(c1.getColor());
        Vehicle v = c1;
        v.move();
        System.out.println(c1.computeTax());

        try {
            if (c1 instanceof Cloneable) {
                Car c2 = (Car) c1.clone();
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}