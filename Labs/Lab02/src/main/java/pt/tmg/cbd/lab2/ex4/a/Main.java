package pt.tmg.cbd.lab2.ex4.a;

import java.sql.Date;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Main {

    private static final int LIMIT = 5;
    private static final int TIMESLOT = 30 * 1000; // 30 seconds
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("cbd");
        MongoCollection<Document> collection = database.getCollection("userProduct");

        System.out.println("Database: " + database.getName());
        System.out.println("Collection: \n" + collection.getNamespace().getCollectionName());

        requestDetails(collection);

        mongoClient.close();
    }

    public static void requestDetails(MongoCollection<Document> collection) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.print("\nRequest details (username;product): ");

            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("\nExiting...");
                break;
            }

            try {
                requestProduct(input, collection);
            } catch (Exception e) {
                System.out.println("Invalid input. Should be <username>;<product>.");
                continue;
            }
        }
        sc.close();
    }

    public static void requestProduct(String input, MongoCollection<Document> collection) {
        String[] parts = input.split(";");
        String username = parts[0];
        String product = parts[1];

        // Get the current time stamp
        long currentTime = System.currentTimeMillis();

        long timeWindow = currentTime - TIMESLOT;

        long actualNumberOfRequests = collection.countDocuments(Filters.and(
            Filters.eq("username", username),
            Filters.gte("timestamp", new Date(timeWindow))
        ));

        if (actualNumberOfRequests >= LIMIT) {
            System.out.println("User " + username + " has reached the limit of requests in the last " + TIMESLOT / 1000 + "s.");
        } else {
            Document doc = new Document("username", username)
                .append("product", product)
                .append("timestamp", new Date(currentTime));

            collection.insertOne(doc);
            System.out.println("Request accepted.");
        }
    }
}
