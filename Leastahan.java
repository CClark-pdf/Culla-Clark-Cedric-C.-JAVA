import java.util.*;


abstract class User { //3
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    
    public abstract void displayWelcomeMessage();
}


class RegularUser extends User { //4
    public RegularUser(String username) {
        super(username);
    }

    @Override
    public void displayWelcomeMessage() {
        System.out.println("Welcome, " + getUsername() + "!");
    }
}class Item {
    private String name;
    private boolean checked;  //1

    public Item(String name) {
        this.name = name;
        this.checked = false;
    }

    public String getName() { //2
        return name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}class ItemManager {
    private final Map<String, List<Item>> userItems = new HashMap<>();

    public void addItem(String username, String itemName) {
        userItems.putIfAbsent(username, new ArrayList<>());
        userItems.get(username).add(new Item(itemName));
    }

    public List<Item> getItems(String username) {
        return userItems.getOrDefault(username, new ArrayList<>());
    }

    public boolean deleteItem(String username, int index) {
        List<Item> items = userItems.get(username);
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }
}
public class Leastahan {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ItemManager itemManager = new ItemManager();
    private static RegularUser currentUser;

    public static void main(String[] args) {
        System.out.println("Welcome to Leastahan!");

        login();

        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Delete Item");
            System.out.println("4. Switch User");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1 -> addItem();
                case 2 -> viewItems();
                case 3 -> deleteItem();
                case 4 -> login();
                case 5 -> {
                    System.out.println("Thank you for using Leastahan! Goodbye.");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void login() {
        System.out.print("\nEnter your username: ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty. Try again.");
            login();
            return;
        }

        currentUser = new RegularUser(username);
        currentUser.displayWelcomeMessage();
    }
    private static void addItem() {
        System.out.print("\nEnter item name: ");
        String itemName = scanner.nextLine().trim();

        if (itemName.isEmpty()) {
            System.out.println("Item name cannot be empty!");
            return;
        }

        itemManager.addItem(currentUser.getUsername(), itemName);
        System.out.println("Item added successfully.");
    }
    private static void viewItems() {
        List<Item> items = itemManager.getItems(currentUser.getUsername());

        if (items.isEmpty()) {
            System.out.println("No items found.");
            return;
        }

        System.out.println("\nYour Items:");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.printf("%d. [%s] %s%n", i + 1, item.isChecked() ? "X" : " ", item.getName());
        }

        System.out.println("\nOptions:");
        System.out.println("1. Mark item as checked");
        System.out.println("2. Go back to main menu");
        System.out.print("Enter your choice: ");
        int choice = getIntInput();

        if (choice == 1) {
            markItemAsChecked(items);
        }
    }
    private static void deleteItem() {
        List<Item> items = itemManager.getItems(currentUser.getUsername());

        if (items.isEmpty()) {
            System.out.println("No items found.");
            return;
        }

        System.out.println("\nYour Items:");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, items.get(i).getName());
        }

        System.out.print("Enter the number of the item to delete: ");
        int itemNumber = getIntInput();
        if (itemManager.deleteItem(currentUser.getUsername(), itemNumber - 1)) {
            System.out.println("Item deleted successfully.");
        } else {
            System.out.println("Invalid item number.");
        }
    }
    private static void markItemAsChecked(List<Item> items) {
        System.out.print("Enter the number of the item to mark as checked: ");
        int itemNumber = getIntInput();
        if (itemNumber < 1 || itemNumber > items.size()) {
            System.out.println("Invalid item number.");
            return;
        }

        items.get(itemNumber - 1).setChecked(true);
        System.out.println("Item marked as checked.");
    }
    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}
