package lists;

import data.Config;
import data.Product;
import entities.WorkStation;
import tools.MyTool;
import java.util.ArrayList;
import java.util.List;

public class WorkStationList extends ArrayList<WorkStation> {
    private String wsFile;
    private final String wsHrBreak = String.format(WorkStation.FORMAT_STRING, "", "", "", "", "", "", "", "")
            .replace("|", "+").replace(" ", "-");
    private List<String> wsNames;
    private boolean changed;

    public WorkStationList() {
        super();
        initialize();
    }

    private final void initialize() {
        Config config = new Config();
        wsFile = config.getWsFile();
        wsNames = MyTool.readLinesFromFile(config.getWsNamesFile());
        importFromFile();
    }

    private ArrayList<WorkStation> searchID(String productID) {
        ArrayList<WorkStation> list = new ArrayList<>();
        for (WorkStation ws : this) {
            if (ws.getProductID().toUpperCase().contains(productID.toUpperCase()))
                list.add(ws);
        }
        return list;
    }

    private ArrayList<WorkStation> searchName(String name) {
        ArrayList<WorkStation> list = new ArrayList<>();
        for (WorkStation ws : this) {
            if (ws.getName().toUpperCase().contains(name.toUpperCase()))
                list.add(ws);
        }
        return list;
    }

    public WorkStation addWorkStation() {
        String productID = MyTool.readPattern("New workstation's ID: ", WorkStation.ID_FORMAT);
        while (!searchID(productID).isEmpty()) {
            System.out.println("ID is duplicated!");
            productID = MyTool.readPattern("New workstation's ID: ", WorkStation.ID_FORMAT);
        }
        String name = MyTool.readName("New workstation's name: ", wsNames);
        String cpu = MyTool.readNonBlank("New workstation's CPU: ");
        String gpu = MyTool.readNonBlank("New workstation's GPU: ");
        int ramSize = MyTool.readRangeInt("New workstation's RAM size: ", 0, 16384);
        double price = MyTool.readRangeDouble("New workstation's price: ", 0, 10000);
        int quantity = MyTool.readRangeInt("New workstation's quantity: ", 0, 1000);
        boolean status = MyTool.readBool("Is the ws available?");
        WorkStation ws = new WorkStation(productID, name, price, quantity, status, cpu, gpu, ramSize);
        this.add(ws);
        changed = true;
        printWorkStation(ws);
        return ws;
    }

    public void searchByID(String productID) {
        ArrayList<WorkStation> list = searchID(productID);
        if (list.isEmpty())
            System.out.println("No workstations found!");
        else
            printWorkStationList(list);
    }

    public void searchByName(String name) {
        ArrayList<WorkStation> list = searchName(name);
        if (list.isEmpty())
            System.out.println("No workstations found!");
        else
            printWorkStationList(list);
    }

    public void updateWorkStation(String productID) {
        ArrayList<WorkStation> list = searchID(productID);
        if (list.isEmpty()) {
            System.out.println("No workstations match the given ID!");
            return;
        }

        WorkStation ws = list.get(0);
        String tmp = MyTool.readName("New name (leave blank to skip): ", wsNames);
        if (!tmp.isBlank())
            ws.setName(tmp);

        tmp = MyTool.readPattern("New price (leave blank to skip): ",
                MyTool.regexBlank + "|" + MyTool.regexDecimal + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            ws.setPrice(Double.parseDouble(tmp));

        tmp = MyTool.readPattern("New quantity (leave blank to skip): ", MyTool.regexBlank + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            ws.setQuantity(Integer.parseInt(tmp));

        tmp = MyTool.readPattern("New status (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            ws.setStatus(MyTool.parseBool(tmp));

        tmp = MyTool.readPattern("New CPU (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            ws.setCpu(tmp);

        tmp = MyTool.readPattern("New GPU (leave blank to skip): ", ".*");
        if (!tmp.isBlank())
            ws.setGpu(tmp);

        tmp = MyTool.readPattern("New RAM size (leave blank to skip): ", MyTool.regexBlank + "|" + MyTool.regexInteger);
        if (!tmp.isBlank())
            ws.setRamSize(Integer.parseInt(tmp));

        System.out.println("Workstation updated.");
        printWorkStation(ws);
        changed = true;
    }

    public void deleteWorkStation (String productID) {
        ArrayList<WorkStation> list = searchID(productID);
        if (list.isEmpty()) {
            System.out.println("No workstations match the given ID!");
            return;
        }

        WorkStation ws = list.get(0);
        remove(ws);
        System.out.println("The following workstation has been deleted.");
        printWorkStation(ws);
        changed = true;
    }

    public void printWorkStation(WorkStation ws) {
        System.out.print(wsHrBreak);
        System.out.printf(WorkStation.FORMAT_STRING, "ID", "Name", "CPU", "GPU", "RAM", "Price", "Quantity", "Status");
        System.out.print(wsHrBreak);
        System.out.print(ws.toWorkStationString());
        System.out.print(wsHrBreak);
    }

    public void printWorkStationList(ArrayList<WorkStation> list) {
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

    public void importFromFile() {
        List<String> list = MyTool.readLinesFromFile(wsFile);
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
            this.add(new WorkStation(productID, name, price, quantity, status, cpu, gpu, ramSize));
        }
    }

    public void exportToFile() {
        if (changed) {
            MyTool.writeFile(wsFile, this);
            changed = false;
            System.out.println("WorkStation list is successfully saved.");
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}