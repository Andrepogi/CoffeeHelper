import java.util.*;
public class WhippedCream extends CoffeeDecorator {
     StringBuilder order = null;
     public WhippedCream(Coffee coffee) {
        super(coffee);
     }

     @Override
     public double Cost() {
         return this.coffee.Cost() + 0.10;
     }
     @Override
     public void addTopping(Coffee coffee) {
     }

    @Override
    public String printCoffee() {
        order = new StringBuilder(this.coffee.printCoffee() + " topped with Whipped Cream" );
        return this.order.toString();
    }
    @Override
    public String toString() {
        return order.toString();
    }
}
