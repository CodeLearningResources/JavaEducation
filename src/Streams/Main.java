package Streams;

        import java.util.ArrayList;
        import java.util.Comparator;
        import java.util.List;
        import java.util.stream.Stream;

/**
 * Created by mikim on 2016-08-16.
 */
public class Main {



    public static void main(String[] args) {
        Object[] powers = Stream.iterate(1.0, p -> p * 2)
        .peek(e -> System.out.println("Fetching: " + e))
        .limit(20).toArray();
    }

}