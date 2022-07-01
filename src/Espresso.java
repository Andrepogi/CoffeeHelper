import java.util.*;
public class Espresso extends CoffeeDecorator{
    StringBuilder order = null;
    @Override
    public double Cost() {
        return this.coffee.Cost() + 0.35;
    }
    public Espresso(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void addTopping(Coffee coffee) {
        this.coffee.addTopping(coffee);
    }

    @Override
    public String printCoffee() {
        order = new StringBuilder(this.coffee.printCoffee() + " and an espresso");
        return this.order.toString();
    }
    @Override
    public String toString() {
        return order.toString();
    }
}
