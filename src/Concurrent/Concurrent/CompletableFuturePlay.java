package Concurrent.Concurrent;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFuturePlay {
    private static CompletableFuture<String> readFile(Path path) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new String(Files.readAllBytes(path));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    public static void main(String[] args) {

//        Path path = Paths.get("C:\\Users\\mikim\\IdeaProjects\\Java_Education\\test.txt");
        Path path = Paths.get("c:/", "__inexist.foo.bar.baz");
        CompletableFuture<List<String[]>> future = readFile(path)
                .thenApply(text -> text.split("\n"))
                .handle((lines, exception) -> {
                    // TODO
                    if (exception != null) {
                        throw new RuntimeException(exception);
                    }

                    List<String[]> list = Arrays.stream(lines)
                            .map(line -> line.split("\\W+"))
                            .collect(Collectors.toList());
                    return list;
                });

        List<String[]> wordsByLine = null;
        try {
            wordsByLine = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(wordsByLine);
    }
}
