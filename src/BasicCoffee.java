import java.util.*;
public class BasicCoffee implements Coffee {
    StringBuilder order = null;
    @Override
    public double Cost() {
        return 0.85;
    }
    @Override
    public void addTopping(Coffee coffee) {}

    @Override
    public String printCoffee() {
        order = new StringBuilder("A Black Coffee");
        return order.toString();
    }

    public String toString() {
        return order.toString();
    }
}
