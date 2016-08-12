package Inheritance;

import java.awt.*;

/**
 * Created by mikim on 2016-07-26.
 */
public abstract class Shape {
    Point point;

    public Shape(Point point) {
        this.point = point;
    }

    public void moveBy(double dx, double dy) {
        point.setLocation(point.getX() + dx, point.getY() + dy);
    }

    public abstract Point getCenter();
}
