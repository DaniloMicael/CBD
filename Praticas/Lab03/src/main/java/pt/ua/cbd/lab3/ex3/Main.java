package pt.ua.cbd.lab3.ex3;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;

public class Main {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder().withKeyspace("videosharing").build()) {
            DatabaseQueries db = new DatabaseQueries(session);

            // ======================= a) insert edit delete search ex ======================= //

            // insert example
            db.insertUser("danilo", "Danilo Silva", "danilomicael@ua.pt");

            // edit example
            db.editUser("danilo", "Danilo Silva", "newemail@ua.pt");

            // delete example
            db.deleteUser("danilo");

            // search example
            ResultSet user = db.searchUser("user1");
            user.forEach(row -> {
                System.out.println("Username: " + row.getString("username"));
                System.out.println("Full Name: " + row.getString("full_name"));
                System.out.println("Email: " + row.getString("email"));
                System.out.println("Registration Timestamp: " + row.getInstant("registration_timestamp"));
            });


            // ======================= b) impl of 4 queries of ex 3.2 ======================= //

            // query2 example
            ResultSet tags = db.query2("user1", 1);
            System.out.println("\nTags for video 1:");
            tags.forEach(row -> {
                System.out.println("Tags: " + row.getSet("tags", String.class));
            });

            // query 4b example
            ResultSet events = db.query4b("user2");
            System.out.println("\nEvents for user2:");
            events.forEach(row -> {
                System.out.println("Username: " + row.getString("username"));
                System.out.println("Video ID: " + row.getInt("video_id"));
                System.out.println("Timestamp: " + row.getInstant("event_timestamp"));
                System.out.println("Event: " + row.getString("event_type"));
                System.out.println("Seconds: " + row.getInt("video_time_seconds"));
            });

            // query 7 example
            ResultSet query7 = db.query7(1);
            System.out.println("\nFollowers of video 1:");
            query7.forEach(row -> {
                System.out.println("User: " + row.getString("follower_username"));
            });

            // query 11 example
            ResultSet query11 = db.query11();
            System.out.println("\nTags and video count:");
            query11.forEach(row -> {
                System.out.println("Tag: " + row.getString("tag"));
                System.out.println("Video Count: " + row.getLong("video_count"));
            });
        }
    }
}