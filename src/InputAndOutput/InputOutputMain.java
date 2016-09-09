package InputAndOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by mikim on 2016-08-24.
 */
public class InputOutputMain {


    public static File produceTocFile(File file) throws FileNotFoundException {
        /*
        Ch 9.2
        Read a text File file and produces a file with same name but extension .toc,
        containing an alphabetized list of all words in the input file together with a list of line numbers in which
        each word occurs.
         */
        if (!file.exists()) {
            throw new FileNotFoundException("Input file is not valid!");
        }

        TreeMap<String, Set<Integer>> map = new TreeMap<>();


        LongAdder lineCounter = new LongAdder();
        lineCounter.increment();    // First line is line1
        File newFile = null;

        Path filePath = file.toPath();
        String fullFileName = filePath.getFileName().toString();
        System.out.println("full file name: " + filePath);
        String fileName = fullFileName.substring(0, fullFileName.lastIndexOf(".")) + ".toc";
        System.out.println("new file: " + fileName);

        try (Stream<String> lines = Files.lines(filePath)) {
            Path path = Paths.get(filePath.getParent().toString() + "\\" + fileName);

            if (Files.exists(path)) Files.delete(path);
            newFile = Files.createFile(Paths.get(filePath.getParent().toString() + "\\" + fileName)).toFile();

            lines.forEach(e -> {
                String[] wordsInEachLIne = e.split("\\W+");
                for (String element : wordsInEachLIne) {
                    int counter = lineCounter.intValue();

                    if (map.containsKey(element)) {
                        map.get(element).add(counter);
                    } else {
                        Set<Integer> valueSet = new HashSet<>();
                        valueSet.add(counter);
                        map.put(element, valueSet);
                    }
                }
                lineCounter.increment();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


        while (!map.isEmpty()) {
            Map.Entry<String, Set<Integer>> entry = map.pollFirstEntry();
            String text = entry.getKey() + ":" + entry.getValue().toString() + " ";
            try {
                Files.write(newFile.toPath(), text.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newFile;
    }

    private static void printFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            list.add(Integer.parseInt(matcher.group()));
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

        String regex = "[-+]?\\d+";
        Pattern pattern = Pattern.compile(regex);
        for (int i = 0; i < len; i++) {
            Matcher matcher = pattern.matcher(tokens[i]);
            if (matcher.find()) {
                list.add(Integer.parseInt(matcher.group()));
            }
        }
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\mikim\\IdeaProjects\\Java_Education\\test.txt");
        System.out.println("Text file is: ");
        printFile(file);
        File newFile = produceTocFile(file);
        System.out.println("\nResult file is: ");
        printFile(newFile);


        String input = "+1234adsfa-3422+342. -323/+323.0 -231dfa23424// -324 -+ - +";
        System.out.println("\nString input is: '" + input + "'");
        System.out.println("Extract all decimal Integers with find(): " + extractAllIntegers1(input));
        System.out.println("Extract all decimal Integers with split(): " + extractAllIntegers2(input));
    }
}