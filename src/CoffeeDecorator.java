import java.util.*;
public abstract class CoffeeDecorator implements Coffee {
    public abstract double Cost();

    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public void addTopping(Coffee coffee) {
        this.coffee.addTopping(coffee);
    }

    public String printCoffee() {
        return this.coffee.printCoffee();
    }
}
