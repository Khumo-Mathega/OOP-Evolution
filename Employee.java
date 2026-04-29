/**
@author Khumo Mathega
 */
public class Employee extends Staff{
    private boolean permanent;
    public Employee(){
        super();
        this.permanent = false;
    }
    public Employee(String name, double salary, boolean permanent){
        super(name, salary);
        setPermanent(permanent);
    }
    public void setPermanent(boolean permanent){
        this.permanent = permanent;
    }
    public boolean getPermanent(){
        return permanent;
    }
    public double Tax(double salary){
        return (salary * 0.15);
    }
    @Override
    public String getDetails(){
        return super.getDetails() + "\nPermanent: " + getPermanent() + "\nNet Income : " +
        (getSalary() - Tax(getSalary())) ;
    }
}