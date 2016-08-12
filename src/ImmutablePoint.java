/**
 * Created by mikim on 2016-07-20.
 */
public class ImmutablePoint {
    // An immutable class that describes a point in the plane
    private double x, y;

    public ImmutablePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ImmutablePoint translate(double translateX, double translateY) {
        // Return a new point with  x and y point added by translateX and translateY value
        return new ImmutablePoint(this.getX() + translateX, this.getY() + translateY);
    }

    public ImmutablePoint scale(double scaleRatio) {
        // Return a new point with x and y point scaled by scaleRatio
        return new ImmutablePoint(this.getX() * scaleRatio, this.getY() * scaleRatio);
    }
}
