package Collection;

import java.util.*;

/**
 * Created by mikim on 2016-08-05.
 */
public class Main {

    public static void replaceWithIterator(ArrayList<String> list) {
        // Ch 7.2 (a) With a iterator
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.set(iterator.next().toUpperCase());
        }
    }

    public static void replaceWithLoop(ArrayList<String> list) {
        // Ch 7.2 (b). With a loop over index values
        int len = list.size();
        for (int i = 0; i < len; i++) {
            list.set(i, list.get(i).toUpperCase());
        }
    }

    public static void replaceAllMethod(ArrayList<String> list) {
        // Ch 7.2 (c). With the replaceAll method
        list.replaceAll(String::toUpperCase);
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

    public static List<String> shuffle(List<String> input) {
        /*
        Ch 7.11
        Return a list with items shuffled except the first and the last.
        */
        if (input == null) throw new NullPointerException("Input is null.");
        else if (input.size() == 0) return new ArrayList<>(input);
        else {
            // Shuffle only when there are more than 2 words.
            if (input.size() > 2) Collections.shuffle(input.subList(1, input.size() - 1));

            return new ArrayList<>(input);
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
                if (o == null) throw new NullPointerException("Invalid index is given!");
                if (!(o instanceof Integer)) return -1;
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
                return (o instanceof Integer) && (Integer) o < size() && (Integer) o >= 0;
            }
        };
    }


    public static void  main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("abc", "123", "justin", "trumpia123"));
        System.out.println("Original arrayList: " + arrayList);

        ArrayList<String> iteratorList = new ArrayList<>(arrayList);
        replaceWithIterator(iteratorList);
        System.out.println("Iterator: " + iteratorList);

        ArrayList<String> loopList = new ArrayList<>(arrayList);
        replaceWithLoop(loopList);
        System.out.println("Loop: " + loopList);

        ArrayList<String> replaceAllList = new ArrayList<>(arrayList);
        replaceAllMethod(replaceAllList);
        System.out.println("replaceAll: " + replaceAllList);

        String[] q = {"asdf", "ccc", "asd", "bbb"};
        System.out.println("Shuffle: " + shuffle(Arrays.asList(q)));
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
        System.out.println("abstract: " + b.contains(1));
    }
}
