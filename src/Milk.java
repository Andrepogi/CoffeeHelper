import java.util.*;
public class Milk extends CoffeeDecorator {
    StringBuilder order = null;
    @Override
    public double Cost() {
        return this.coffee.Cost() + 0.15;
    }
    public Milk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void addTopping(Coffee coffee) {
    }

    @Override
    public String printCoffee() {
        order = new StringBuilder(this.coffee.printCoffee() + " with milk");
        return this.order.toString();
    }
    @Override
    public String toString() {
        return order.toString();
    }

}
