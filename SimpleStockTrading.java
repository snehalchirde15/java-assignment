import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class User {
    String name;
    double cash;
    Map<String, Integer> holdings = new HashMap<>();

    User(String name, double cash) {
        this.name = name;
        this.cash = cash;
    }

    void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (cash >= cost) {
            cash -= cost;
            holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stock.symbol);
        } else {
            System.out.println("Not enough cash to buy!");
        }
    }

    void sellStock(Stock stock, int quantity) {
        int owned = holdings.getOrDefault(stock.symbol, 0);
        if (owned >= quantity) {
            cash += stock.price * quantity;
            holdings.put(stock.symbol, owned - quantity);
            System.out.println("Sold " + quantity + " shares of " + stock.symbol);
        } else {
            System.out.println("Not enough shares to sell!");
        }
    }

    void showPortfolio(Map<String, Stock> market) {
        System.out.println("\n--- Portfolio of " + name + " ---");
        System.out.println("Cash: $" + cash);
        double total = cash;
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            Stock stock = market.get(symbol);
            double value = qty * stock.price;
            System.out.println(symbol + ": " + qty + " shares (Value: $" + value + ")");
            total += value;
        }
        System.out.println("Total Portfolio Value: $" + total);
    }
}

public class SimpleStockTrading {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create market with some stocks
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 150));
        market.put("GOOG", new Stock("GOOG", 120));
        market.put("TSLA", new Stock("TSLA", 200));

        // Create user
        User user = new User("Snehal", 1000);

        while (true) {
            System.out.println("\n=== Stock Trading Menu ===");
            System.out.println("1. Show Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Show Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("\n--- Market Data ---");
                for (Stock s : market.values()) {
                    System.out.println(s.symbol + " : $" + s.price);
                }
            } else if (choice == 2) {
                System.out.print("Enter stock symbol: ");
                String sym = sc.next().toUpperCase();
                Stock stock = market.get(sym);
                if (stock != null) {
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    user.buyStock(stock, qty);
                } else {
                    System.out.println("Stock not found!");
                }
            } else if (choice == 3) {
                System.out.print("Enter stock symbol: ");
                String sym = sc.next().toUpperCase();
                Stock stock = market.get(sym);
                if (stock != null) {
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    user.sellStock(stock, qty);
                } else {
                    System.out.println("Stock not found!");
                }
            } else if (choice == 4) {
            	
                user.showPortfolio(market);
            } else if (choice == 5) {
                System.out.println("Exiting... Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}
