package Concurrent.Concurrent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by mikim on 2016-09-05.
 */
public class Main {
    public static CompletableFuture<String> readPage(String page) {
        return CompletableFuture.completedFuture(page);
    }

    public static List<URL> getLinks(String page) {

        Document doc;
        try {
            doc = Jsoup.connect(page).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements links = doc.select("a[href]"); // a with href
        List<URL> urls = new ArrayList<>();
        for (Element link : links) {
            String absHref = link.attr("abs:href");
            try {
                if (!absHref.isEmpty()) urls.add(new URL(link.attr("abs:href")));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return urls;
    }

    public static void DisplayAllLinks(String webPage) {
        /*
        Ch 10.24. Ask the user for a URL and reads the web page at that URL, and displays all the links
         */
        CompletableFuture<String> contents = readPage(webPage);
        contents.thenApply(Main::getLinks).thenAccept(System.out::println);

        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException{
        File file = new File("C:\\Users\\mikim\\IdeaProjects");
        String keyword = "java";
        final int DEQUE_THREADS = 5;
        final int ENQUE_THREADS = 1;
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        CountDownLatch latch = new CountDownLatch(DEQUE_THREADS + ENQUE_THREADS);
        ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                30L, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        EnqueueTask enqueueTask1 = new EnqueueTask(file, blockingQueue, latch);
        executor.execute(enqueueTask1);

        DequeueTask dequeueTask1 = new DequeueTask(keyword, blockingQueue, latch);
        for (int i = 0 ; i < DEQUE_THREADS; i++) {
            executor.execute(dequeueTask1);
        }

        latch.await();
        executor.shutdown();
        System.out.println("Finished all threads");

        System.out.println("Display all Links:");
        String webPage = "https://www.yahoo.com";
        DisplayAllLinks(webPage);
    }
}
