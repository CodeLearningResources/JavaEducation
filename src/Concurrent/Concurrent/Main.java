package Concurrent.Concurrent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by mikim on 2016-09-05.
 */
public class Main {

    public static void blockingQueue(File file, String keyword) {
        /*
        Ch 10.10. Use a blocking queue for processing files in a dir. One thread walks the file tree and
         inserts the files into a queue. Several threads remove the files and search each one for a given keyword,
         printing out any matches. When the producer is done, put dummy file into the queue.
         */

        BlockingQueue<Path> blockingQueue = new LinkedBlockingDeque<>();
        Runnable enqueueTask = () -> {
            try {
                Stream<Path> paths = Files.walk(file.toPath());
                paths.forEach(blockingQueue::add);

                Path dummy = Paths.get("C:\\Users\\mikim\\IdeaProjects\\Java_Education\\dummy.txt");

                if (Files.exists(dummy)) Files.delete(dummy);
                blockingQueue.add(Files.createFile(dummy));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Runnable dequeueTask = () -> {

            try {
                String path = null;
                while (!(path = blockingQueue.take().getFileName().toString()).equals("dummy.txt")) {
                    if (path.contains(keyword)) System.out.println(path);
                    else if (path.equals("dummy.txt")) return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Executor exec = Executors.newCachedThreadPool();
        exec.execute(enqueueTask);
        for (int i = 0; i < 5; i++) exec.execute(dequeueTask);
        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    }

    public static CompletableFuture<String> readPage(String page) {
        return CompletableFuture.supplyAsync(() -> page);
    }

    public static List<URL> getLinks(String page) {
        Document doc = null;
        try {
            doc = Jsoup.connect(page).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements links = doc.select("a[href]"); // a with href
        List<URL> urls = new ArrayList<>();
        for (Element link : links) {
            String absHref = link.attr("abs:href");
            try {
                if (!absHref.isEmpty()) urls.add(new URL(link.attr("abs:href")));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    public static void DisplayAllLinks(String webpage) {
        /*
        Ch 10.24. Ask the user for a URL and reads the web page at that URL, and displays all the links
         */
        CompletableFuture<String> contents = readPage(webpage);
        CompletableFuture<List<URL>> links = contents.thenApply(Main::getLinks);
        links.thenAccept(System.out::println);

        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {

        File file = new File("C:\\Users\\mikim\\IdeaProjects");
        System.out.println("BlockingQueue:");
        blockingQueue(file, "java");

        System.out.println("\nDisplay all links:");
        String webPage = "https://www.yahoo.com";
        DisplayAllLinks(webPage);

    }
}
