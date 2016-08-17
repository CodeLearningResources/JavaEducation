package Collection;

import java.util.*;

/**
 * Created by mikim on 2016-08-05.
 */
public class Main {

    public static Iterator<String> replaceWithIterator(ArrayList<String> list) {
        // Ch 7.2 (a) With a iterator
        Iterator<String> iterator = list.iterator();
        iterator.forEachRemaining(String::toUpperCase);

        return iterator;
    }

    public static ArrayList<String> replaceWithLoop(ArrayList<String> list) {
        // Ch 7.2 (b). With a loop over index values
        int len = list.size();
        for (int i = 0; i < len; i++) {
            list.set(i, list.get(i).toUpperCase());
        }

        return new ArrayList<>(list);
    }

    public static ArrayList<String> replaceAllMethod(ArrayList<String> list) {
        // Ch 7.2 (c). With the replaceAll method
        list.replaceAll(String::toUpperCase);
        return new ArrayList<>(list);
    }

    public static <T> Set<T> unionSet(Set<T> set1, Set<T> set2) {
        // Ch 7.3 union set method
        Set<T> union = new HashSet<>(set1);
        union.addAll(set2);
        return union;
    }

    public static <T> Set<T> intersectionSet(Set<T> set1, Set<T> set2) {
        // Ch 7.3 intersection set method

        Set<T> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection;
    }

    public static <T> Set<T> differenceSet(Set<T> set1, Set<T> set2) {
        // Ch 7.3 difference set method
        Set<T> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        return difference;
    }

    public static List<String> shuffle(String input) {
        /*
        Ch 7.11
        Return a list with items shuffled except the first and the last.
        */
        if (input == null) throw new NullPointerException("Input is null.");
        else if (input.length() == 0) return new ArrayList<>();
        else {
            List<String> list = Arrays.asList(input.split(" "));
            // Shuffle only when there are 2 or more words.
            if (list.size() >= 2) Collections.shuffle(list.subList(1, list.size() - 1));

            return new ArrayList<>(list);
        }
    }

    public static List<Integer> getNumbers(int n) {
        /*
        Ch 7.14
        Return an immutable list view of the numbers from 0 to n, without actually storing the numbers.
        */
        return new AbstractList<Integer>() {

            @Override
            public int indexOf(Object o) {
                if (o == null) return -1;       // Return -1 if object o is null
                if (!(o instanceof Integer)) throw new IllegalArgumentException("Index must be an Integer!");
                else if ((Integer) o < 0 || (Integer) o >= size())
                    throw new IndexOutOfBoundsException("Invalid Index given!");
                else return (Integer) o;
            }

            @Override
            public int lastIndexOf(Object o) {
                return indexOf(o);
            }

            @Override
            public Integer get(int index) {
                if (index >= 0 && index < size()) return index;
                else throw new IndexOutOfBoundsException("Index must be zero or positive integer!");
            }

            @Override
            public int size() {
                return n + 1;
            }

            @Override
            public boolean contains(Object o) {
                return o != null && (Integer) o < size();
            }
        };
    }


    public static void  main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("abc", "123", "justin", "trumpia123"));
        System.out.println("Original arrayList: " + arrayList);

        ArrayList<String> iteratorList = new ArrayList<>(arrayList);
        System.out.println("Iterator: " + replaceWithIterator(iteratorList));

        ArrayList<String> loopList = new ArrayList<>(arrayList);
        System.out.println("Loop: " + replaceWithLoop(loopList));

        ArrayList<String> replaceAllList = new ArrayList<>(arrayList);
        System.out.println("replaceAll: " + replaceAllMethod(replaceAllList));

        System.out.println("Shuffle: " + shuffle("aaa ccc asdf bbb"));
        List<Integer> aa = getNumbers(10);
       Integer a = aa.get(0);
        System.out.println(a);

        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        s1.add("abc");
        s1.add("qqq");
        s2.add("qqq");
        System.out.println("Intersection: " + differenceSet(s1, s2));

        List<Integer> b = getNumbers(10);
        System.out.println("abstract: " + b.contains(null));
    }
}
