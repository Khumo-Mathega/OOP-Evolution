/**
@author Khumo Mathega
 */
import java.util.*;
import java.io.*;

public class Application{
    public static ArrayList<Staff> array = new ArrayList<Staff>();  // any method can see it
    public static ArrayList<Staff> newArray = new ArrayList<Staff>();

    //////////////////////////////////////
    public static void main(String[] args){
        ReadingFromTextFile();
        Serialization();
        Deserialization();
        DisplayArray();  // print
        Collections.sort(newArray);  // sort
        System.out.println("Sorted Array");
        DisplayArray();
    }

    /////////////// function to read from textfile
    public static void ReadingFromTextFile(){
        int count = 0;
        Scanner input = null;
        try{
            File file = new File("staff.txt");
            input = new Scanner(file);
            while (input.hasNextLine()){
                String line = input.nextLine();
                String[] parts = line.split(",");
                // format 0.string 1.string 2.double 3.string/bool
                String id = parts[0].trim(); // A or B
                String n = parts[1].trim();
                double s = Double.parseDouble(parts[2].trim());
                String x = parts[3].trim();
                if (id.equals("A")){
                    array.add(new Employer(n, s, x));
                }
                else if (id.equals("B")){
                    boolean b = Boolean.parseBoolean(x);
                    array.add(new Employee(n, s, b));
                }
                count++;      
            }
            input.close();
        }
        catch (IOException e){
            System.out.println("File Not Found Error...Exiting...");
            System.exit(-1);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Number of Reads from file: " + count);
    }
////////////////////////////////////////////// end of function

///////////////////////////////////////////// Serialization method
    public static void Serialization(){
        ObjectOutputStream out;
        try{
            FileOutputStream file = new FileOutputStream("data.ser");
            out = new ObjectOutputStream(file);
            for (int i = 0; i < array.size(); i++){
                out.writeObject(array.get(i));
            }
            out.close();            
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
            System.exit(-1);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
/////////////////////////////////////////

////////////////////////////////////////  Deserialize method
    public static void Deserialization(){
        ObjectInputStream in;
        int x = 0;
        try{
            FileInputStream file = new FileInputStream("data.ser");
            in = new ObjectInputStream(file);
            while (true){
                try{
                    newArray.add((Staff) in.readObject());
                    x++;
                }
                catch (EOFException e){
                    System.out.println("");
                    break;
                }
            }
            in.close();            
        }
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
            System.exit(-1);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Objects Deserialized: " + x);
    }
///////////////////////////////////////
    public static void DisplayArray(){
        System.out.println("======= ========== ========");
        for (int i = 0; i < newArray.size(); i++){
            System.out.println(newArray.get(i));
        }
        System.out.println("======= ========== ========");
    }
}