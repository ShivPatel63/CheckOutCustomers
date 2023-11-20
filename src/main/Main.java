package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// It has customer account data
class CustomerAccount {
	
	// Variable declaration
    private String accountId;
    private int password;
    private long cardNumber;
    private String customerEmail;

    // Constucter created
    public CustomerAccount(String accountId, int password, long cardNumber, String customerEmail) {
        this.accountId = accountId;
        this.password = password;
        this.cardNumber = cardNumber;
        this.customerEmail = customerEmail;
    }

    // Getter methods
    public String getAccountId() {
        return accountId;
    }

    public int getPassword() {
        return password;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}

// It has all the details regarding order delivery
class DeliveryOrder {
    private String accountId;
    private String item;
    private int quantity;
    private double totalPrice;
    private int authorizationNumber;

    // Constucter created
    public DeliveryOrder(String accountId, String item, int quantity, double totalPrice, int authorizationNumber) {
        this.accountId = accountId;
        this.item = item;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.authorizationNumber = authorizationNumber;
    }

    
    // Getter methods
    public String getAccountId() {
        return accountId;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getAuthorizationNumber() {
        return authorizationNumber;
    }
}


// Main class from where program runs.
public class Main {
    private static List<CustomerAccount> customerAccounts = new ArrayList<>();
    private static List<DeliveryOrder> deliveryOrders = new ArrayList<>();

    public static void main(String[] args) {
    	
    	// Initilaizing sample data using instance.
        initializeSampleData();

        // User inputs
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your account ID: ");
        String accountId = scanner.next();

        CustomerAccount customerAccount = findCustomerAccount(accountId);
        
        // Checking wheather user has account or not.
        if (customerAccount != null) {
            System.out.print("Enter your password: ");
            int password = scanner.nextInt();

            // Verifing the password and asking for other details
            if (password == customerAccount.getPassword()) {
            	
                System.out.println("Welcome, " + customerAccount.getCustomerEmail());

                System.out.print("Enter item name: ");
                String item = scanner.next();
                
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                
                System.out.print("Enter total price: ");
                double totalPrice = scanner.nextDouble();

                System.out.print("Enter credit card number: ");
                long cardNumber = scanner.nextLong();

                // Requesting authorization from the bank
                int authorizationNumber = requestAuthorizationFromBank(cardNumber);
                
                if (authorizationNumber != -1) {
                    DeliveryOrder newOrder = new DeliveryOrder(accountId, item, quantity, totalPrice, authorizationNumber);
                    deliveryOrders.add(newOrder);
                    System.out.println("Order placed successfully!");
                } else {
                    System.out.println("Payment authorization failed.");
                }
            } else {
                System.out.println("Incorrect password. Access denied.");
            }
        } else {
            System.out.println("Customer not found.");
        }
    }


    
    private static int requestAuthorizationFromBank(long cardNumber) {
        // Simulate bank authorization process based on credit card number
        if (cardNumber == 13579) {
            System.out.println("Bank Authorization code : 7777");
            System.out.println("Payment approved.");
            return 7777;
        } else if (cardNumber == 24680) {
            System.out.println("Bank Authorization code : 0000");
            System.out.println("Payment denied by the bank.");
            System.out.println("Payment authorization failed.");
            System.exit(0);
            return 0000;
        } else {
            System.out.println("Invalid credit card number.");
            return -1; // You may choose to handle invalid credit card numbers differently
        }
    }
    
    
    private static void initializeSampleData() {
        customerAccounts.add(new CustomerAccount("steve", 2345, 12345678, "steve@ttu.edu"));
        customerAccounts.add(new CustomerAccount("alex", 4567, 23456789, "alex@ttu.edu"));
        customerAccounts.add(new CustomerAccount("jane", 6789, 45678901, "jane@ttu.edu"));
        customerAccounts.add(new CustomerAccount("john", 5678, 56789012, "john@ttu.edu"));
        customerAccounts.add(new CustomerAccount("sam", 7890, 89012345, "sam@ttu.edu"));
        deliveryOrders.add(new DeliveryOrder("alex", "note", 3, 20.00, 3333));
    }

    private static CustomerAccount findCustomerAccount(String accountId) {
        for (CustomerAccount account : customerAccounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    private static int requestAuthorizationFromBank() {
        // Simulate bank authorization process
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter authorization number (4 digits): ");
        int authorizationNumber = scanner.nextInt();
        if (authorizationNumber >= 1000 && authorizationNumber <= 9999) {
            return authorizationNumber;
        } else {
            System.out.println("Invalid authorization number.");
            return -1;
        }
    }
}