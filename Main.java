/**
@author Khumo Mathega
 */
import java.util.*;
import java.io.*;

public class Main{
    private static int MAX = 100;
    public static void main(String[] args){
        // reading from text file first
        int count = 0;
        Staff[] array = new Staff[MAX];
        try{
            File file = new File("staff.txt");
            Scanner input = new Scanner(file);
            while(input.hasNextLine() && count < MAX){
                try{
                    String line = input.nextLine();
                    String[] parts = line.split(",");
                    // format string, string, double, (bool, string)
                    String id = parts[0].trim(); // A or B
                    String n = parts[1].trim();
                    double s = Double.parseDouble(parts[2].trim());
                    String x = parts[3].trim();
                    // leave part[3] as a string , only change if its employee
                    if (id == "A"){
                        array[count] = new Employer(n, s, x);
                    }else if (id == "B"){
                        boolean b = Boolean.parseBoolean(x);
                        array[count] = new Employee(n, s, b);
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
        // Serialization
        try{
            FileOutputStream file = new FileOutputStream("data.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            for (int i = 0; i < count; i++){
                out.writeObject(array[i]);
            }
            out.close();
        }catch (Exception e){
            System.out.println("File Error");
            System.exit(-1);
        }
        // Deserialization
        int num = 0;
        Staff[] arr = new Staff[MAX];
        try{
            FileInputStream file = new FileInputStream("data.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            while(true && num < MAX){
                try{
                    arr[num] = (Staff) in.readObject();
                    num ++;
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
        // sort then print
        Arrays.sort(arr, 0, num);
        System.out.println("======== ======== ===========");
        for(int i = 0; i < num; i++){
            System.out.println(arr[i]);
        }
        System.out.println("======== ======== ===========");
    }
}