package lists;

import data.Config;
import data.Product;
import entities.Misc;
import tools.MyTool;
import java.util.ArrayList;
import java.util.List;

public class MiscList extends ArrayList<Misc> {
    private String miscFile;
    private final String miscHrBreak = String.format(Misc.FORMAT_STRING, "", "", "", "", "")
            .replace("|", "+").replace(" ", "-");
    private List<String> miscNames;
    private boolean changed;

    public MiscList() {
        super();
        initialize();
    }

    private final void initialize() {
        Config config = new Config();
        miscFile = config.getMiscFile();
        miscNames = MyTool.readLinesFromFile(config.getMiscNamesFile());
        importFromFile();
    }

    private ArrayList<Misc> searchID(String productID) {
        ArrayList<Misc> list = new ArrayList<>();
        for (Misc misc : this) {
            if (misc.getProductID().toUpperCase().contains(productID.toUpperCase()))
                list.add(misc);
        }
        return list;
    }

    private ArrayList<Misc> searchName(String name) {
        ArrayList<Misc> list = new ArrayList<>();
        for (Misc misc : this) {
            if (misc.getName().toUpperCase().contains(name.toUpperCase()))
                list.add(misc);
        }
        return list;
    }

    public Misc addMisc() {
        String productID = MyTool.readPattern("New product's ID: ", Misc.ID_FORMAT);
        while (!searchID(productID).isEmpty()) {
            System.out.println("ID is duplicated!");
            productID = MyTool.readPattern("New product's ID: ", Misc.ID_FORMAT);
        }
        String name = MyTool.readName("New product's name: ", miscNames);
        double price = MyTool.readRangeDouble("New product's price: ", 0, 10000);
        int quantity = MyTool.readRangeInt("New product's quantity: ", 0, 1000);
        boolean status = MyTool.readBool("Is the product available?");
        Misc misc = new Misc(productID, name, price, quantity, status);
        this.add(misc);
        changed = true;
        printMisc(misc);
        return misc;
    }

    public void searchByID(String productID) {
        ArrayList<Misc> list = searchID(productID);
        if (list.isEmpty())
            System.out.println("No miscellaneous products found!");
        else
            printMiscList(list);
    }

    public void searchByName(String name) {
        ArrayList<Misc> list = searchName(name);
        if (list.isEmpty())
            System.out.println("No miscellaneous products found!");
        else
            printMiscList(list);
    }

    public void updateMisc(String productID) {
        ArrayList<Misc> list = searchID(productID);
        if (list.isEmpty()) {
            System.out.println("No miscellaneous products match the given ID");
            return;
        }

        Misc misc = list.get(0);
        String tmp = MyTool.readName("New name (leave blank to skip): ", miscNames);
        if (!tmp.isBlank())
            misc.setName(tmp);

        tmp = MyTool.readPattern("New price (leave blank to skip): ",
                MyTool.regexBlank + "|" + MyTool.regexDecimal + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            misc.setPrice(Double.parseDouble(tmp));

        tmp = MyTool.readPattern("New quantity (leave blank to skip): ", MyTool.regexBlank + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            misc.setQuantity(Integer.parseInt(tmp));

        tmp = MyTool.readPattern("New status (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            misc.setStatus(MyTool.parseBool(tmp));

        System.out.println("Miscellaneous product updated.");
        printMisc(misc);
        changed = true;
    }

    public void deleteMisc (String productID) {
        ArrayList<Misc> list = searchID(productID);
        if (list.isEmpty()) {
            System.out.println("No miscellaneous products match the given ID!");
            return;
        }

        Misc misc = list.get(0);
        remove(misc);
        System.out.println("The following miscellaneous product has been deleted.");
        printMisc(misc);
        changed = true;
    }

    public void printMisc(Misc misc) {
        System.out.print(miscHrBreak);
        System.out.printf(Misc.FORMAT_STRING, "ID", "Name", "Price", "Quantity", "Status");
        System.out.print(miscHrBreak);
        System.out.print(misc.toMiscString());
        System.out.print(miscHrBreak);
    }

    public void printMiscList(ArrayList<Misc> list) {
        if (list.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        System.out.print(miscHrBreak);
        System.out.printf(Misc.FORMAT_STRING, "ID", "Name", "Price", "Quantity", "Status");
        System.out.print(miscHrBreak);
        for (Misc misc : list)
            System.out.print(misc.toMiscString());
        System.out.print(miscHrBreak);
    }

    public void importFromFile() {
        List<String> list = MyTool.readLinesFromFile(miscFile);
        for (String line : list) {
            String[] parts = line.split("" + Product.SEPARATOR);
            String productID = parts[0];
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            int quantity = Integer.parseInt(parts[3]);
            boolean status = MyTool.parseBool(parts[4]);
            this.add(new Misc(productID, name, price, quantity, status));
        }
    }

    public void exportToFile() {
        if (changed) {
            MyTool.writeFile(miscFile, this);
            changed = false;
            System.out.println("Miscellaneous products list is successfully saved.");
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}