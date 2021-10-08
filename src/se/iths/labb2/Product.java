package se.iths.labb2;

import java.util.Objects;

public class Product {
    private final String category;
    private final String brand;
    private final String productName;
    private final float price;
    private final float alcohol;
    private final int productId;

    public Product(String category, String brand, String productName, float price, float alcohol, int productId) {
        this.category = category;
        this.brand = brand;
        this.productName = productName;
        this.price = price;
        this.alcohol = alcohol;
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public String setCategory(String category) {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String setBrand(String brand) {
        return brand;
    }

    public String getProductName() {
        return productName;
    }

    public String setProductName(String productName) {
        return productName;
    }

    public float getPrice() {
        return price;
    }

    public float setPrice(float price) {
        return price;
    }

    public float getAlcohol() {
        return alcohol;
    }

    public float setAlcohol(float alcohol) {
        return alcohol;
    }

    public int getProductId() {
        return productId;
    }

    public int setProductId(int productId) {
        return productId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Product) obj;
        return Objects.equals(this.category, that.category) &&
                Objects.equals(this.brand, that.brand) &&
                Objects.equals(this.productName, that.productName) &&
                Float.floatToIntBits(this.price) == Float.floatToIntBits(that.price) &&
                Float.floatToIntBits(this.alcohol) == Float.floatToIntBits(that.alcohol) &&
                this.productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, brand, productName, price, alcohol, productId);
    }

    @Override
    public String toString() {
        return "Product: [" +
                "Category: " + category + ", " +
                "Brand: " + brand + ", " +
                "Name: " + productName + ", " +
                "Price: " + price + "kr, " +
                "Alcohol: " + alcohol + ", " +
                "ProductId: " + productId + ']';
    }

}
