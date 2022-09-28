/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.util.Scanner;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Console;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Win11
 */
public class MyTools
{
    public static final Scanner sc = new Scanner(System.in);
    public static final Console console = System.console();
    
    
    
    public static boolean parseBool(String boolStr)
    {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == 'T' || c == 'Y' || c == '1');
    }
    
    public static double parseDouble(String doubleStr) throws ParseException {
        String s = doubleStr.trim();
        return Double.parseDouble(s);
    }
    public static int parseInt(String intStr) throws ParseException {
        String s = intStr.trim();
        return Integer.parseInt(s);
    }

    public static String hashWithSHA256(String str)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    public static String readNonBlank(String message)
    {
        String input = "";
        do
        {
            System.out.print(message);
            input = sc.nextLine().trim();
        }
        while (input.isEmpty());
        return input;
    }
    
    public static String readPattern(String message, String pattern)
    {
        String input = "";
        boolean valid;
        do
        {
            System.out.print(message);
            input = sc.nextLine().trim();
            valid = input.matches(pattern);
        }
        while (!valid);
        return input;
    }
    
    
    public static boolean readBool(String message)
    {
        String input = "";
        do
        {
            System.out.print(message + "[Y/N-T/F-1/0]: ");
            input = sc.nextLine().trim();
        }
        while (input.isEmpty());
        return MyTools.parseBool(input);
    }
    
    public static List<String> readLinesFromFile(String filename)
    {
        try
        {
            File file = new File(filename);
            if (!file.exists())
                return null;
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            List<String> list = new ArrayList<>();
            String tmp = br.readLine();
            while (tmp != null && !tmp.isBlank())
            {
                list.add(tmp.trim());
                tmp = br.readLine();
            }
            br.close();
            fr.close();
            return list;
        }
        catch (IOException e)
        {
            System.out.println(e);
            return null;
        }
    }
    public static int readRangeInt(String message, int min, int max) {
        int input = 0;
        do {
            System.out.print(message + ": ");
            Scanner SC = new Scanner(System.in);
            input = SC.nextInt();
            if(input <= min || input >= max){
                System.out.println("Invalid input! Please, try again.");
            }
        } while (input <= min || input >= max);
        return input;
    }
    public static double readRangeDouble(String message, double min, double max) {
        double input = 0;
        do {
            System.out.print(message + ": ");
            Scanner SC = new Scanner(System.in);
            input = SC.nextDouble();
            if(input <= min || input >= max){
                System.out.println("Invalid input! Please, try again.");
            }
        } while (input <= min || input >= max);
        return input;
    }
    public static String readStatus(String message) {
        String input;
        boolean valid;
        do {
            System.out.print(message + ": ");
            Scanner SC = new Scanner(System.in);
            input = SC.nextLine().trim();
            valid = (input.equalsIgnoreCase("available") || input.equalsIgnoreCase("not available"));
            if(!valid){
                System.out.println("Invalid input! Please, try again.");
            }
        } while (!valid);
        return input;
    }
    public static void writeToFile(String filename, List list)
    {
        try
        {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            for (Object o: list)
                pw.println(o.toString());
            pw.close();
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
