package se.iths.labb2;

import java.util.*;
import java.util.stream.Collectors;

public class Search{

    void showProductsByCategory(List<Product> productList) {
        productList.stream().filter(product -> product.getCategory().equalsIgnoreCase("beer")).forEach(System.out::println);
        System.out.println();
        productList.stream().filter(product -> product.getCategory().equalsIgnoreCase("wine")).forEach(System.out::println);
        System.out.println();
        productList.stream().filter(product -> product.getCategory().equalsIgnoreCase("spirit")).forEach(System.out::println);
    }

    void showBestAPK(List<Product> productList) {
        List<Product> bestAPK = productList.stream()
                .sorted(Comparator.comparingDouble(p -> p.getAlcohol() / p.getPrice()))
                .collect(Collectors.toList());
        Collections.reverse(bestAPK);
        System.out.println("\nShowing 5 products with best APK from highest to lowest: ");
        bestAPK.stream().limit(5).forEach(System.out::println);
    }

    void showProductsLessThan200(List<Product> productList) {
        productList.stream()
                .filter(product -> product.getPrice() < 200)
                .forEach(System.out::println);
    }

    void showProductsMoreThan200(List<Product> productList) {
        productList.stream()
                .filter(product -> product.getPrice() > 200)
                .forEach(System.out::println);
    }

    void searchByEAN(List<Product> productList, int inputProductId) {
        productList.stream()
                .filter(product -> product.getProductId() == inputProductId)
                .forEach(System.out::println);
    }
}
