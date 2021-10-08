package se.iths.labb2;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    Manage manage;
    Search search;
    Save save;

    Menu() {
        this.manage = new Manage();
        this.search = new Search();
        this.save = new Save();
    }

    private void run() {
        manage.checkFileToLoad();
        int choice;
        do {
            printMenuOption();
            choice = readChoice();
            executeChoice(choice);
        } while (choice != 0);
    }

    private void executeChoice(int choice) {
        switch (choice) {
            case 1 -> manage.execute();
            case 2 -> runSearch();
            case 3 -> save.saveToFile(manage.getProductList());
        }
    }

    private void printMenuOption() {
        System.out.println("\nSystembolaget main menu:");
        System.out.println("1. Product management");
        System.out.println("2. Search for products");
        System.out.println("3. Save changes");
        System.out.println("0. Exit program (don't forget to save before exiting)");
    }

    private void runSearch() {
        int choice;
        do {
            printSearchMenuOption();
            choice = readChoice();
            executeSearchChoice(choice);
        } while (choice != 0);
    }

    private void printSearchMenuOption() {
        System.out.println("\nSearch menu");
        System.out.println("1. Show product sorted by category");
        System.out.println("2. Show five products with best alcohol/krona ratio (APK in Sweden)");
        System.out.println("3. Show products less than 200kr");
        System.out.println("4. Show products more than 200kr");
        System.out.println("5. Search product by EAN");
        System.out.println("0. Back to main menu");
        System.out.println("\n(Find your local programmer to add new options for the menu)");
    }

    private void executeSearchChoice(int choice) {
        switch (choice) {
            case 1 -> search.showProductsByCategory(manage.getProductList());
            case 2 -> search.showBestAPK(manage.getProductList());
            case 3 -> search.showProductsLessThan200(manage.getProductList());
            case 4 -> search.showProductsMoreThan200(manage.getProductList());
            case 5 -> search.searchByEAN(manage.getProductList(), readChoice("Enter EAN for your product of choice: "));
        }
    }

    private int readChoice(String promptText) {
        System.out.println(promptText);
        return readChoice();
    }

    private int readChoice() {
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        System.out.println("\nWelcome to Systembolaget!");
        Menu menu = new Menu();
        menu.run();
    }
}
