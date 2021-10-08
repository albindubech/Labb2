package se.iths.labb2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Save {

    private String convertProductToListOfString(Product product) {
        return product.getCategory() + "," + product.getBrand() + "," + product.getProductName() + ","
                + product.getPrice() + "," + product.getAlcohol() + "," + product.getProductId();
    }

    void saveToFile(List<Product> products) {
        List<String> stringList = products.stream()
                .map(this::convertProductToListOfString)
                .toList();

        String homePath = System.getProperty("user.home");
        Path path = Path.of(homePath,"desktop", "Products of Albin", "products.csv");

        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.write(path, stringList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
