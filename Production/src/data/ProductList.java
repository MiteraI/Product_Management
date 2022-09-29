/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.*;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import tools.MyTool;
/**
 *
 * @author Administrator
 */
public class ProductList extends ArrayList<Product> {
    private String datafile="";
    private final String productHrBreak = String.format(Product.FORMAT_STRING, "", "", "", "", "").replace("|", "+").replace(" ", "-");
    private final String laptopHrBreak = String.format(Laptop.FORMAT_STRING, "", "", "", "", "", "", "", "").replace("|", "+").replace(" ", "-");
    private final String phoneHrBreak = String.format(Phone.FORMAT_STRING, "", "", "", "", "", "", "", "").replace("|", "+").replace(" ", "-");
    private final String wsHrBreak = String.format(WorkStation.FORMAT_STRING, "", "", "", "", "", "", "", "").replace("|", "+").replace(" ", "-");
    private final String regexBlank = "^$";
    private final String regexDecimal = "\\d{0,4}\\.\\d+";
    private final String regexInteger = "\\d{1,3}";
    private boolean changed = false;

    public ProductList() {}

    private void loadProductFromFile()
    {
        List<String> list = MyTool.readLinesFromFile(datafile);
        for(String str: list)
        {
            String[] parts = str.split("" + Product.SEPARATOR);
            String productID = parts[0];
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            int quantity = Integer.parseInt(parts[3]);
            boolean status = MyTool.parseBool(parts[4]);
            if (productID.matches(Laptop.ID_FORMAT))
            {
                String cpu = parts[5];
                String gpu = parts[6];
                int ramSize = Integer.parseInt(parts[7]);
                this.add(new Laptop(productID, name, price, quantity, status, cpu, gpu, ramSize));
            }
            else if (productID.matches(Phone.ID_FORMAT))
            {
                String os = parts[5];
                int storage = Integer.parseInt(parts[6]);
                int ramSize = Integer.parseInt(parts[7]);
                this.add(new Phone(productID, name, price, quantity, status, os, storage, ramSize));
            }
            else if (productID.matches(WorkStation.ID_FORMAT))
            {
                String cpu = parts[5];
                String gpu = parts[6];
                int ramSize = Integer.parseInt(parts[7]);
                this.add(new WorkStation(productID, name, price, quantity, status, cpu, gpu, ramSize));
            }
        }
    }

    public void initWithFile()
    {
        Config cR = new Config();
        datafile = cR.getProductFile();
        loadProductFromFile();   
    }

    private ArrayList<Product> searchByID(String productID)
    {
        ArrayList<Product> list = new ArrayList<>();
        for (Product p : this)
        {
            if (p.getProductID().toUpperCase().contains(productID.toUpperCase()))
                list.add(p);
        }
        return list;
    }

    private ArrayList<Product> searchByName(String name)
    {
        ArrayList<Product> list = new ArrayList<>();
        for (Product p : this)
        {
            if (p.getName().toUpperCase().contains(name.toUpperCase()))
                list.add(p);
        }
        return list;
    }

    private void printProduct(Product p)
    {
        System.out.println(productHrBreak);
        System.out.printf(Product.FORMAT_STRING, "ID", "Name", "Price", "Quantity", "Status");
        System.out.println(productHrBreak);
        System.out.println(p.toFormatString());
        System.out.println(productHrBreak);
    }

    private void printProductList(ArrayList<Product> list)
    {
        if (list.isEmpty())
        {
            System.out.println("Empty list!");
            return;
        }
        System.out.println(productHrBreak);
        System.out.printf(Product.FORMAT_STRING, "ID", "Name", "Price", "Quantity", "Status");
        System.out.println(productHrBreak);
        for (Product p : list)
            System.out.println(p.toFormatString());
        System.out.println(productHrBreak);
    }

