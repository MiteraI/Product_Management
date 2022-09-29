/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Administrator
 */
public class Product implements Comparable<Product> {
    public static final String ID_FORMAT = "M\\d{3}";
    public static final String NAME_FORMAT = "[a-zA-Z0-9\" \"]{5,100}";
    public static final String FORMAT_STRING = "|%-5s|%-30s|%-15s|%-10s|%-15s|\n";
    public static final char SEPARATOR = ',';
    private String productID;
    private String name;
    private double price;
    private int quantity;
    private boolean status;

    public Product() {}

    public Product(String productID, String name, double price, int quantity, boolean status) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return productID + 
            SEPARATOR + name + 
            SEPARATOR + price + 
            SEPARATOR + quantity + 
            SEPARATOR + status;
    }

    public String toFormatString()
    {
        return String.format(Product.FORMAT_STRING, productID, name, price, quantity, status ? "Available" : "Not available");
    }

    @Override
    public int compareTo(Product p) {
        if(this.getQuantity()== p.getQuantity()){
            if (this.getPrice() > p.getPrice())
                return -1;
            else if (this.getPrice() == p.getPrice()){
                return 0;
            }
            else{
                return 1;
            }    
        } else if(this.getQuantity() > p.getQuantity()){
            return -1;
        }
        else{
            return 1;
        }
    }
}
