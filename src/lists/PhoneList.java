package lists;

import data.Config;
import data.Product;
import entities.Phone;
import tools.MyTool;
import java.util.ArrayList;
import java.util.List;

public class PhoneList extends ArrayList<Phone> {
    private String phoneFile;
    private final String phoneHrBreak = String.format(Phone.FORMAT_STRING, "", "", "", "", "", "", "", "")
            .replace("|", "+").replace(" ", "-");
    private List<String> phoneNames;
    private boolean changed;

    public PhoneList() {
        super();
        initialize();
    }

    private final void initialize() {
        Config config = new Config();
        phoneFile = config.getPhoneFile();
        phoneNames = MyTool.readLinesFromFile(config.getPhoneNamesFile());
        importFromFile();
    }

    private ArrayList<Phone> searchID(String productID) {
        ArrayList<Phone> list = new ArrayList<>();
        for (Phone phone : this) {
            if (phone.getProductID().toUpperCase().contains(productID.toUpperCase()))
                list.add(phone);
        }
        return list;
    }

    private ArrayList<Phone> searchName(String name) {
        ArrayList<Phone> list = new ArrayList<>();
        for (Phone phone : this) {
            if (phone.getName().toUpperCase().contains(name.toUpperCase()))
                list.add(phone);
        }
        return list;
    }

    public Phone addPhone() {
        String productID = MyTool.readPattern("New Phone's ID: ", Phone.ID_FORMAT);
        while (!searchID(productID).isEmpty()) {
            System.out.println("ID is duplicated!");
            productID = MyTool.readPattern("New Phone's ID: ", Phone.ID_FORMAT);
        }
        String name = MyTool.readName("New phone's name: ", phoneNames);
        String os = MyTool.readNonBlank("New phone's OS: ");
        int storage = MyTool.readRangeInt("New phone's storage: ", 0, 2048);
        int ramSize = MyTool.readRangeInt("New phone's RAM size: ", 0, 8);
        double price = MyTool.readRangeDouble("New phone's price: ", 0, 10000);
        int quantity = MyTool.readRangeInt("New phone's quantity: ", 0, 1000);
        boolean status = MyTool.readBool("Is the phone available?");
        Phone Phone = new Phone(productID, name, price, quantity, status, os, storage, ramSize);
        this.add(Phone);
        changed = true;
        printPhone(Phone);
        return Phone;
    }

    public void searchByID(String productID) {
        ArrayList<Phone> list = searchID(productID);
        if (list.isEmpty())
            System.out.println("No phones found!");
        else
            printPhoneList(list);
    }

    public void searchByName(String name) {
        ArrayList<Phone> list = searchName(name);
        if (list.isEmpty())
            System.out.println("No phones found!");
        else
            printPhoneList(list);
    }

    public void updatePhone(String productID) {
        ArrayList<Phone> list = searchID(productID);
        if (list.isEmpty()) {
            System.out.println("No phones match the given ID!");
            return;
        }
        
        Phone phone = list.get(0);
        String tmp = MyTool.readName("New name (leave blank to skip): ", phoneNames);
        if (!tmp.isBlank())
            phone.setName(tmp);

        tmp = MyTool.readPattern("New price (leave blank to skip): ",
                MyTool.regexBlank + "|" + MyTool.regexDecimal + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            phone.setPrice(Double.parseDouble(tmp));

        tmp = MyTool.readPattern("New quantity (leave blank to skip): ", MyTool.regexBlank + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            phone.setQuantity(Integer.parseInt(tmp));

        tmp = MyTool.readPattern("New status (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            phone.setStatus(MyTool.parseBool(tmp));

        tmp = MyTool.readPattern("New OS (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            phone.setOs(tmp);

        tmp = MyTool.readPattern("New storage (leave blank to skip): ", MyTool.regexBlank + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            phone.setStorage(Integer.parseInt(tmp));

        tmp = MyTool.readPattern("New RAM size (leave blank to skip): ", MyTool.regexBlank + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            phone.setRamSize(Integer.parseInt(tmp));

        System.out.println("Phone updated");
        printPhone(phone);
        changed = true;
    }

    public void deletePhone (String productID) {
        ArrayList<Phone> list = searchID(productID);
        if (list.isEmpty()) {
            System.out.println("No phones match the given ID!");
            return;
        }

        Phone phone = list.get(0);
        remove(phone);
        System.out.println("The following phone has been deleted.");
        printPhone(phone);
        changed = true;
    }

    public void printPhone(Phone phone) {
        System.out.print(phoneHrBreak);
        System.out.printf(Phone.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.print(phoneHrBreak);
        System.out.print(phone.toPhoneString());
        System.out.print(phoneHrBreak);
    }

    public void printPhoneList(ArrayList<Phone> list) {
        if (list.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }

        System.out.print(phoneHrBreak);
        System.out.printf(Phone.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.print(phoneHrBreak);
        for (Phone phone : list)
            System.out.print(phone.toPhoneString());
        System.out.print(phoneHrBreak);
    }

    public void importFromFile() {
        List<String> list = MyTool.readLinesFromFile(phoneFile);
        for (String line : list) {
            String[] parts = line.split("" + Product.SEPARATOR);
            String productID = parts[0];
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            int quantity = Integer.parseInt(parts[3]);
            boolean status = MyTool.parseBool(parts[4]);
            String os = parts[5];
            int storage = Integer.parseInt(parts[6]);
            int ramSize = Integer.parseInt(parts[7]);
            this.add(new Phone(productID, name, price, quantity, status, os, storage, ramSize));
        }
    }

    public void exportToFile() {
        if (changed) {
            MyTool.writeFile(phoneFile, this);
            changed = false;
            System.out.println("Phone list is successfully saved.");
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}