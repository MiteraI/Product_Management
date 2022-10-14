package lists;

import data.Config;
import data.Product;
import entities.Laptop;
import tools.MyTool;
import java.util.ArrayList;
import java.util.List;

public class LaptopList extends ArrayList<Laptop> {
    private String laptopFile;
    private final String laptopHrBreak = String.format(Laptop.FORMAT_STRING, "", "", "", "", "", "", "", "")
            .replace("|", "+").replace(" ", "-");
    private List<String> laptopNames;
    private boolean changed;

    public LaptopList() {
        super();
        initialize();
    }

    private final void initialize() {
        Config config = new Config();
        laptopFile = config.getLaptopFile();
        laptopNames = MyTool.readLinesFromFile(config.getLaptopNamesFile());
        importFromFile();
    }

    private ArrayList<Laptop> searchID(String productID) {
        ArrayList<Laptop> list = new ArrayList<>();
        for (Laptop laptop : this) {
            if (laptop.getProductID().toUpperCase().contains(productID.toUpperCase()))
                list.add(laptop);
        }
        return list;
    }

    private ArrayList<Laptop> searchName(String name) {
        ArrayList<Laptop> list = new ArrayList<>();
        for (Laptop laptop : this) {
            if (laptop.getName().toUpperCase().contains(name.toUpperCase()))
                list.add(laptop);
        }
        return list;
    }

    public Laptop addLaptop() {
        String productID = MyTool.readPattern("New laptop's ID: ", Laptop.ID_FORMAT);
        while (!searchID(productID).isEmpty()) {
            System.out.println("ID is duplicated!");
            productID = MyTool.readPattern("New laptop's ID: ", Laptop.ID_FORMAT);
        }
        String name = MyTool.readName("New laptop's name: ", laptopNames);
        String cpu = MyTool.readNonBlank("New laptop's CPU: ");
        String gpu = MyTool.readNonBlank("New laptop's GPU: ");
        int ramSize = MyTool.readRangeInt("New laptop's RAM size: ", 0, 32);
        double price = MyTool.readRangeDouble("New laptop's price: ", 0, 10000);
        int quantity = MyTool.readRangeInt("New laptop's quantity: ", 0, 1000);
        boolean status = MyTool.readBool("Is the laptop available?");
        Laptop laptop = new Laptop(productID, name, price, quantity, status, cpu, gpu, ramSize);
        this.add(laptop);
        changed = true;
        printLaptop(laptop);
        return laptop;
    }

    public void searchByID(String productID) {
        ArrayList<Laptop> list = searchID(productID);
        if (list.isEmpty())
            System.out.println("No laptops found!");
        else
            printLaptopList(list);
    }

    public void searchByName(String name) {
        ArrayList<Laptop> list = searchName(name);
        if (list.isEmpty())
            System.out.println("No laptops found!");
        else
            printLaptopList(list);
    }

    public void updateLaptop(String productID) {
        ArrayList<Laptop> list = searchID(productID);
        if (list.isEmpty()) {
            System.out.println("No laptops match the given ID!");
            return;
        }
        
        Laptop laptop = list.get(0);
        String tmp = MyTool.readName("New name (leave blank to skip): ", laptopNames);
        if (!tmp.isBlank())
            laptop.setName(tmp);

        tmp = MyTool.readPattern("New price (leave blank to skip): ",
                MyTool.regexBlank + "|" + MyTool.regexDecimal + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            laptop.setPrice(Double.parseDouble(tmp));

        tmp = MyTool.readPattern("New quantity (leave blank to skip): ", MyTool.regexBlank + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            laptop.setQuantity(Integer.parseInt(tmp));

        tmp = MyTool.readPattern("New status (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            laptop.setStatus(MyTool.parseBool(tmp));

        tmp = MyTool.readPattern("New CPU (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            laptop.setCpu(tmp);

        tmp = MyTool.readPattern("New GPU (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            laptop.setGpu(tmp);

        tmp = MyTool.readPattern("New RAM size (leave blank to skip): ", MyTool.regexBlank + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            laptop.setRamSize(Integer.parseInt(tmp));

        System.out.println("Laptop updated.");
        printLaptop(laptop);
        changed = true;
    }

    public void deleteLaptop (String productID) {
        ArrayList<Laptop> list = searchID(productID);
        if (list.isEmpty()) {
            System.out.println("No laptops match the given ID!");
            return;
        }

        Laptop laptop = list.get(0);
        remove(laptop);
        System.out.println("The following laptop has been deleted.");
        printLaptop(laptop);
        changed = true;
    }

    public void printLaptop(Laptop laptop) {
        System.out.print(laptopHrBreak);
        System.out.printf(Laptop.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.print(laptopHrBreak);
        System.out.print(laptop.toLaptopString());
        System.out.print(laptopHrBreak);
    }

    public void printLaptopList(ArrayList<Laptop> list) {
        if (list.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }

        System.out.print(laptopHrBreak);
        System.out.printf(Laptop.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.print(laptopHrBreak);
        for (Laptop laptop : list)
            System.out.print(laptop.toLaptopString());
        System.out.print(laptopHrBreak);
    }

    public void importFromFile() {
        List<String> list = MyTool.readLinesFromFile(laptopFile);
        for (String line : list) {
            String[] parts = line.split("" + Product.SEPARATOR);
            String productID = parts[0];
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            int quantity = Integer.parseInt(parts[3]);
            boolean status = MyTool.parseBool(parts[4]);
            String cpu = parts[5];
            String gpu = parts[6];
            int ramSize = Integer.parseInt(parts[7]);
            this.add(new Laptop(productID, name, price, quantity, status, cpu, gpu, ramSize));
        }
    }

    public void exportToFile() {
        if (changed) {
            MyTool.writeFile(laptopFile, this);
            changed = false;
            System.out.println("Laptop list is successfully saved.");
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}