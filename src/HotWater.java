import java.util.*;
public class HotWater extends CoffeeDecorator {
    StringBuilder order = null;
    public HotWater(Coffee coffee) {
        super(coffee);
    }
    @Override
    public double Cost() {
        return this.coffee.Cost();
    }

    @Override
    public void addTopping(Coffee coffee) {
        this.coffee.addTopping(coffee);
    }


    @Override
    public String printCoffee() {
        order = new StringBuilder(this.coffee.printCoffee() + " as a hot drink");
        return this.order.toString();
    }
    @Override
    public String toString() {
        return order.toString();
    }
}
