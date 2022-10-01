/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;
import data.ProductList;
import java.text.ParseException;
import tools.MyTool;
/**
 *
 * @author Administrator
 */
public class ProductMng {
    public static void main(String[] args) throws ParseException {
        String[] options = {"Create new product", "Check if product exists", 
                            "Search product's information", "Update product",
                            "Delete product", "Save product list to file",
                            "Print all products", "Print list of laptops",
                            "Print list of phones", "Print list of workstations"};
        Menu mnu = new Menu(options);
        ProductList pList = new ProductList();
        pList.initWithFile();
        int choice;
        do {
            choice = mnu.getChoice("--------Product Manager--------");
            switch (choice) {
                case 1 -> pList.addProduct();
                case 2 -> pList.checkExistProduct();
                case 3 -> pList.searchProduct();
                case 4 -> pList.updateProduct();
                case 5 -> pList.deleteProduct();
                case 6 -> pList.writeProductToFile();
                case 7 -> pList.printAllProducts();
                case 8 -> pList.printAllLaptops();
                case 9 -> pList.printAllPhones();
                case 10 -> pList.printAllWorkStations();
                default -> {
                    if (pList.isChanged()) {
                        boolean res = MyTool.readBool("Data changed. Write to file? ");
                        if (res == true) {
                            pList.writeProductToFile();
                        }
                    }
                }
            }
            // pList.printAllProductInFile();
        } while (choice > 0 && choice <= mnu.size());
        System.out.println("Good Bye!");
    }
}
