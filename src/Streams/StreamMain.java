package Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by mikim on 2016-08-19.
 */
public class StreamMain {

    public enum ReduceType {
        ACCUMULATOR {
            public <T> ArrayList<T> joinAllElements(Stream<ArrayList<T>> input) {
                return input.reduce((x, y) -> {
                    ArrayList<T> newList = new ArrayList<>();
                    newList.addAll(x);
                    newList.addAll(y);
                    return newList;
                }).orElse(new ArrayList<T>());
            }
        },
        IDENTITY_ACCUMULATOR {
            public <T> ArrayList<T> joinAllElements(Stream<ArrayList<T>> input) {
                return input.reduce(new ArrayList<>(),
                        (x, y) -> {
                            x.addAll(y);
                            return x;
                        }
                );
            }
        },
        COMBINER {
            public <T> ArrayList<T> joinAllElements(Stream<ArrayList<T>> input) {
                return input.reduce(new ArrayList<T>(),
                        (a, b) -> {a.addAll(b); return a;},
                        (x, y) -> {
                            x.addAll(y);
                            return x;
                        }
                );
            }
        };

        public abstract <T> ArrayList<T> joinAllElements(Stream<ArrayList<T>> input);
    }

    public static Stream<Long> infiniteRandomNumbers(int seed, int a, int c, int m) {
        /* Ch 8.4 Making an infinite stream of random number without using Random numbers
        * x_0 = seed. x_n+1 = (a * x_n + c) % m
        * */
        return LongStream.iterate(seed, n -> ((a * n + c) % m)).boxed();

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

        ReduceType accumulator = ReduceType.ACCUMULATOR;
        ReduceType identityAccumulator = ReduceType.IDENTITY_ACCUMULATOR;
        ReduceType combiner = ReduceType.COMBINER;

        System.out.println("Total count: " + stream.count());
        System.out.println("Accumulator: " + accumulator.joinAllElements(stream1));
        System.out.println("Identity Accumulator: " + identityAccumulator.joinAllElements(stream2));
        System.out.println("Combiner: " + combiner.joinAllElements(stream3));

        int seed = 1, a = 100, c = 13, m = (int) Math.pow(2,48);
        System.out.printf("Infinite Random numbers with seed = %d, a = %d, c = %d, m= %d", seed, a, c, m);
        infiniteRandomNumbers(seed, a, c, m).limit(10).forEach(System.out::println);
    }
}
