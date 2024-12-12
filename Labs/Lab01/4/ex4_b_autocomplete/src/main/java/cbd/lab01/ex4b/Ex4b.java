package cbd.lab01.ex4b;
import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.AbstractMap;

import java.io.File;
import java.util.Scanner;

public class Ex4b {
    // Sorted set that contains the names and their popularity
    public static String NAMES_SCORES_KEY = "names_scores";

    // Sorted set that contains the names (scores are always 0)
    // This auxiliar set is used to search for names with a specific prefix
    public static String NAMES_KEY = "names";

    private static void addNames(Jedis jedis, Scanner sc) {

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            String name = parts[0];
            Double popularity = Double.parseDouble(parts[1]);
            jedis.zadd(NAMES_SCORES_KEY, popularity, name);
            jedis.zadd(NAMES_KEY, 0, name);
        }
    }

    private static void searchPrefix(Jedis jedis, String prefix) {
        // Get all the names that start with the prefix
        List<String> namesWithPrefix = jedis.zrangeByLex(NAMES_KEY, "[" + prefix, "[" + prefix + "\uFFFF");

        if (namesWithPrefix.isEmpty()) {
            return;
        }

        // Get the popularity of the names with the prefix
        // The method zscore returns the score of the element in the sorted set
        // Then the names are sorted by popularity, comparing the scores
        namesWithPrefix.stream()
            .map(name -> new AbstractMap.SimpleEntry<>(name, jedis.zscore(NAMES_SCORES_KEY, name)))
            .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
            .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
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
        jedis.del(NAMES_SCORES_KEY);
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