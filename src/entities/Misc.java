package entities;

import data.Product;

public class Misc extends Product {
    public static final String ID_FORMAT = "M\\d{3}";
    public static final String FORMAT_STRING = "|%-5s|%-30s|%-15s|%-10s|%-15s|\n";

    public Misc() {
    }

    public Misc(String productID, String name, double price, int quantity, boolean status) {
        super(productID, name, price, quantity, status);
    }

    public String toMiscString() {
        return String.format(Misc.FORMAT_STRING, productID, name, price, quantity,
                status ? "Available" : "Not available");
    }
}
