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
        for (Product p : list)
            System.out.println(p.toFormatString());
        System.out.println(laptopHrBreak);
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
        for (Product p : list)
            System.out.println(p.toFormatString());
        System.out.println(phoneHrBreak);
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
        for (Product p : list)
            System.out.println(p.toFormatString());
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
            if (p instanceof Laptop)
            {

            }
        }
    }
    
    public int checkID(String productID){
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductID().equals(productID)) {
                return i;
            }
        }
        return -1;
    }

    public void addProduct(){
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
    }
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
