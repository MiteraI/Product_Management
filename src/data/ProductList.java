/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import tools.MyTool;

/**
 *
 * @author Administrator
 */
public class ProductList extends ArrayList<Product> {
    private String datafile = "";
    private final String productHrBreak = String.format(Product.FORMAT_STRING, "", "", "", "", "")
            .replace("|", "+").replace(" ", "-");
    private final String laptopHrBreak = String.format(Laptop.FORMAT_STRING, "", "", "", "", "", "", "", "")
            .replace("|", "+").replace(" ", "-");
    private final String phoneHrBreak = String.format(Phone.FORMAT_STRING, "", "", "", "", "", "", "", "")
            .replace("|", "+").replace(" ", "-");
    private final String wsHrBreak = String.format(WorkStation.FORMAT_STRING, "", "", "", "", "", "", "", "")
            .replace("|", "+").replace(" ", "-");
    private final String regexBlank = "^$";
    private final String regexDecimal = "\\d{0,4}\\.\\d+";
    private final String regexInteger = "\\d{1,3}";
    private boolean changed = false;

    public ProductList() {
    }

    private void loadProductFromFile() {
        List<String> list = MyTool.readLinesFromFile(datafile);
        for (String str : list) {
            String[] parts = str.split("" + Product.SEPARATOR);
            String productID = parts[0];
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            int quantity = Integer.parseInt(parts[3]);
            boolean status = MyTool.parseBool(parts[4]);
            if (productID.matches(Laptop.ID_FORMAT)) {
                String cpu = parts[5];
                String gpu = parts[6];
                int ramSize = Integer.parseInt(parts[7]);
                this.add(new Laptop(productID, name, price, quantity, status, cpu, gpu, ramSize));
            } else if (productID.matches(Phone.ID_FORMAT)) {
                String os = parts[5];
                int storage = Integer.parseInt(parts[6]);
                int ramSize = Integer.parseInt(parts[7]);
                this.add(new Phone(productID, name, price, quantity, status, os, storage, ramSize));
            } else if (productID.matches(WorkStation.ID_FORMAT)) {
                String cpu = parts[5];
                String gpu = parts[6];
                int ramSize = Integer.parseInt(parts[7]);
                this.add(new WorkStation(productID, name, price, quantity, status, cpu, gpu, ramSize));
            }
        }
    }

    public void initWithFile() {
        Config cR = new Config();
        datafile = cR.getProductFile();
        loadProductFromFile();
    }

    private ArrayList<Product> searchByID(String productID) {
        ArrayList<Product> list = new ArrayList<>();
        for (Product p : this) {
            if (p.getProductID().toUpperCase().contains(productID.toUpperCase()))
                list.add(p);
        }
        return list;
    }

    private ArrayList<Product> searchByName(String name) {
        ArrayList<Product> list = new ArrayList<>();
        for (Product p : this) {
            if (p.getName().toUpperCase().contains(name.toUpperCase()))
                list.add(p);
        }
        return list;
    }

    private void printProduct(Product p) {
        System.out.print(productHrBreak);
        System.out.printf(Product.FORMAT_STRING, "ID", "Name", "Price", "Quantity", "Status");
        System.out.print(productHrBreak);
        System.out.print(p.toProductString());
        System.out.print(productHrBreak);
    }

