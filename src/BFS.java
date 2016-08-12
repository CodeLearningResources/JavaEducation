/**
 * Created by mikim on 2016-07-28.
 */
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Byeongho on 2016-07-27.
 * 3장 10번 문제_재제출입니다.
 */

public class BFS {

    public static void Bfs(File f) throws IOException, NullPointerException{

        ArrayDeque<File> queue = new ArrayDeque<>();
        queue.offer(f);

        while(queue.size() > 0){
            File[] files = queue.poll().listFiles(File::isDirectory);
            if (files.length > 0) {
                for (File file : files) {
                    System.out.println(file.getPath());
                    queue.offer(file);
                }
            }
        }

    }

    public static void Dfs(File f) throws IOException, NullPointerException {
        ArrayDeque<File> stack = new ArrayDeque<>();
        stack.add(f);

        while(stack.size() > 0){
            File[] files = stack.pop().listFiles(File::isDirectory);
            if (files.length > 0) {
                for (File file : files) {
                    System.out.println(file.getPath());
                    stack.add(file);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, NullPointerException {
        File f = new File("C:/Users/mikim");
        System.out.println("BFS: ");
        Bfs(f);
        System.out.println("\nDFS: ");
        Dfs(f);


    }
}