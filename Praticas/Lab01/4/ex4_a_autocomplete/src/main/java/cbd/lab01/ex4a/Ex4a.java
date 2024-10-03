package cbd.lab01.ex4a;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.util.Scanner;

public class Ex4a {
    public static String NAMES_KEY = "names";

    private static void addNames(Jedis jedis, Scanner sc) {

        while (sc.hasNextLine()) {
            // Add the name to the sorted set with a score of 0
            jedis.zadd(NAMES_KEY, 0, sc.nextLine());
        }
    }

    private static void searchPrefix(Jedis jedis, String prefix) {
        // Get all the names that start with the prefix
        // The "[" is used to include the prefix in the search (minimum value)
        // The "\uFFFF" is used to include all the characters in the search after the prefix (maximum value)
        jedis.zrangeByLex(NAMES_KEY, "[" + prefix, "[" + prefix + "\uFFFF").forEach(System.out::println);
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner sc = null; 
        try {
            File file = new File(args[0]);
            sc = new Scanner(file);
        } catch (Exception e) {
            System.err.println("Provide a file as argument");
            System.exit(1);
        }
        
        Jedis jedis = new Jedis();
        jedis.del(NAMES_KEY);

        addNames(jedis, sc);
        sc.close();

        String input;
        while (true) {
            System.out.print("Search for ('Enter' for quit): ");
            input = new Scanner(System.in).nextLine();

            if (input.isEmpty()) {
                break;
            }

            System.out.println();
            searchPrefix(jedis, input);
            System.out.println();
        }
        jedis.close();
    }
}