package cbd.lab01.ex5a;

import redis.clients.jedis.Jedis;
import java.time.Instant;
import java.util.Scanner;

public class Ex5a {
    private static final int LIMIT = 5;             // 5 maximum requests per timeslot
    private static final int TIMESLOT = 30;         // 30 seconds

    private static void requestProduct(Jedis jedis, String input) {
        String[] request = input.split(";");
        String username = request[0];
        String product = request[1];

        // Get the current time stamp
        long currentTime = Instant.now().getEpochSecond();

        // Create the user key
        String userKey = "user:" + username;

        // Remove all the requests that are older than the time slot
        jedis.zremrangeByScore(userKey, 0, currentTime - TIMESLOT);

        // Get the number of requests in the time slot
        long numProducts = jedis.zcount(userKey, currentTime - TIMESLOT, currentTime);

        if (numProducts >= LIMIT) {
            System.out.println("User " + username + " has reached the limit of requests (5) in the last " + TIMESLOT + " seconds.");
            System.out.println("Wait " + (TIMESLOT - (currentTime - jedis.zscore(userKey, jedis.zrange(userKey, 0, 0).iterator().next()))) + " seconds to make a new request.");
            return;
        } else {
            // Create the product entry. Necessary to have a unique key for each product
            String productEntry = product + ":" + currentTime;

            // Add the product to the user key
            jedis.zadd(userKey, currentTime, productEntry);
            System.out.println("User " + username + " requested product " + product + ".");
        }
    }

    public static void main( String[] args ) {
        Jedis jedis = new Jedis();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSystem is ready to receive requests. Press Enter to exit.");

        while (true) {
            System.out.print("\nRequest details (username;product): ");

            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("\nExiting...");
                break;
            }

            try {
                requestProduct(jedis, input);
            } catch (Exception e) {
                System.out.println("Invalid input. Should be <username>;<product>.");
                continue;
            }
        }

        sc.close();
        jedis.close();
    }
}
