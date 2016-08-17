package Streams;

        import java.util.stream.Stream;

/**
 * Created by mikim on 2016-08-16.
 */
public class Main {



    public static void main(String[] args) {
        Stream<String> silence = Stream.empty();
        silence.count();
    }
}