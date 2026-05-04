/**
@author Khumo Mathega
 */
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){
        // reading from text file first
        int count = 0;
        ArrayList<Staff> array = new ArrayList<Staff>();
        try{
            File file = new File("staff.txt");
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                try{
                    String line = input.nextLine();
                    String[] parts = line.split(",");
                    // format string, string, double, (bool, string)
                    String id = parts[0].trim(); // A or B
                    String n = parts[1].trim();
                    double s = Double.parseDouble(parts[2].trim());
                    String x = parts[3].trim();
                    // leave part[3] as a string , only change if its employee
                    if (id.equals("A")){
                        array.add(new Employer(n, s, x));
                    }else if (id.equals("B")){
                        boolean b = Boolean.parseBoolean(x);
                        array.add(new Employee(n, s, b));
                    }
                }
                catch(Exception e){
                    System.out.println("Skipping bad line...");
                }
                count++;
            }
            input.close();
        }catch(FileNotFoundException e){
            System.out.print("Error: " + e.getMessage());
            System.exit(-1);
        }
        System.out.println("Objects added(From txt file to ArrayList): " + count);
        for (int i = 0; i < count; i ++){
            System.out.println(array.get(i));
        }

        // Serialization
        try{
            FileOutputStream file = new FileOutputStream("data.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            for (int i = 0; i < count; i++){
                out.writeObject(array.get(i));
            }
            out.close();
        }catch (Exception e){
            System.out.println("File Error");
            System.exit(-1);
        }
        // Deserialization
        int count2 = 0;
        ArrayList<Staff> arr = new ArrayList<Staff>();
        try{
            FileInputStream file = new FileInputStream("data.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            while(true ){
                try{
                    arr.add((Staff) in.readObject());
                    count2 ++;
                }catch (EOFException e){
                    break;
                }
            }
            in.close();
        }
        catch(Exception e){
            System.out.println("File not found...");
            System.exit(-1);
        }
        System.out.println("Objects added to list(From ser file to ArrayList): " + count2);
        // sort then print
        Collections.sort(arr);
        System.out.println("Sorted Array");
        System.out.println("======== ======== ===========");
        for(int i = 0; i < count2; i++){
            System.out.println(arr.get(i));
        }
        System.out.println("======== ======== ===========");
    }
}