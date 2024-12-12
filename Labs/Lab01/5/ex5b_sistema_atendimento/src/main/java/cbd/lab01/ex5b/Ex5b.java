package cbd.lab01.ex5b;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.Tuple;

import java.time.Instant;
import java.util.Scanner;
import java.util.List;

public class Ex5b {
    private static final int LIMIT = 30;            // 30 maximum units of products per timeslot
    private static final int TIMESLOT = 60;         // 60 seconds

    private static void requestProduct(Jedis jedis, String input) {
        String[] request = input.split(";");
        String username = request[0];
        String product = request[1];
        int quantity = Integer.parseInt(request[2]);

        // Get the current time stamp
        long currentTime = Instant.now().getEpochSecond();

        // Create the user key
        String userKey = "user:" + username;

        // Remove all the requests that are older than the time slot
        jedis.zremrangeByScore(userKey, 0, currentTime - TIMESLOT);

        // Get all the requests in the time slot
        List<Tuple> recentRequests = jedis.zrangeByScoreWithScores(userKey, currentTime - TIMESLOT, currentTime);

        // Get the number of products requested in the time slot
        int productsQty = 0;
        for (Tuple requestEntry : recentRequests) {
            productsQty += Integer.parseInt(requestEntry.getElement().split(":")[1]);
        }

        // Check if the user has reached the limit of requests
        if (productsQty + quantity > LIMIT) {
            System.out.println("User " + username + " has reached the limit of requests (30 units) in the last " + TIMESLOT + " seconds.");
            System.out.println("Total units requested: " + productsQty + ". Units requested in this attempt: " + quantity + ".");
            return;
        } else {
            // Create the product entry. Necessary to have a unique key for each product
            String productEntry = product + ":" + quantity + ":" + currentTime;

            // Add the product to the user key
            jedis.zadd(userKey, currentTime, productEntry);
            System.out.println("User " + username + " requested " + quantity + " units of product " + product + ".");
        }
    }

    public static void main( String[] args ) {
        Jedis jedis = new Jedis();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSystem is ready to receive requests. Press Enter to exit.");

        while (true) {
            System.out.print("\nRequest details (username;product;quantity): ");

            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("\nExiting...");
                break;
            }

            try {
                requestProduct(jedis, input);
            } catch (Exception e) {
                System.out.println("Invalid input. Should be <username>;<product>;<quantity>.");
                continue;
            }
        }

        sc.close();
        jedis.close();
    }
}