    private void printLaptop(Laptop laptop)
    {
        System.out.println(laptopHrBreak);
        System.out.printf(Laptop.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.println(laptopHrBreak);
        System.out.println(laptop.toFormatString());
        System.out.println(laptopHrBreak);
    }

    private void printLaptopList(ArrayList<Laptop> list)
    {
        if (list.isEmpty())
        {
            System.out.println("Empty list!");
            return;
        }
        
        System.out.println(laptopHrBreak);
        System.out.printf(Laptop.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.println(laptopHrBreak);
        for (Laptop laptop : list)
            System.out.println(laptop.toFormatString());
        System.out.println(laptopHrBreak);
    }

    private void printPhone(Phone phone)
    {
        System.out.println(phoneHrBreak);
        System.out.printf(Laptop.FORMAT_STRING, "ID", "Name", "OS", "Storage", "RAM", "Price", "Quantity", "Status");
        System.out.println(phoneHrBreak);
        System.out.println(phone.toFormatString());
        System.out.println(phoneHrBreak);
    }

    private void printPhoneList(ArrayList<Phone> list)
    {
        if (list.isEmpty())
        {
            System.out.println("Empty list!");
            return;
        }
        
        System.out.println(phoneHrBreak);
        System.out.printf(Laptop.FORMAT_STRING, "ID", "Name", "OS", "Storage", "RAM", "Price", "Quantity", "Status");
        System.out.println(phoneHrBreak);
        for (Phone phone : list)
            System.out.println(phone.toFormatString());
        System.out.println(phoneHrBreak);
    }

    private void printWorkStation(WorkStation ws)
    {
        System.out.println(wsHrBreak);
        System.out.printf(WorkStation.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.println(wsHrBreak);
        System.out.println(ws.toFormatString());
        System.out.println(wsHrBreak);
    }

    private void printWorkStationList(ArrayList<WorkStation> list)
    {
        if (list.isEmpty())
        {
            System.out.println("Empty list!");
            return;
        }
        
        System.out.println(wsHrBreak);
        System.out.printf(WorkStation.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.println(wsHrBreak);
        for (WorkStation ws : list)
            System.out.println(ws.toFormatString());
        System.out.println(wsHrBreak);
    }

    public void checkExistProduct()
    {
        //loadProductFromFile();
        int attr = MyTool.readRangeInt("Search by: [1] ID; [2] Name", 1, 2);
        ArrayList<Product> list = new ArrayList<>();
        switch (attr)
        {
            case 1 ->
            {
                String productID = MyTool.readNonBlank("Enter product ID: ");
                list = searchByID(productID);
            }
            case 2 ->
            {
                String name = MyTool.readNonBlank("Enter product name: ");
                list = searchByName(name);
            }
        }
        if (list.isEmpty())
            System.out.println("No products found!");
        else
            System.out.println("Product(s) found!");
    }

    public void searchProduct()
    {
        int attr = MyTool.readRangeInt("Search by: [1] ID; [2] Name", 1, 2);
        ArrayList<Product> list = new ArrayList<>();
        switch (attr)
        {
            case 1 ->
            {
                String productID = MyTool.readNonBlank("Enter product ID: ");
                list = searchByID(productID);
            }
            case 2 ->
            {
                String name = MyTool.readNonBlank("Enter product name: ");
                list = searchByName(name);
            }
        }
        if (list.isEmpty())
        {
            System.out.println("No products found!");
        }
        else if (list.size() == 1)
        {
            Product p = list.get(0);
            if (p instanceof Laptop laptop)
                printLaptop(laptop);
            else if (p instanceof Phone phone)
                printPhone(phone);
            else if (p instanceof WorkStation ws)
                printWorkStation(ws);
            else
                printProduct(p);
        }
        else
        {
            printProductList(list);
        }
    }

    public void addProduct()
    {
        int choice = MyTool.readRangeInt("Add: [1] Laptop; [2] Phone; [3] WorkStation; [4] Misc", 1, 4);
        switch (choice)
        {
            case 1 -> printLaptop(addLaptop());
            case 2 -> printPhone(addPhone());
            case 3 -> printWorkStation(addWorkStation());
            case 4 -> printProduct(addMisc());
        }
        System.out.println("Product has been added.");
        changed = true;
    }

    private Laptop addLaptop()
    {
        String productID = MyTool.readPattern("New laptop's ID: ", Laptop.ID_FORMAT);
        while (!searchByID(productID).isEmpty())
        {
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
        return new Laptop(productID, name, price, quantity, status, cpu, gpu, ramSize);
    }

    private Phone addPhone()
    {

    }

    private WorkStation addWorkStation()
    {

    }

    private Product addMisc()
    {

    }

    public void updateProduct(String productID)
    {
        ArrayList<Product> list = searchByID(productID);
        if (list.isEmpty())
        {
            System.out.println("No products found!");
            return;
        }
        Product p = list.get(0);
        if (p instanceof Laptop laptop)
        {
            updateLaptop(laptop);
            printLaptop(laptop);
        }
        else if (p instanceof Phone phone)
        {
            updatePhone(phone);
            printPhone(phone);
        }
        else if (p instanceof WorkStation ws)
        {
            updateWorkStation(ws);
            printWorkStation(ws);
        }
        else
        {
            updateProduct(p);
            printProduct(p);
        }
        System.out.println("Product has been updated.");
        changed = true;
    }

    private void updateLaptop(Laptop laptop)
    {
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

    private void updatePhone(Phone phone)
    {
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

    private void updateWorkStation(WorkStation ws)
    {
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

    private void updateProduct(Product p)
    {
        String tmp = MyTool.readPattern("New name (leave blank to skip): ", regexBlank + "|" + Product.NAME_FORMAT);
        if (tmp.isBlank())
            p.setName(tmp);

        tmp = MyTool.readPattern("New price (leave blank to skip): ", regexBlank + "|" + regexDecimal + "|" + regexInteger);
        if (!tmp.isBlank())
            p.setPrice(Double.parseDouble(tmp));
        
        tmp = MyTool.readPattern("New quantity (leave blank to skip): ", regexBlank + "|" + regexInteger);
        if (!tmp.isBlank())
            p.setQuantity(Integer.parseInt(tmp));
        
        tmp = MyTool.readPattern("New status (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            p.setStatus(MyTool.parseBool(tmp));
    }

    /*public void addProduct()
    {
        String productID;
        String name;
        double price;
        int quantity;
        String status;
        int pos;
        do {
            productID = MyTool.readPattern("New product ID", Product.ID_FORMAT);
            productID = productID.toUpperCase();
            pos = checkID(productID);
            if (pos >= 0) {
                System.out.println("Product ID is duplicated!");
            }
        } while (pos >= 0);
        do {
            name = MyTool.readPattern("Name of new product", Product.PRODUCT_FORMAT);
            if (searchName(name)) {
                System.out.println("Product name is duplicated!");
            }
        } while (searchName(name));
        price = MyTool.readRangeDouble("Price of new product", 0, 10000);
        quantity = MyTool.readRangeInt("Quantity of new product", 0, 1000);
                
        status = MyTool.readStatus("Status of new product");
        Product p = new Product(productID, name, price, quantity, status);
        this.add(p);
        System.out.println("Product has been updated");
        System.out.println("+-----+------------------------------+---------------+----------+--------------+");
        System.out.format("|%-5s|%-30s|%-15s|%-10s|%-14s|\n","ID","Name","Price","Quantity","Status");
        System.out.println("+-----+------------------------------+---------------+----------+--------------+");
        System.out.format("|%-5s|%-30s|%-15.2f|%-10d|%-14s|\n",productID,name,price,quantity,status);
        System.out.println("+-----+------------------------------+---------------+----------+--------------+");
        changed = true;
    }*/

    public void deleteProduct() {
        int pos;
        do{
            String productID = MyTool.readPattern("Enter ID of a product to delete",Product.ID_FORMAT).toUpperCase();
            pos = checkID(productID);
            if (pos < 0) {    
                System.out.println("This product is not found!");
            }
        }while(pos<0);
        this.remove(pos);
        System.out.println("A product has been deleted!");
        changed = true;
        
    }

    public void updateProduct() {
        String productID = MyTool.readPattern("Enter ID needs updating", Product.ID_FORMAT);
        int pos = checkID(productID);
        String name;
        double price;
        int quantity;
        String status;
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            do {
                name = MyTool.readPattern("Name of new product", Product.PRODUCT_FORMAT);
                if (searchProduct(name)) {
                    System.out.println("Product name is duplicated!");
                }
            } while (searchProduct(name));
            price = MyTool.readRangeDouble("Price of new product", 0, 10000);
            quantity = MyTool.readRangeInt("Quantity of new product", 0, 1000);
            status = MyTool.readStatus("Status of new product").toUpperCase();
            this.get(pos).setProductID(productID);
            this.get(pos).setName(name);
            this.get(pos).setPrice(price);
            this.get(pos).setQuantity(quantity);
            this.get(pos).setStatus(status);
            System.out.println("Product has been updated");
            System.out.println("+-----+------------------------------+---------------+----------+--------------+");
            System.out.format("|%-5s|%-30s|%-15s|%-10s|%-14s|\n","ID","Name","Price","Quantity","Status");
            System.out.println("+-----+------------------------------+---------------+----------+--------------+");
            System.out.format("|%-5s|%-30s|%-15.2f|%-10d|%-14s|\n",productID,name,price,quantity,status);
            System.out.println("+-----+------------------------------+---------------+----------+--------------+");
        }
        changed = true;
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
    public void printAllProducts() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            Collections.sort(this);
            System.out.println("+-----+------------------------------+---------------+----------+--------------+");
            System.out.format("|%-5s|%-30s|%-15s|%-10s|%-14s|\n","ID","Name","Price","Quantity","Status");
            System.out.println("+-----+------------------------------+---------------+----------+--------------+");
            for (Product product : this) {
                System.out.format("|%-5s|%-30s|%-15.2f|%-10d|%-14s|\n",product.productID,product.name,product.price,product.quantity,product.status);
                System.out.println("+-----+------------------------------+---------------+----------+--------------+");
            }
            
        }
    }

    public void printAllProductInFile() throws ParseException {
        List<String> lines = MyTool.readLinesFromFile(datafile);
        ArrayList<Product> list = new ArrayList<>();
        for (String s : lines) {
            Product p = new Product(s);
            list.add(p);
        }
        if(list.isEmpty()){
            System.out.println("Empty list!");
        }
        else{
            Collections.sort(list);
            System.out.println("+-----+------------------------------+---------------+----------+--------------+");
            System.out.format("|%-5s|%-30s|%-15s|%-10s|%-14s|\n","ID","Name","Price","Quantity","Status");
            System.out.println("+-----+------------------------------+---------------+----------+--------------+");
            for (Product product : list) {
                System.out.format("|%-5s|%-30s|%-15.2f|%-10d|%-14s|\n",product.productID,product.name,product.price,product.quantity,product.status);
                System.out.println("+-----+------------------------------+---------------+----------+--------------+");
            }
            
        }
    }
}
