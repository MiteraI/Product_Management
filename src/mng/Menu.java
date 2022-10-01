/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;
import java.util.ArrayList;
import tools.MyTool;
/**
 *
 * @author Administrator
 */
public class Menu extends ArrayList<String>{
    public Menu() {
        super();
    }

    public Menu(String[] items) {
        super();
        for (String item : items) {
            this.add(item);
        }
    }

    public int getChoice(String title) {
        System.out.println(title);
        for (int i = 0; i < this.size(); i++) {
            System.out.println("    " +(i + 1) + " - " + this.get(i));
        }
        System.out.println("Press other to quit.");
        return Integer.parseInt(MyTool.readNonBlank("Choose [1.." + this.size() + "]: ")); //nên trả về string, lỗi nếu nhập char
    }
}
