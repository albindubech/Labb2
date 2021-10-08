package se.iths.labb2;

import java.util.Scanner;

import static se.iths.labb2.Manage.checkFileToLoad;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    Manage manage = new Manage();
    Search search = new Search();
    Save save = new Save();

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
            case 1 -> manage.execute();
            case 2 -> search.execute();
            case 3 -> save.saveToFile();
        }
    }

    private void printMenuOption() {
        System.out.println("\nSystembolaget main menu:");
        System.out.println("1. Product management");
        System.out.println("2. Search for products");
        System.out.println("3. Save changes");
        System.out.println("0. Exit program (don't forget to save before exiting)");
    }

    private int readChoice(Scanner scanner) {
        return scanner.nextInt();
    }

    public static void main(String[] args) {
//        Manage manage = new Manage();
        checkFileToLoad();
        System.out.println("\nWelcome to Systembolaget!");
        Menu menu = new Menu();
        menu.run();
    }
}
