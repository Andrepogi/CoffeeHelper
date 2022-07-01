import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Main {
    public static ArrayList<Double> orderCosts = new ArrayList<>();
    public static ArrayList<String> orderList = new ArrayList<>();
    public static ArrayList<Integer> invNums = new ArrayList<>(6);


    public static void main(String[] args) throws IOException{
        System.out.println("Cafe Application Running");
        Scanner CafeApplication = new Scanner(System.in);
        Scanner userOrders = new Scanner(System.in);
        Scanner custName = new Scanner(System.in);
        int input = 0;

        invNums = inventoryReader(); //initializes invNums

        Stack<String> stack = new Stack<String>();

        while (input != 1) { //Initial Menu
            System.out.println("Press 1: Read Inventory");
            System.out.println("Press 2: Create Coffee Order");
            System.out.println("Press 3: Update Inventory");
            System.out.println("Press 4: Update Log File");
            System.out.println("Press 5: Exit the application");
            switch (CafeApplication.nextLine()) {
                case "1": //read inventory
                    File invFile = new File("Inventory.txt");
                    Scanner fileReader = new Scanner(invFile);
                    while (fileReader.hasNextLine()) {
                        String data = fileReader.nextLine();
                        System.out.println(data);
                    }
                    fileReader.reset();
                    break;
                case "2": //create coffee order
                    String customerName;
                    System.out.println("Coffee order created. Select toppings for the first coffee:");
                    String line = "yes";
                    do {
                        createOrder(invNums);
                        System.out.println("Do you want to add another coffee to this order? - yes or no");
                    } while (!(line = userOrders.nextLine()).equals("no"));
                    System.out.println("Add a name to the order: ");
                    customerName = custName.nextLine();
                    stack.push(printOrder(orderList, orderCosts, customerName));
                    break;
                case "3": //update inventory
                    updateInventory(invNums);
                    break;
                case "4": //update log file
                    updateLog(stack);
                    break;
                case "5": //exit the application
                    input = 1;
                    break;
                default:
                    System.out.println("Invalid selection. Please Try Again");
                    break;
            }
        }
    }

    /**
     * This code assigns a date and time of an order as well as the contents of the order and updates the following information
     * into "LogFile.txt"
     * @param s
     */
    public static void updateLog(Stack<String> s) {

        File logfile = new File("LogFile.txt");
        Iterator siterator = s.iterator();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss z");
        String dString = date.format(new Date());
        String tString = time.format(new Date());
        try {
            FileWriter fw = new FileWriter(logfile);
            fw.write("Writing orders from stack " + dString + " at " + tString + "\n\n");
            while (siterator.hasNext()) {
                fw.write((String) siterator.next() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This code wipes all written data on "Inventory.txt" and replaces it with the same text and new data that includes
     * the changes in inventory that could have changed in the process of making several coffee orders.
     * @param num
     * @throws IOException
     */
    public static void updateInventory(ArrayList<Integer> num) throws IOException {
        File inventoryFile = new File("Inventory.txt");
        Scanner fileReader = new Scanner(inventoryFile);
        FileWriter fw = new FileWriter(inventoryFile);
        PrintWriter pw = new PrintWriter(inventoryFile);
        pw.print("");
        pw.close();

        fw.write("Black Coffee = " + num.get(0) + "\n");
        fw.write("Milk = " + num.get(1) + "\n");
        fw.write("Hot Water = " + num.get(2) + "\n");
        fw.write("Espresso = " + num.get(3) + "\n");
        fw.write("Sugar = " + num.get(4) + "\n");
        fw.write("Whipped Cream = " + num.get(5) + "\n");
        fw.close();
    }

    /**
     * This method reads the current values present in "Inventory.txt" and returns an ArrayList of type integer which we can use to initialize
     * the ArrayList in the main method. How I did this was by removing all non-digit characters on any line in the text file and keeping the
     * value which I then used to assign to a temporary Arraylist which I returned back to the main method.
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<Integer> inventoryReader() throws FileNotFoundException {
        File inventoryFile = new File("Inventory.txt");
        Scanner scnr = new Scanner(inventoryFile);
        String line;
        String cleanLine;
        int cleanInt;
            ArrayList<Integer> intArrayList = new ArrayList<>(6);

            while (scnr.hasNext()) {
                line = scnr.nextLine();
                cleanLine = line.replaceAll("\\D+", "");
                if (cleanLine != "") {
                    cleanInt = Integer.parseInt(cleanLine);
                    intArrayList.add(cleanInt);
                }
            }
        return intArrayList;
    }

    /**
     * This method is responsible for the creation of all the orders. I have included several if-statements that determine
     * whether the current menu item/topping is in stock or not. The values are then synced with the values in the field arraylist
     * @param z
     */
    public static void createOrder(ArrayList<Integer> z) {
        Scanner userFeedback = new Scanner(System.in);
        int in = 0;

        String line = "yes";
        Coffee coffee = new BasicCoffee();
        while (in != 1) {
            if (z.get(0) <= 0) {
                System.out.println("Out of Coffees");
                break;
            }
            System.out.println("Enter the following values to add toppings: 1.) milk 2.) hot water 3.) espresso 4.) sugar 5.) whipped cream e - to complete order");

            switch (userFeedback.nextLine()) {
                case "1":
                    if (z.get(1) - 1 > 0) {
                        coffee = new Milk(coffee);
                        z.set(1, z.get(1) - 1);
                        break;
                    }
                    else {
                        System.out.println("Out of milk :(");
                        break;
                    }
                case "2":
                    if (z.get(2) - 1 > 0) {
                        coffee = new HotWater(coffee);
                        z.set(2, z.get(2) - 1);
                        break;
                    }
                    else {
                        System.out.println("Out of Hot Water :(");
                        break;
                    }
                case "3":
                    if (z.get(3) - 1 > 0) {
                        coffee = new Espresso(coffee);
                        z.set(3, z.get(3) - 1);
                        break;
                    }
                    else {
                        System.out.println("Out of Espresso :(");
                        break;
                    }
                case "4":
                    if (z.get(4) - 1 > 0) {
                        coffee = new Sugar(coffee);
                        z.set(4, z.get(4) - 1);
                        break;
                    }
                    else {
                        System.out.println("Out of Sugar :(");
                        break;
                    }
                case "5":
                    if (z.get(5) - 1 > 0) {
                        coffee = new WhippedCream(coffee);
                        z.set(5, z.get(5) - 1);
                        break;
                    }
                    else {
                        System.out.println("Out of Hot Water :(");
                        break;
                    }
                case "e":
                    coffee = new BlackCoffee(coffee);
                    z.set(0, z.get(0) - 1);
                    in = 1;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
        invNums = z;
        orderList.add(coffee.printCoffee());
        orderCosts.add(coffee.Cost());
    }

    /**
     * This method is responsible for creating the text that is written in "LogFile.txt".
     * I have three separate parameters: the contents of the order which include the toppings, drink, etc., the cost
     * of the drink, and the name of the customer. This method also includes the total cost of the order.
     * @param coffees
     * @param prices
     * @param customer
     * @return
     */
    public static String printOrder(ArrayList<String> coffees, ArrayList<Double> prices, String customer) {
        double total = 0;
        StringBuilder str = new StringBuilder();

        str.append("ORDER FOR: " + customer + "\n");
        for (int i = 0; i < coffees.size(); i++) {
            str.append("Item: " + (i+1) + " | " + coffees.get(i));
            str.append(String.format(" | Cost: %.2f \n", prices.get(i)));
        }

        for (Double d : prices) {
            total += d;
        }
        str.append("TOTAL COST OF ORDER: ");
        str.append(String.format("%.2f", total));

        return str.toString();
    }
}
