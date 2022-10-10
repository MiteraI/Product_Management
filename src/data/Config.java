/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;
import tools.MyTool;

/**
 *
 * @author Administrator
 */
public class Config {
    private static final String CONFIG_FILE = "Product/Config.txt";
    private String laptopFile;
    private String phoneFile;
    private String wsFile;
    private String miscFile;
    private String laptopNamesFile;
    private String phoneNamesFile;
    private String wsNamesFile;
    private String miscNamesFile;

    public Config() {
        readData();
    }

    private final void readData() {
        List<String> lines = MyTool.readLinesFromFile(CONFIG_FILE);
        for (String line : lines) {
            line = line.toUpperCase();
            String[] parts = line.split(":");
            if (line.contains("NAME")) {
                if (line.contains("LAPTOP")) {
                    laptopNamesFile = "Product/" + parts[1].trim();
                } else if (line.contains("PHONE")) {
                    phoneNamesFile = "Product/" + parts[1].trim();
                } else if (line.contains("WORKSTATION")) {
                    wsNamesFile = "Product/" + parts[1].trim();
                } else if (line.contains("MISC")) {
                    miscNamesFile = "Product/" + parts[1].trim();
                }
            } else {
                if (line.contains("LAPTOP")) {
                    laptopFile = "Product/" + parts[1].trim();
                } else if (line.contains("PHONE")) {
                    phoneFile = "Product/" + parts[1].trim();
                } else if (line.contains("WORKSTATION")) {
                    wsFile = "Product/" + parts[1].trim();
                } else if (line.contains("MISC")) {
                    miscFile = "Product/" + parts[1].trim();
                }
            }
        }
    }

    public String getLaptopFile() {
        return this.laptopFile;
    }

    public String getPhoneFile() {
        return this.phoneFile;
    }

    public String getWsFile() {
        return this.wsFile;
    }

    public String getMiscFile() {
        return this.miscFile;
    }

    public String getLaptopNamesFile() {
        return this.laptopNamesFile;
    }

    public String getPhoneNamesFile() {
        return this.phoneNamesFile;
    }

    public String getWsNamesFile() {
        return this.wsNamesFile;
    }

    public String getMiscNamesFile() {
        return this.miscNamesFile;
    }
}
