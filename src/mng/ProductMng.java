package mng;

import lists.*;
import entities.*;
import tools.MyTool;

public class ProductMng {
    public static void main(String[] args) {
        Menu mainMenu = new Menu();
        mainMenu.add("Create a new product");
        mainMenu.add("Search product(s)");
        mainMenu.add("Update a product's information");
        mainMenu.add("Delete a product");
        mainMenu.add("Print all products");
        mainMenu.add("Print list of laptops");
        mainMenu.add("Print list of phones");
        mainMenu.add("Print list of workstations");
        mainMenu.add("Print list of miscellaneous products");
        mainMenu.add("Save changes");
        int mainChoice;

        Menu entitiesMenu = new Menu();
        entitiesMenu.add("Laptop");
        entitiesMenu.add("Phone");
        entitiesMenu.add("Workstation");
        entitiesMenu.add("Misc");
        int entityChoice;

        Menu attrMenu = new Menu();
        attrMenu.add("ID");
        attrMenu.add("Name");
        int attrChoice;

        LaptopList laptopList = new LaptopList();
        PhoneList phoneList = new PhoneList();
        WorkStationList wsList = new WorkStationList();
        MiscList miscList = new MiscList();

        do {
            mainChoice = mainMenu.getChoice("----------Product Manager----------", "Press other to quit.");
            switch (mainChoice) {
                case 1 -> {
                    entityChoice = entitiesMenu.getChoice("Create a:");
                    switch (entityChoice) {
                        case 1 -> laptopList.addLaptop();
                        case 2 -> phoneList.addPhone();
                        case 3 -> wsList.addWorkStation();
                        case 4 -> miscList.addMisc();
                    }
                }
                case 2 -> {
                    attrChoice = attrMenu.getChoice("Search by:");
                    switch (attrChoice) {
                        case 1 -> {
                            String productID = MyTool.readNonBlank("Enter product ID: ");
                            laptopList.searchByID(productID);
                            phoneList.searchByID(productID);
                            wsList.searchByID(productID);
                            miscList.searchByID(productID);
                        }
                        case 2 -> {
                            String name = MyTool.readNonBlank("Enter product name: ");
                            laptopList.searchByName(name);
                            phoneList.searchByName(name);
                            wsList.searchByName(name);
                            miscList.searchByName(name);
                        }
                    }
                }
                case 3 -> {
                    String productID = MyTool.readPattern("ID of product to be updated (leave blank to cancel): ",
                            Laptop.ID_FORMAT + "|" + Phone.ID_FORMAT + "|" + WorkStation.ID_FORMAT + "|"
                                    + Misc.ID_FORMAT + "|" + MyTool.regexBlank);
                    if (productID.matches(Laptop.ID_FORMAT))
                        laptopList.updateLaptop(productID);
                    else if (productID.matches(Phone.ID_FORMAT))
                        phoneList.updatePhone(productID);
                    else if (productID.matches(WorkStation.ID_FORMAT))
                        wsList.updateWorkStation(productID);
                    else if (productID.matches(Misc.ID_FORMAT))
                        miscList.updateMisc(productID);
                }
                case 4 -> {
                    String productID = MyTool.readPattern("ID of product to be deleted (leave blank to cancel): ",
                            Laptop.ID_FORMAT + "|" + Phone.ID_FORMAT + "|" + WorkStation.ID_FORMAT + "|"
                                    + Misc.ID_FORMAT + "|" + MyTool.regexBlank);
                    if (productID.matches(Laptop.ID_FORMAT))
                        laptopList.deleteLaptop(productID);
                    else if (productID.matches(Phone.ID_FORMAT))
                        phoneList.deletePhone(productID);
                    else if (productID.matches(WorkStation.ID_FORMAT))
                        wsList.deleteWorkStation(productID);
                    else if (productID.matches(Misc.ID_FORMAT))
                        miscList.deleteMisc(productID);
                }
                case 5 -> {
                    if (laptopList.isEmpty() && phoneList.isEmpty() && wsList.isEmpty() && miscList.isEmpty()) {
                        System.out.println("Product list is empty!");
                    }
                    if (!laptopList.isEmpty()) {
                        System.out.println("Laptop list:");
                        laptopList.printLaptopList(laptopList);
                    }
                    if (!phoneList.isEmpty()) {
                        System.out.println("Phone list:");
                        phoneList.printPhoneList(phoneList);
                    }
                    if (!wsList.isEmpty()) {
                        System.out.println("Workstation list:");
                        wsList.printWorkStationList(wsList);
                    }
                    if (!miscList.isEmpty()) {
                        System.out.println("Miscellaneous products:");
                        miscList.printMiscList(miscList);
                    }
                }
                case 6 -> laptopList.printLaptopList(laptopList);
                case 7 -> phoneList.printPhoneList(phoneList);
                case 8 -> wsList.printWorkStationList(wsList);
                case 9 -> miscList.printMiscList(miscList);
                case 10 -> {
                    laptopList.exportToFile();
                    phoneList.exportToFile();
                    wsList.exportToFile();
                    miscList.exportToFile();
                }
                default -> {
                    if (laptopList.isChanged() || phoneList.isChanged() || wsList.isChanged() || miscList.isChanged()) {
                        boolean res = MyTool.readBool("Data changed. Save changes? ");
                        if (res == true) {
                            laptopList.exportToFile();
                            phoneList.exportToFile();
                            wsList.exportToFile();
                            miscList.exportToFile();
                        }
                    }
                }
            }
        } while (mainChoice > 0 && mainChoice <= mainMenu.size());
        System.out.println("Good Bye!");
    }
}
