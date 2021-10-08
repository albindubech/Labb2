package se.iths.labb2;

import java.util.*;
import java.util.stream.Collectors;

public class Search{

    public void execute() {
        run();
    }

    private final Scanner scanner = new Scanner(System.in);
    Manage manage = new Manage();

    private void run() {
        int choice;
        do {
            printMenuOption();
            choice = readChoice(scanner);
            executeChoice(choice);
        } while (choice != 0);
    }

    private void executeChoice(int choice) {
        switch (choice) {
            case 1 -> showProductsByCategory(manage.getProductList());
            case 2 -> showBestAPK(manage.getProductList());
            case 3 -> showProductsLessThan200(manage.getProductList());
            case 4 -> showProductsMoreThan200(manage.getProductList());
            case 5 -> searchByEAN(manage.getProductList());
        }
    }

    private void showProductsByCategory(List<Product> productList) {
        productList.stream().filter(product -> product.getCategory().equalsIgnoreCase("beer")).forEach(System.out::println);
        System.out.println();
        productList.stream().filter(product -> product.getCategory().equalsIgnoreCase("wine")).forEach(System.out::println);
        System.out.println();
        productList.stream().filter(product -> product.getCategory().equalsIgnoreCase("spirit")).forEach(System.out::println);
    }

    private void showBestAPK(List<Product> productList) {
        List<Product> bestAPK = productList.stream()
                .sorted(Comparator.comparingDouble(p -> p.getAlcohol() / p.getPrice()))
                .collect(Collectors.toList());
        Collections.reverse(bestAPK);
        System.out.println("\nShowing 5 products with best APK from highest to lowest: ");
        bestAPK.stream().limit(5).forEach(System.out::println);
    }

    private void showProductsLessThan200(List<Product> productList) {
        productList.stream()
                .filter(product -> product.getPrice() < 200)
                .forEach(System.out::println);
    }

    private void showProductsMoreThan200(List<Product> productList) {
        productList.stream()
                .filter(product -> product.getPrice() > 200)
                .forEach(System.out::println);
    }

    private void searchByEAN(List<Product> productList) {
        System.out.println("\nWrite the EAN code of the product:");
        int inputProductId = scanner.nextInt();
        productList.stream()
                .filter(product -> product.getProductId() == inputProductId)
                .forEach(System.out::println);
    }

    private void printMenuOption() {
        System.out.println("\nSearch menu");
        System.out.println("1. Show product sorted by category");
        System.out.println("2. Show five products with best alcohol/krona ratio (APK in Sweden)");
        System.out.println("3. Show products less than 200kr");
        System.out.println("4. Show products more than 200kr");
        System.out.println("5. Search product by EAN");
        System.out.println("0. Back to main menu");
        System.out.println("\n(Find your local programmer to add new options for the menu)");
    }

    private int readChoice(Scanner scanner) {
        return scanner.nextInt();
    }
}
