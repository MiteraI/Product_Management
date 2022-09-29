package entity;

public class Phone extends Product
{
    public static String ID_FORMAT = "P\\d{3}";
    public static String FORMAT_STRING = "|%-5s|%-30s|%-15s|%-10s|%-5s|%-15s|%-10s|%-14s|\n";
    private String os;
    private int storage;
    private int ramSize;

    public Phone() {}

    public Phone(String productID, String name, double price, int quantity, boolean status, String os, int storage, int ramSize)
    {
        super(productID, name, price, quantity, status);
        this.os = os;
        this.storage = storage;
        this.ramSize = ramSize;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getStorage() {
        return this.storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getRamSize() {
        return this.ramSize;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

    @Override
    public String toString() {
        return super.toString() + 
            Product.SEPARATOR + os + 
            Product.SEPARATOR + storage + 
            Product.SEPARATOR + ramSize;
    }

    @Override
    public String toFormatString()
    {
        return String.format(FORMAT_STRING, super.getProductID(), super.getName(), os, storage + "GB", ramSize + "GB", 
            super.getPrice(), super.getQuantity(), super.getStatus());
    }
}
