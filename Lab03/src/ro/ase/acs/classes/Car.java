package ro.ase.acs.classes;

import ro.ase.acs.interfaces.Taxable;

public class Car extends Vehicle implements Taxable {
    private String color;
    private Engine engine;

    public Car() {
        this("white", "", 0);
    }

    public Car(String color, String producer, double price) {
        super(producer, price);
        this.color = color;
        this.engine = new Engine();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public double computeTax() {
        return getPrice() * 0.01;
    }

    @Override
    public void move() {
        System.out.println("The car is moving.");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Car copy = (Car) super.clone();
        copy.color = color;
        copy.engine = new Engine();
        copy.engine.power = this.engine.power;
        return super.clone();
    }
}

class Engine {
    public int power;
}
