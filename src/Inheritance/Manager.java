package Inheritance;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by mikim on 2016-07-25.
 */
public class Manager extends Employee{
    private double bonus;

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Manager(String name, int salary) {
        super(name, salary);
        this.bonus = 0;
    }

    public static void main(String[] args) {
        Object objects = new Object();
        for (Field f : objects.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            Object value = null;
            try {
                value = f.get(objects);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(f.getName() + ":" + value);
        }


//        Class<?> c1 = objects.getClass();
//        String classname = "java.util.Scanner";
//        Class<?> cl = null;
//        try {
//            cl = Class.forName(classname);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        while (cl != null) {
//            for (Method m : cl.getDeclaredMethods()) {
//                System.out.println(
//                        Modifier.toString(m.getModifiers()) + " " +
//                        m.getReturnType().getCanonicalName() + " " +
//                        m.getName() +
//                        Arrays.toString(m.getParameters()));
//            }
//            cl = cl.getSuperclass();
//        }

    }
}
