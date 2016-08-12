package Inheritance;

import java.awt.*;

/**
 * Created by mikim on 2016-07-26.
 */
public class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(Point topLeft, double width, double height) {
        super(topLeft);
        this.width = width;
        this.height = height;
    }

    public Point getCenter() {
        int x = (int) (super.point.getX() + width/2);
        int y = (int) (super.point.getY() + height/2);
        return new Point(x, y);
    }
}
