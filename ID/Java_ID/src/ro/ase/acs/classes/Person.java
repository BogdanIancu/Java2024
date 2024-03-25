package ro.ase.acs.classes;

public abstract class Person implements Cloneable {
    private int age;

    public Person() {
    }

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person copy = (Person)super.clone();
        copy.age = age;
        return copy;
    }

    public abstract float getIncome();
}
