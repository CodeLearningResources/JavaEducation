package Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by mikim on 2016-08-19.
 */
public class StreamMain {

    public enum ReduceType {ACCUMULATOR, IDENTITY_ACCUMULATOR, COMBINER}

    public static Stream<Long> infiniteRandomNumbers(int seed, int a, int c, int m) {
        /* Ch 8.4 Making an infinite stream of random number without using Random numbers
        * x_0 = seed. x_n+1 = (a * x_n + c) % m
        * */
        return IntStream.iterate(seed, n -> ((a * n + c) % m)).asLongStream().boxed();

    }

    public static <T> ArrayList<T> joinAllElements(Stream<ArrayList<T>> input, ReduceType accumulator) {
        /*
        Ch 8.13
        Join all elements in a input Stream<ArrayList<T>> to one ArrayList<T> with accumulator.
         */

        if (accumulator == ReduceType.ACCUMULATOR) {
            return input.reduce((x, y) -> {
                ArrayList<T> newList = new ArrayList<>();
                newList.addAll(x);
                newList.addAll(y);
                return newList;
            }).orElse(new ArrayList<T>());

        } else if (accumulator == ReduceType.IDENTITY_ACCUMULATOR) {
            return input.reduce(new ArrayList<>(),
                    (x, y) -> {
                        x.addAll(y);
                        return x;
                    }
            );

        } else if (accumulator == ReduceType.COMBINER) {
            return input.reduce(new ArrayList<T>(),
                    (x, y) -> {
                        x.addAll(y);
                        return x;
                    },
                    (combinerList1, combinerList2) -> {
                        combinerList1.addAll(combinerList2);
                        return combinerList1;
                    }
            );
        } else {
            return new ArrayList<>();
        }
    }


    public static void main(String[] args) {
        List<ArrayList<Integer>> list = Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 2)),
                new ArrayList<>(Arrays.asList(3, 4, 5)),
                new ArrayList<>(Arrays.asList(6, 7)),
                new ArrayList<>(Arrays.asList(8, 9, 10, 11)));

        Stream<ArrayList<Integer>> stream = list.stream();
        Stream<ArrayList<Integer>> stream1 = list.stream();
        Stream<ArrayList<Integer>> stream2 = list.stream();
        Stream<ArrayList<Integer>> stream3 = list.stream();

        System.out.println("Total count: " + stream.count());
        System.out.println("Accumulator: " + joinAllElements(stream1, ReduceType.ACCUMULATOR));
        System.out.println("Identity Accumulator: " + joinAllElements(stream2, ReduceType.IDENTITY_ACCUMULATOR));
        System.out.println("Combiner: " + joinAllElements(stream3, ReduceType.COMBINER));

        int seed = 1, a = 100, c = 13, m = (int) Math.pow(2,48);
        System.out.printf("Infinite Random numbers with seed = %d, a = %d, c = %d, m= %d", seed, a, c, m);
        infiniteRandomNumbers(seed, a, c, m).limit(10).forEach(System.out::println);
    }
}
