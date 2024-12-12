package pt.tmg.cbd.lab2.ex4.b;

import java.sql.Date;
import java.util.Arrays;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

public class Main {

    private static final int LIMIT = 30; // 30 maximum units of products by timeslot
    private static final int TIMESLOT = 60 * 1000; // 60 seconds
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
            System.out.print("\nRequest details (username;product;qty): ");

            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("\nExiting...");
                break;
            }

            try {
                requestProduct(input, collection);
            } catch (Exception e) {
                System.out.println("Invalid input. Should be <username>;<product;qty>.");
                continue;
            }
        }
        sc.close();
    }

    public static void requestProduct(String input, MongoCollection<Document> collection) {
        String[] parts = input.split(";");
        String username = parts[0];
        String product = parts[1];
        int qty = Integer.parseInt(parts[2]);

        // long startTime = System.currentTimeMillis();
        
        // Get the current time stamp
        long currentTime = System.currentTimeMillis();
        
        long timeWindow = currentTime - TIMESLOT;

        Document result = collection.aggregate(Arrays.asList(
            Aggregates.match(Filters.and(
                Filters.eq("username", username),
                Filters.gte("timestamp", new Date(timeWindow))
            )),
            Aggregates.group(null, Accumulators.sum("total", "$qty"))
        )).first();

        long actualNumberOfUnits = (result != null) ? result.getInteger("total") : 0;

        // long endTime = System.currentTimeMillis();
        // System.out.println("Time to get the total number of units: " + (endTime - startTime) + "ms");

        if (actualNumberOfUnits + qty > LIMIT) {
            System.out.println("User " + username + " has reached the limit of requests (30 units) in the last " + TIMESLOT / 1000 + "s.");
            System.out.println("Total units requested: " + actualNumberOfUnits + ". Units requested in this attempt: " + qty + ".");
        } else {

            // long insertStartTime = System.currentTimeMillis();

            Document doc = new Document("username", username)
                .append("product", product)
                .append("qty", qty)
                .append("timestamp", new Date(currentTime));

            collection.insertOne(doc);

            // long insertEndTime = System.currentTimeMillis();
            // System.out.println("Time to insert the new request: " + (insertEndTime - insertStartTime) + "ms");

            System.out.println("Request accepted.");
        }
    }
}
