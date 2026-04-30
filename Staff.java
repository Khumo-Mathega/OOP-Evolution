/**
@author Khumo Mathega
*/
import java.util.*;
import java.io.*;

// This will be my super class Staff
public abstract class Staff implements Taxable, Comparable<Staff>, Serializable{
    private String name;
    private double salary;
    public abstract double Tax(double salary);
    public Staff(){
        this("", 0);
    }
    public Staff(String name, double salary){
        setName(name);
        setSalary(salary);
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public String getName(){
        return name;
    }
    public double getSalary(){
        return salary;
    }
    public int compareTo(Staff other){
        return (this.name).compareTo(other.name);
    }
    public String getDetails(){
        return "\nName: " + getName() + "\nSalary: %.2f" + getSalary();
    }
    public String toString(){
        return getDetails();
    }
}