package Inheritance;

/**
 * Created by mikim on 2016-08-05.
 */
public enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private String abbrev;

    Size(String abbrev) {
        this.abbrev = abbrev;
    }

    public String getAbbrev() { return  abbrev;}

    public static void main(String[] args) {
        System.out.println(LARGE.getAbbrev());
    }
}
