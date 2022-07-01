import java.util.*;
public class BlackCoffee extends CoffeeDecorator {
    StringBuilder order = null;

    @Override
    public double Cost() {
        return this.coffee.Cost();
    }
    public BlackCoffee(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void addTopping(Coffee coffee) {
        instructions();
    }

    @Override
    public String printCoffee() {
        order = new StringBuilder(this.coffee.printCoffee());
        return this.coffee.toString();
    }
    @Override
    public String toString() {
        return order.toString();
    }
    public void instructions() {
        System.out.println("Pour coffee from pot into cup");
    }
}
