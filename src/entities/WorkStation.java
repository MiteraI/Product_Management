package entities;

import data.Product;

public class WorkStation extends Product {
    public static String ID_FORMAT = "WS\\d{3}";
    public static String FORMAT_STRING = "|%-5s|%-30s|%-15s|%-15s|%-10s|%-15s|%-10s|%-15s|\n";
    private String cpu;
    private String gpu;
    private int ramSize;

    public WorkStation() {
    }

    public WorkStation(String productID, String name, double price, int quantity, boolean status, String cpu,
            String gpu, int ramSize) {
        super(productID, name, price, quantity, status);
        this.cpu = cpu;
        this.gpu = gpu;
        this.ramSize = ramSize;
    }

    public String getCpu() {
        return this.cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return this.gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
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
                Product.SEPARATOR + cpu +
                Product.SEPARATOR + gpu +
                Product.SEPARATOR + ramSize;
    }

    public String toWorkStationString() {
        return String.format(FORMAT_STRING, productID, name, cpu, gpu, ramSize + "GB", price, quantity,
                status ? "Available" : "Not available");
    }
}