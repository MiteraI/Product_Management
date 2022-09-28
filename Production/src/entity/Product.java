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
public abstract class Product implements Comparable<Product> {
    public static final String NAME_FORMAT = "[a-zA-Z0-9\" \"]{5,100}";
    public static final char SEPARATOR = ',';
    String productID;
    String name;
    double price;
    int quantity;
    boolean status;

    public Product() {
        this.productID = "";
        this.name = "";
        this.price = 0.0;
        this.quantity = 0;
        this.status = false;
    }

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
        return productID + SEPARATOR + name + SEPARATOR + price + SEPARATOR + quantity + SEPARATOR + status;
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
