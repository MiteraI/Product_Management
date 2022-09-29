package entity;

public class WorkStation extends Product
{
    public static String ID_FORMAT = "WS\\d{3}";
    public static String FORMAT_STRING = "|%-5s|%-30s|%-15s|%-15s|%-5s|%-15s|%-10s|%-14s|\n";
    private String cpu;
    private String gpu;
    private int ramSize;

    public WorkStation() {}
    
    public WorkStation(String productID, String name, double price, int quantity, boolean status, String cpu, String gpu, int ramSize)
    {
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

    @Override
    public String toFormatString()
    {
        return String.format(FORMAT_STRING, super.getProductID(), super.getName(), cpu, gpu, ramSize + "GB", 
            super.getPrice(), super.getQuantity(), super.getStatus());
    }
}