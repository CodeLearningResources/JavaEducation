/**
 * Created by mikim on 2016-07-20.
 */
public class MutatorPoint {
    private double x, y;

    public MutatorPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double doThings() {
        return 0.1;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public MutatorPoint translate(double x, double y) {
        this.setX(x+this.getX());
        this.setY(y+this.getY());
        return this;
    }

    public MutatorPoint scale(double scaleRatio) {
        this.setX(scaleRatio * this.getX());
        this.setY(scaleRatio * this.getY());
        return this;
    }
}
