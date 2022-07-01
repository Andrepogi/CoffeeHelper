import java.util.*;
public class Sugar extends CoffeeDecorator {
    StringBuilder order = null;
    @Override
    public double Cost() {
        return this.coffee.Cost() + 0.05;
    }
    public Sugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void addTopping(Coffee coffee) {
    }

    @Override
    public String printCoffee() {
        order = new StringBuilder(this.coffee.printCoffee() + " with sugar");
        return this.order.toString();
    }
    @Override
    public String toString() {
        return order.toString();
    }
}
