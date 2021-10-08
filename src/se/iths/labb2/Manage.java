package se.iths.labb2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Manage {
    private final Scanner scanner = new Scanner(System.in);
    private List<Product> products = new ArrayList<>();
    private static final Pattern pattern = Pattern.compile(",");

    private String category;
    private String brand;
    private String productName;
    private float price;
    private float alcohol;
    private int productId;

    public void execute() {
        run();
    }

    private void run() {
        int choice;
        do {
            productManagementMenu();
            choice = readChoice(scanner);
            executeChoice(choice);
        } while (choice != 0);
    }

    private void executeChoice(int choice) {
        switch (choice) {
            case 1 -> addNewProductParameters();
            case 2 -> eraseProduct();
        }
    }

    private void eraseProduct() {
        System.out.println("\nEnter the product ID of the product you want to remove: ");

        int inputRemove = scanner.nextInt();
        Object removeThis = products.stream().filter(product -> product.getProductId() == inputRemove)
                .findFirst()
                .orElse(null);

        if (removeThis == null)
            System.out.println("\nI couldn't find that ID, please try again.");

        else {
            System.out.println("\nAre you sure you want to erase: " + removeThis + "?");
            System.out.println("1. To confirm");
            System.out.println("0. To go back to product management menu");

            int choice = scanner.nextInt();
            if (choice == 1)
                products.remove(removeThis);
        }
    }

    private void addNewProductParameters() {
        System.out.println("\nEnter product category (Beer, Wine or Spirit): ");
        scanner.nextLine();
        category = scanner.nextLine();
        System.out.println("Enter the brand: ");
        brand = scanner.nextLine();
        System.out.println("Enter the products name: ");
        productName = scanner.nextLine();
        System.out.println("Enter the price, with decimals: ");
        price = scanner.nextFloat();
        System.out.println("Enter the alcohol percentage: ");
        alcohol = scanner.nextFloat();
        productId = createUniqueId(category);
        createTheProduct();
    }

    public int createUniqueId(String category) {
        long counter = products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .count();
        System.out.println("The product received the ID: " + (int) (decideId(category) + counter + 1));
        return (int) (decideId(category) + counter + 1);
    }

    private int decideId(String category) {
        return switch (category.toLowerCase()) {
            case "beer" -> 10000;
            case "wine" -> 20000;
            case "spirit" -> 30000;
            default -> 40000;
        };
    }

    private void createTheProduct() {
        products.add(new Product(category, brand, productName, price, alcohol, productId));
    }

    public List<Product> getProductList() {
        return products;
    }

    public void checkFileToLoad() {
        String homePath = System.getProperty("user.home");
        Path path = Path.of(homePath, "desktop", "Products of Albin", "products.csv");
        Path backupPath = Path.of("resources", "products.csv");

        if (Files.exists(path))
            loadFromFile(path);
        else loadFromFile(backupPath);
    }

    private void loadFromFile(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            products = lines
                    .map(Manage::createProducts)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Product createProducts(String line) {
        String[] arr = pattern.split(line);
        return new Product(arr[0], arr[1], arr[2], Float.parseFloat(arr[3]), Float.parseFloat(arr[4]), Integer.parseInt(arr[5]));
    }

    private int readChoice(Scanner scanner) {
        return scanner.nextInt();
    }

    private void productManagementMenu() {
        System.out.println("\nProduct management menu");
        System.out.println("1. Add new product");
        System.out.println("2. Erase product");
        System.out.println("0. Back to main menu");
    }
}
