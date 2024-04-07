package ro.ase.acs.interfaces;

@FunctionalInterface
public interface Printable {
    void print(String message);

    default void printLine(String message) {
        System.out.println(message);
    }
}
