package Inheritance;

import java.awt.*;

/**
 * Created by mikim on 2016-07-26.
 */
public class Line extends Shape {
    private Point to;

    public Line(Point from, Point to) {
        super(from);
        this.to = to;
    }

    public Point getCenter() {
        int x = (int) ( (to.getX() - super.point.getX()) / 2 + super.point.getX() );
        int y = (int) ( (to.getY() - super.point.getY()) / 2 + super.point.getY() );
        return new Point(x,y);
    }
}
