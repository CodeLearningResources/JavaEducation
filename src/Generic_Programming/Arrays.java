package Generic_Programming;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by mikim on 2016-08-01.
 */
public class  Arrays {
    public static <T extends Comparable<? super T>> T min(T[] array) {  // Ch 6.10 min method
        isValidArray(array);                        // Check whether array is valid
        int index = firstNonNullIndex(array);       // Check array has null in it
        if (index < 0) return null;

        T currentMin = array[index];
        int len = array.length;
        for (int i= index; i < len; i ++) {
            if (array[i] != null && array[i].compareTo(currentMin) < 0) {
                currentMin = array[i];
            }
        }
        return currentMin;
    }

    public static <T extends Comparable<? super T>> T max(T[] array) {  // Ch 6.10 max method
        isValidArray(array);                        // Check whether array is valid
        int index = firstNonNullIndex(array);       // Check array has null in it
        if (index < 0) return null;

        T currentMax = array[index];
        int len = array.length;
        for (int i = index; i < len; i ++) {
            if (array[i] != null && array[i].compareTo(currentMax) > 0) {
                currentMax = array[i];
            }
        }
        return currentMax;
    }

    private static  <T> void isValidArray(T[] array) throws NullPointerException, IllegalArgumentException {
        // Check whether array is null

        if (array == null) throw new NullPointerException("Cannot handle null array!");

        // Check array is empty
        if (array.length == 0) throw new IllegalArgumentException("Cannot handle zero-length array!");
    }

    private static <T> int firstNonNullIndex(T[] array) {
        // Return first index of non-null item in array. If all items are null, return -1
        int len = array.length;
        int firstNonNullIndex = -1;
        for (int i = 0; i < len; i++) {
            if (array[i] != null) {
                firstNonNullIndex = i;
                break;
            }
        }
        return firstNonNullIndex;
    }

    public static <T, R> ArrayList<R> map(ArrayList<T> list, Function<T, R> function) {
        // Ch 6.15 map method
        // Return an array list consisting of the results of applying the function to the given elements

        int len = list.size();
        ArrayList<R> newList = new ArrayList<>(len);
        list.forEach(e -> newList.add(function.apply(e)) );

        return newList;
    }

    public static void main(String[] args) {
        Integer[] aa = {1,2,3,4,5};
        System.out.println(max(aa));
        ArrayList<String> a = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String input = "a" + i;
            a.add(input);
        }
        System.out.println("map method test:");
        System.out.println(map(a, (e) -> e.toString() + " [Testing] "));
    }
}
