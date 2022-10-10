package mng;

import java.util.ArrayList;
import tools.MyTool;

public class Menu extends ArrayList<String> {
    public Menu() {
        super();
    }

    public int getChoice(String title, String cancelMessage) {
        System.out.println(title);
        for (int i = 0; i < this.size(); i++) {
            System.out.println("    " + (i + 1) + " - " + this.get(i));
        }
        System.out.println(cancelMessage);
        try {
            return Integer.parseInt(MyTool.readNonBlank("Choose [1.." + this.size() + "]: "));
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public int getChoice(String title) {
        return getChoice(title, "Press other to cancel.");
    }
}
