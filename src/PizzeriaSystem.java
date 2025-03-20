import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// CustomPizza类
class CustomPizza {
    private String toppings;
    private double price;

    public CustomPizza(String toppings, double price) {
        this.toppings = toppings;
        this.price = price;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CustomPizza{toppings='" + toppings + "', price=" + price + "}";
    }
}

// HandleOrders类
class HandleOrders {
    private ArrayList<CustomPizza> customPizzas = new ArrayList<>();

    public void processOrder() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Do you want a custom pizza? (yes/no)");
            String answer = scanner.nextLine();
            if ("yes".equalsIgnoreCase(answer)) {
                StringBuilder customPizzaToppings = new StringBuilder();
                double customPizzaPrice = 0;
                while (true) {
                    System.out.println("Enter a topping (or 'done' to finish):");
                    String topping = scanner.nextLine();
                    if ("done".equalsIgnoreCase(topping)) {
                        break;
                    }
                    customPizzaToppings.append(topping).append(", ");
                    customPizzaPrice += 1; // Assume each topping costs $1
                }
                if (customPizzaToppings.length() > 0) {
                    customPizzaToppings.delete(customPizzaToppings.length() - 2, customPizzaToppings.length());
                }
                CustomPizza customPizza = new CustomPizza(customPizzaToppings.toString(), customPizzaPrice);
                customPizzas.add(customPizza);
            }
        }
    }

    public void displayCustomPizzas() {
        for (CustomPizza pizza : customPizzas) {
            System.out.println(pizza);
        }
    }
}

// OrderLogs类
class OrderLogs {
    private ArrayDeque<String> orderLogs;

    public OrderLogs() {
        orderLogs = new ArrayDeque<>();
    }

    public void addOrderLog(String log) {
        orderLogs.push(log);
    }

    private void mostRecentLogEntry() {
        if (!orderLogs.isEmpty()) {
            System.out.println(orderLogs.peek());
        } else {
            System.out.println("The log is empty.");
        }
    }

    private String getOrderLog() {
        if (!orderLogs.isEmpty()) {
            return orderLogs.pop();
        }
        return null;
    }

    private void removeAllLogEntries() {
        orderLogs.clear();
    }

    private boolean orderLogsEmpty() {
        return orderLogs.isEmpty();
    }

    public void handleLogs() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Choose what you want to do with order logs:");
                System.out.println("1. Display all the logs");
                System.out.println("2. Display the most recent logs");
                System.out.println("3. Remove a log entry");
                System.out.println("4. Remove all log entries");
                System.out.println("5. Check if the log is completely empty");
                System.out.println("Enter your choice (1 - 5)");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                switch (choice) {
                    case 1:
                        for (String log : orderLogs) {
                            System.out.println(log);
                        }
                        break;
                    case 2:
                        mostRecentLogEntry();
                        break;
                    case 3:
                        if (!orderLogsEmpty()) {
                            System.out.println("Removed log: " + getOrderLog());
                        } else {
                            System.out.println("The log is empty.");
                        }
                        break;
                    case 4:
                        removeAllLogEntries();
                        System.out.println("All log entries removed.");
                        break;
                    case 5:
                        if (orderLogsEmpty()) {
                            System.out.println("The log is completely empty");
                        } else {
                            System.out.println("The log is not completely empty");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}

// PizzaOrderQueue类
class PizzaOrderQueue {
    private Queue<String> pizzaOrders;

    public PizzaOrderQueue() {
        pizzaOrders = new LinkedList<>();
    }

    public void addOrder(String order) {
        pizzaOrders.add(order);
    }

    private void displayNextOrder() {
        if (!pizzaOrders.isEmpty()) {
            System.out.println("Next order: " + pizzaOrders.peek());
        } else {
            System.out.println("No orders in the queue.");
        }
    }

    private String processOrder() {
        if (!pizzaOrders.isEmpty()) {
            return pizzaOrders.poll();
        }
        return null;
    }

    private void removeAllOrders() {
        pizzaOrders.clear();
    }

    private boolean isQueueEmpty() {
        return pizzaOrders.isEmpty();
    }

    public void handleQueue() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Choose what you want to do with pizza orders queue:");
                System.out.println("1. Display next order");
                System.out.println("2. Process an order");
                System.out.println("3. Remove all orders");
                System.out.println("4. Check if the queue is empty");
                System.out.println("Enter your choice (1 - 4)");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                switch (choice) {
                    case 1:
                        displayNextOrder();
                        break;
                    case 2:
                        if (!isQueueEmpty()) {
                            System.out.println("Processed order: " + processOrder());
                        } else {
                            System.out.println("No orders in the queue.");
                        }
                        break;
                    case 3:
                        removeAllOrders();
                        System.out.println("All orders removed.");
                        break;
                    case 4:
                        if (isQueueEmpty()) {
                            System.out.println("The queue is empty");
                        } else {
                            System.out.println("The queue is not empty");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}

// 主类
public class PizzeriaSystem {
    public static void main(String[] args) {
        HandleOrders handleOrders = new HandleOrders();
        handleOrders.processOrder();
        handleOrders.displayCustomPizzas();

        OrderLogs orderLogs = new OrderLogs();
        orderLogs.addOrderLog("Order 1 placed");
        orderLogs.addOrderLog("Order 2 placed");
        orderLogs.handleLogs();

        PizzaOrderQueue pizzaOrderQueue = new PizzaOrderQueue();
        pizzaOrderQueue.addOrder("Pizza 1");
        pizzaOrderQueue.addOrder("Pizza 2");
        pizzaOrderQueue.handleQueue();
    }
}    