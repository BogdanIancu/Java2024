package ro.ase.acs.main;

import ro.ase.acs.classes.Addition;
import ro.ase.acs.interfaces.BinaryOperation;
import ro.ase.acs.interfaces.Printable;
import ro.ase.acs.interfaces.Taxable;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        BinaryOperation op;
        op = new Addition();

        System.out.println(op.compute(4, 5));

        double result = new Addition().compute(3, 4);
        System.out.println(result);

        op = new BinaryOperation() {
            @Override
            public double compute(double x, double y) {
                return x * y;
            }
        };
        System.out.println(op.compute(4, 5));

        op = (x, y) -> x / y;
        System.out.println(op.compute(4, 5));

        Printable p = m -> System.out.print(m);
        p.print("Hello!");
        System.out.println();

        final double value = 100;

        Taxable t = () -> {
            if(value < 0) {
                return 0;
            } else {
                return value * 0.2;
            }
        };
        System.out.println(t.computeTax());

        List<Integer> list = List.of(1, 3, 1, 4, 6, 8, 8, 10);
        long distinctElements = list.stream().distinct().count();
        System.out.println(distinctElements);

        List<Integer> subList = list.stream().filter(i -> i % 2 == 0).
                sorted().collect(Collectors.toList());
        System.out.println(subList);

        List<String> strings = List.of("Mary", "John", "George", "Elizabeth", "Charles");
        String r = strings.stream().map(s -> s.toUpperCase()).
                reduce((s1, s2) -> s1 + ", " + s2).get();
        System.out.println(r);

        strings.stream().sorted().forEach(System.out::println);
    }
}
