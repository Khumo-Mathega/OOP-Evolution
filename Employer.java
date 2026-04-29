/**
@author Khumo Mathega
 */
public class Employer extends Staff{
    private String field;
    public Employer(){
        super();
        this.field = "";
    }
    public Employer(String name, double salary, String field){
        super(name, salary);
        setField(field);
    }
    public void setField(String field){
        this.field = field;
    }
    public String getField(){
        return field;
    }
    public double Tax(double salary){
        return (salary * 0.20);
    }
    @Override
    public String getDetails(){
        return super.getDetails() + "\nField: " + getField() + "\nNet Income : " +
        (getSalary() - Tax(getSalary()) );
    }
}