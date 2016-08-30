package InputAndOutput;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by mikim on 2016-08-24.
 */
public class InputOutputMain {


    public static File produceTocFile(File file, String delimiter) throws FileNotFoundException {
        /*
        Ch 9.2
        Read a text File file and produces a file with same name but extension .toc,
        containing an alphabetized list of all words in the input file together with a list of line numbers in which
        each word occurs.
         */
        if (!file.exists()) {
            throw new FileNotFoundException("Input file is not valid!");
        }

        TreeMap<String, Integer> map = new TreeMap<>();
        final AtomicInteger lineCounter = new AtomicInteger(1);
        File newFile = null;

        Path filePath = file.toPath();
        int fileWithExtensionLength = filePath.getFileName().toString().length();
        String fileName = filePath.getFileName().toString().substring(0, fileWithExtensionLength - 3) + "toc";

        try (Stream<String> lines = Files.lines(filePath)) {
            Path path = Paths.get(filePath.getParent().toString() + "\\" + fileName);

            if (Files.exists(path)) Files.delete(path);
            newFile = Files.createFile(Paths.get(filePath.getParent().toString() + "\\" + fileName)).toFile();

            lines.forEach(e -> {
                String[] wordsInEachLIne = e.split(delimiter);
                for (String element : wordsInEachLIne) {
                    map.put(element, lineCounter.get());
                }
                lineCounter.incrementAndGet();
            });
            map.forEach((k,v) -> System.out.println("Key: " + k + ". line number: " + v));

        } catch (IOException e) {
            e.printStackTrace();
        }


        while (!map.isEmpty()) {
            Map.Entry<String, Integer> entry = map.pollFirstEntry();
            String text = entry.getKey() + ":" + entry.getValue() + " ";
            try {
                Files.write(newFile.toPath(), text.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newFile;
    }

    public static ArrayList<Integer> extractAllIntegers1(String input) {
        /*
            Ch 9. 10 (a)
            Extract All decimal integers including negative ones from a String input to ArrayList<Integer> using find
             */
        ArrayList<Integer> list = new ArrayList<>();

        String regex = "[-+]?\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String match = matcher.group();
            list.add(Integer.parseInt(match));
        }
        return list;
    }

    public static ArrayList<Integer> extractAllIntegers2(String input) {
        /*
            Ch 9. 10 (a)
            Extract All decimal integers including negative ones from a String input to ArrayList<Integer> using split
             */
        String[] tokens = input.replaceAll("[^-+\\d]+", " ").split(" ");
        int len = tokens.length;
        ArrayList<Integer> list = new ArrayList<>(len);

        for (int i = 0; i < len; i++) {
            list.add(Integer.parseInt(tokens[i]));
        }
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\mikim\\IdeaProjects\\Java_Education\\test.txt");
        File f1 = produceTocFile(file, " ");

        String input = "1234adsfa-342 2342. -323/323.0 -231dfa23424// -324";
        System.out.println("Extract all decimal Integers with find(): " +
                extractAllIntegers1(input));
        System.out.println("Extract all decimal Integers with split(): " + extractAllIntegers2(input));


    }
}