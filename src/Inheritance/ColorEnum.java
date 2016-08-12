package Inheritance;

/**
 * Created by mikim on 2016-08-03.
 */
public enum  ColorEnum {
    // RGB system of colors. (Red, Green, Blue)
    BLACK(0,0,0), RED(255,0,0), GREEN(0,255,0), BLUE(0,0,255),  CYAN(0,255,255), MAGENTA(255,0,255),
    YELLOW(255,255,0), WHITE(255,255,255);

    private int red, green, blue;

    ColorEnum(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public static void main(String[] args) {
        System.out.println(BLACK.getBlue());
    }
}


