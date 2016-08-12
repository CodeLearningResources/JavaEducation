package Collection;

import java.util.*;

/**
 * Created by mikim on 2016-08-05.
 */
public class Main {

    public static ArrayList<String> replaceWithIterator(ArrayList<String> list) {
        // Ch 7.2 (a)
        ArrayList<String> newLIst = new ArrayList<>(list.size());
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            newLIst.add(iterator.next().toUpperCase());
        }
        return newLIst;
    }

    public static ArrayList<String> replaceWithLoop(ArrayList<String> list) {
        // Ch 7.2 (b)
        int len = list.size();
        ArrayList<String> newList = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            newList.add(list.get(i).toUpperCase());
        }
        return newList;
    }

    public static ArrayList<String> replaceAllMethod(ArrayList<String> list) {
        // Ch 7.2 (c)
        list.replaceAll(String::toUpperCase);
        return new ArrayList<>(list);
    }

//    public static Set<Comparator> unionSet(Set<Comparator> set1, Set<Comparator> set2) {
//        // Ch 7.3 union set method
//
//    }

//    public static Set<Comparator> intersectionSet(Set<Comparator> set1, Set<Comparator> set2) {
//        // Ch 7.3 intersection set method
//
//        if (!set1.containsAll(set2)) {
//            return new HashSet<>();
//        }
//    }

//    public static Set<Comparator> differneceSet(Set<Comparator> set1, Set<Comparator> set2) {
//        // Ch 7.3 difference set method
//
//    }


    public static void  main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("abc", "123", "justin", "trumpia123"));
        System.out.println("Original arrayList: " + arrayList);

        ArrayList<String> iteratorList = new ArrayList<>(arrayList);
        System.out.println("Iterator: " + replaceWithIterator(iteratorList));

        ArrayList<String> loopList = new ArrayList<>(arrayList);
        System.out.println("Loop: " + replaceWithLoop(loopList));

        ArrayList<String> replaceAllList = new ArrayList<>(arrayList);
        System.out.println("replaceAll: " + replaceAllMethod(replaceAllList));
    }
}
