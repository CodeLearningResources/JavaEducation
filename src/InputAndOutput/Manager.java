package InputAndOutput;

/**
 * Created by mikim on 2016-08-29.
 */
public class Manager extends Employee {
    private double bonus;

    public Manager(String name, double salary) {
        super(name, salary);
        bonus = 0;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getSalary() { // Overrides superclass method
        return super.getSalary() + bonus;
    }

    public String toString() {
        return super.toString() + "[bonus=" + bonus + "]";
    }
}