    private void printProductList(ArrayList<Product> list) {
        if (list.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        System.out.print(productHrBreak);
        System.out.printf(Product.FORMAT_STRING, "ID", "Name", "Price", "Quantity", "Status");
        System.out.print(productHrBreak);
        for (Product p : list)
            System.out.print(p.toProductString());
        System.out.print(productHrBreak);
    }

    private void printLaptop(Laptop laptop) {
        System.out.print(laptopHrBreak);
        System.out.printf(Laptop.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.print(laptopHrBreak);
        System.out.print(laptop.toLaptopString());
        System.out.print(laptopHrBreak);
    }

    private void printLaptopList(ArrayList<Laptop> list) {
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

    private void printPhone(Phone phone) {
        System.out.print(phoneHrBreak);
        System.out.printf(Phone.FORMAT_STRING, "ID", "Name", "OS", "Storage", "RAM", "Price", "Quantity", "Status");
        System.out.print(phoneHrBreak);
        System.out.print(phone.toPhoneString());
        System.out.print(phoneHrBreak);
    }

    private void printPhoneList(ArrayList<Phone> list) {
        if (list.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }

        System.out.print(phoneHrBreak);
        System.out.printf(Phone.FORMAT_STRING, "ID", "Name", "OS", "Storage", "RAM", "Price", "Quantity", "Status");
        System.out.print(phoneHrBreak);
        for (Phone phone : list)
            System.out.print(phone.toPhoneString());
        System.out.print(phoneHrBreak);
    }

    private void printWorkStation(WorkStation ws) {
        System.out.print(wsHrBreak);
        System.out.printf(WorkStation.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.print(wsHrBreak);
        System.out.print(ws.toWorkStationString());
        System.out.print(wsHrBreak);
    }

    private void printWorkStationList(ArrayList<WorkStation> list) {
        if (list.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }

        System.out.print(wsHrBreak);
        System.out.printf(WorkStation.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.print(wsHrBreak);
        for (WorkStation ws : list)
            System.out.print(ws.toWorkStationString());
        System.out.print(wsHrBreak);
    }

    public void checkExistProduct() {
        // loadProductFromFile();
        int attr = MyTool.readRangeInt("Search by: [1] ID; [2] Name: ", 1, 2);
        ArrayList<Product> list = new ArrayList<>();
        switch (attr) {
            case 1 -> {
                String productID = MyTool.readNonBlank("Enter product ID: ");
                list = searchByID(productID);
            }
            case 2 -> {
                String name = MyTool.readNonBlank("Enter product name: ");
                list = searchByName(name);
            }
        }
        if (list.isEmpty())
            System.out.println("No products found!");
        else
            System.out.println("Product(s) found!");
    }

    public void searchProduct() {
        int attr = MyTool.readRangeInt("Search by: [1] ID; [2] Name: ", 1, 2);
        ArrayList<Product> list = new ArrayList<>();
        switch (attr) {
            case 1 -> {
                String productID = MyTool.readNonBlank("Enter product ID: ");
                list = searchByID(productID);
            }
            case 2 -> {
                String name = MyTool.readNonBlank("Enter product name: ");
                list = searchByName(name);
            }
        }
        if (list.isEmpty()) {
            System.out.println("No products found!");
        } else if (list.size() == 1) {
            Product p = list.get(0);
            if (p instanceof Laptop laptop)
                printLaptop(laptop);
            else if (p instanceof Phone phone)
                printPhone(phone);
            else if (p instanceof WorkStation ws)
                printWorkStation(ws);
            else
                printProduct(p);
        } else {
            printProductList(list);
        }
    }

    public void addProduct() {
        int choice = MyTool.readRangeInt("Add: [1] Laptop; [2] Phone; [3] WorkStation; [4] Misc: ", 0, 5);
        switch (choice) {
            case 1 -> printLaptop(addLaptop());
            case 2 -> printPhone(addPhone());
            case 3 -> printWorkStation(addWorkStation());
            case 4 -> printProduct(addMisc());
        }
        System.out.println("Product has been added.");
        changed = true;
    }

    private Laptop addLaptop() {
        String productID = MyTool.readPattern("New laptop's ID: ", Laptop.ID_FORMAT);
        while (!searchByID(productID).isEmpty()) {
            System.out.println("ID is duplicated!");
            productID = MyTool.readPattern("New laptop's ID: ", Laptop.ID_FORMAT);
        }
        String name = MyTool.readPattern("New laptop's name: ", Laptop.NAME_FORMAT);
        String cpu = MyTool.readNonBlank("New laptop's CPU: ");
        String gpu = MyTool.readNonBlank("New laptop's GPU: ");
        int ramSize = MyTool.readRangeInt("New laptop's RAM size: ", 0, 32);
        double price = MyTool.readRangeDouble("New laptop's price: ", 0, 10000);
        int quantity = MyTool.readRangeInt("New laptop's quantity: ", 0, 1000);
        boolean status = MyTool.readBool("Is the laptop available?");
        Laptop laptop = new Laptop(productID, name, price, quantity, status, cpu, gpu, ramSize);
        this.add(laptop);
        return laptop;
    }

    private Phone addPhone() {
        String productID = MyTool.readPattern("New phone's ID: ", Phone.ID_FORMAT);
        while (!searchByID(productID).isEmpty()) {
            System.out.println("ID is duplicated!");
            productID = MyTool.readPattern("New phone's ID: ", Phone.ID_FORMAT);
        }
        String name = MyTool.readPattern("New phone's name: ", Phone.NAME_FORMAT);
        String os = MyTool.readNonBlank("New phone's OS: ");
        int storage = MyTool.readRangeInt("New phone's storage: ", 0, 2048);
        int ramSize = MyTool.readRangeInt("New phone's RAM size: ", 0, 8);
        double price = MyTool.readRangeDouble("New phone's price: ", 0, 10000);
        int quantity = MyTool.readRangeInt("New phone's quantity: ", 0, 1000);
        boolean status = MyTool.readBool("Is the phone available?");
        Phone phone = new Phone(productID, name, price, quantity, status, os, storage, ramSize);
        this.add(phone);
        return phone;
    }

    private WorkStation addWorkStation() {
        String productID = MyTool.readPattern("New workstation's ID: ", WorkStation.ID_FORMAT);
        while (!searchByID(productID).isEmpty()) {
            System.out.println("ID is duplicated!");
            productID = MyTool.readPattern("New workstation's ID: ", WorkStation.ID_FORMAT);
        }
        String name = MyTool.readPattern("New workstation's name: ", WorkStation.NAME_FORMAT);
        String cpu = MyTool.readNonBlank("New workstation's CPU: ");
        String gpu = MyTool.readNonBlank("New workstation's GPU: ");
        int ramSize = MyTool.readRangeInt("New workstation's RAM size: ", 0, 16384);
        double price = MyTool.readRangeDouble("New workstation's price: ", 0, 10000);
        int quantity = MyTool.readRangeInt("New workstation's quantity: ", 0, 1000);
        boolean status = MyTool.readBool("Is the workstation available?");
        WorkStation ws = new WorkStation(productID, name, price, quantity, status, cpu, gpu, ramSize);
        this.add(ws);
        return ws;
    }

    private Product addMisc() {
        String productID = MyTool.readPattern("New product's ID: ", Product.ID_FORMAT);
        while (!searchByID(productID).isEmpty()) {
            System.out.println("ID is duplicated!");
            productID = MyTool.readPattern("New product's ID: ", Product.ID_FORMAT);
        }
        String name = MyTool.readPattern("New product's name: ", Product.NAME_FORMAT);
        double price = MyTool.readRangeDouble("New product's price: ", 0, 10000);
        int quantity = MyTool.readRangeInt("New product's quantity: ", 0, 1000);
        boolean status = MyTool.readBool("Is the product available?");
        Product p = new Product(productID, name, price, quantity, status);
        this.add(p);
        return p;
    }

    public void updateProduct() {
        String productID = MyTool.readPattern("ID of product to be updated: ",
                Product.ID_FORMAT + "|" + Laptop.ID_FORMAT + "|" + Phone.ID_FORMAT + "|" + WorkStation.ID_FORMAT);
        ArrayList<Product> list = searchByID(productID);
        if (list.isEmpty()) {
            System.out.println("No products found!");
            return;
        }
        Product p = list.get(0);
        if (p instanceof Laptop laptop) {
            updateLaptop(laptop);
            printLaptop(laptop);
        } else if (p instanceof Phone phone) {
            updatePhone(phone);
            printPhone(phone);
        } else if (p instanceof WorkStation ws) {
            updateWorkStation(ws);
            printWorkStation(ws);
        } else {
            updateProduct(p);
            printProduct(p);
        }
        System.out.println("Product has been updated.");
        changed = true;
    }

    private void updateLaptop(Laptop laptop) {
        updateProduct(laptop);

        String tmp = MyTool.readPattern("New CPU (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            laptop.setCpu(tmp);

        tmp = MyTool.readPattern("New GPU (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            laptop.setGpu(tmp);

        tmp = MyTool.readPattern("New RAM size (leave blank to skip): ", regexBlank + "|" + regexInteger);
        if (!tmp.isBlank())
            laptop.setRamSize(Integer.parseInt(tmp));
    }

    private void updatePhone(Phone phone) {
        updateProduct(phone);

        String tmp = MyTool.readPattern("New OS (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            phone.setOs(tmp);

        tmp = MyTool.readPattern("New storage (leave blank to skip): ", regexBlank + "|" + regexInteger);
        if (!tmp.isBlank())
            phone.setStorage(Integer.parseInt(tmp));

        tmp = MyTool.readPattern("New RAM size (leave blank to skip): ", regexBlank + "|" + regexInteger);
        if (!tmp.isBlank())
            phone.setRamSize(Integer.parseInt(tmp));
    }

    private void updateWorkStation(WorkStation ws) {
        updateProduct(ws);

        String tmp = MyTool.readPattern("New CPU (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            ws.setCpu(tmp);

        tmp = MyTool.readPattern("New GPU (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            ws.setGpu(tmp);

        tmp = MyTool.readPattern("New RAM size (leave blank to skip): ", regexBlank + "|" + regexInteger);
        if (!tmp.isBlank())
            ws.setRamSize(Integer.parseInt(tmp));
    }

    private void updateProduct(Product p) {
        String tmp = MyTool.readPattern("New name (leave blank to skip): ", regexBlank + "|" + Product.NAME_FORMAT);
        if (tmp.isBlank())
            p.setName(tmp);

        tmp = MyTool.readPattern("New price (leave blank to skip): ",
                regexBlank + "|" + regexDecimal + "|" + regexInteger);
        if (!tmp.isBlank())
            p.setPrice(Double.parseDouble(tmp));

        tmp = MyTool.readPattern("New quantity (leave blank to skip): ", regexBlank + "|" + regexInteger);
        if (!tmp.isBlank())
            p.setQuantity(Integer.parseInt(tmp));

        tmp = MyTool.readPattern("New status (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            p.setStatus(MyTool.parseBool(tmp));
    }

    public void deleteProduct() {
        String productID = MyTool.readPattern("ID of product to be deleted: ",
                Product.ID_FORMAT + "|" + Laptop.ID_FORMAT + "|" + Phone.ID_FORMAT + "|" + WorkStation.ID_FORMAT);
        ArrayList<Product> list = searchByID(productID);
        if (list.isEmpty()) {
            System.out.println("No products found!");
            return;
        }
        Product p =list.get(0);
        this.remove(p);
        System.out.println("The following product has been removed.");
        if (p instanceof Laptop laptop)
            printLaptop(laptop);
        else if (p instanceof Phone phone)
            printPhone(phone);
        else if (p instanceof WorkStation ws)
            printWorkStation(ws);
        else
            printProduct(p);
        changed = true;
    }

    public void printAllProducts()
    {   
        Collections.sort(this);
        printProductList(this);
    }

    public void printAllLaptops()
    {
        ArrayList<Laptop> list = new ArrayList<>();
        for (Product p : this)
        {
            if (p instanceof Laptop laptop)
                list.add(laptop);
        }
        Collections.sort(list);
        printLaptopList(list);
    }

    public void printAllPhones()
    {
        ArrayList<Phone> list = new ArrayList<>();
        for (Product p : this)
        {
            if (p instanceof Phone phone)
                list.add(phone);
        }
        Collections.sort(list);
        printPhoneList(list);
    }

    public void printAllWorkStations()
    {
        ArrayList<WorkStation> list = new ArrayList<>();
        for (Product p : this)
        {
            if (p instanceof WorkStation ws)
                list.add(ws);
        }
        Collections.sort(list);
        printWorkStationList(list);
    }

    public void writeProductToFile() {
        if (changed) {
            MyTool.writeFile(datafile, this);
            changed = false;
            System.out.println("Successfully Written!");
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
