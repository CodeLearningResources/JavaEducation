package Inheritance;

import java.awt.*;

/**
 * Created by mikim on 2016-07-26.
 */
public class Circle extends Shape{
    private double radius;

    public Circle(Point center,double radius) {
        super(center);
        this.radius = radius;
    }

    public Point getCenter() {
        return  new Point(super.point);
    }

    public double getRadius() {
        return this.radius;
    }
}
