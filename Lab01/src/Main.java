public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Student s = new Student("George", 8.5f);
        System.out.println(s.getName());
        System.out.println(s.getGrade());

        int x = 5;
        int y = x;
        x++;
        System.out.println(x + " " + y);

        Student s2 = s.myClone();
        s2.setGrade(9);
        System.out.println(s.getGrade());
        System.out.println(s2.getGrade());
    }
}
