/**
@author Khumo Mathega
 */
import java.util.*;
import java.io.*;

public class Application{
    private ArrayList<Staff> array = new ArrayList<Staff>();  // any method can see it

    /////////////// function to read from textfile
    public static int ReadingFromTextFile(){
        int count = 0;
        Scanner input;
        try{
            File file = new File("staff.txt");
            input = new Scanner(file);
        }
        catch (IOException e){
            System.out.println("File Not Found Error...Exiting...");
            System.exit(-1);
        }
        while (input.hasNextLine()){
            try{
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
            catch (Exception e){
                System.out.println("Error: " + e.getMessage());
                System.exit(-1);
            }
        }
        input.close();
        System.out.println("Number of Reads from file: " + count);
        return count;
    }
////////////////////////////////////////////// end of function

///////////////////////////////////////////// Serialization method
    public static void Serialization(int count){
        ObjectOutputStream out;
        try{
            FileOutputStream file = new FileOutputStream("data.ser");
            out = new ObjectOutputStream(file);
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
            System.exit(-1);
        }
        for (int i = 0; i < count; i++){
            out.writeObject(array.get(i));
        }
        out.close();

    }
/////////////////////////////////////////

////////////////////////////////////////  Deserialize method
    public static ArrayList<String> Deserialization(){
        ArrayList<String> arrayTwo = new ArrayList<>();
        ObjectInputStream in;
        try{
            FileInputStream file = new FileInputStream("data.ser");
            in = new ObjectInputStream(file);
        }
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
            System.exit(-1);
        }
        int x = 0;
        while (true){
            try{
                arrayTwo.add((Staff) in.readObject());
                x++;
            }
            catch (EOFException e){
                System.out.println("");
                break;
            }
        }
        in.close();
        System.out.println("Objects Deserialized: " + x);
        return arrayTwo;
    }
///////////////////////////////////////
    public static void DisplayArray(ArrayList<String> a){
        System.out.println("======= ========== ========");
        for (int i = 0; i < a.length(); i++){
            System.out.println(a.get(i));
        }
        System.out.println("======= ========== ========");
    }
//////////////////////////////////////
    public static void main(String[] args){
        int count = ReadingFromTextFile();
        Serialization(count);
        ArrayList<String> arrayTwo = Deserialization();
        DisplayArray(arrayTwo);  // print
        Collections.sort(arrayTwo);  // sort
        System.out.println("Sorted Array");
        DisplayArray(arrayTwo);
    }
}