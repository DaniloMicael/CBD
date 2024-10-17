package pt.tmg.cbd.lab2.ex3.a;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

// simple class to insert, edit and search for restaurants collection

public class RestaurantsService {

    private MongoCollection<Document> collection;

    public RestaurantsService(MongoCollection<Document> collection) {
        this.collection = collection;
    }
    
    // insert a restaurant
    public void insertRestaurant(Document restaurant) {
        try {
            collection.insertOne(restaurant);
            System.out.println("Restaurant inserted");
        } catch (Exception e) {
            System.out.println("Error inserting restaurant: " + e.getMessage());
        }

    }

    // edit a restaurant
    public void editRestaurant(String name, String newName) {
        try {
            collection.updateOne(new Document("nome", name), new Document("$set", new Document("nome", newName)));
            System.out.println("Restaurant updated");
        } catch (Exception e) {
            System.out.println("Error updating restaurant: " + e.getMessage());
        }
    }

    // search for a restaurant
    public void searchRestaurant(String name) {
        Document restaurant = collection.find(new Document("nome", name)).first();
        if (restaurant != null) {
            System.out.println("Restaurant found: " + restaurant.toJson());
        } else {
            System.out.println("Restaurant not found");
        }

    }

    // delete a restaurant
    public void deleteRestaurant(String name) {
        try {
            collection.deleteOne(new Document("nome", name));
            System.out.println("Restaurant deleted");
        } catch (Exception e) {
            System.out.println("Error deleting restaurant: " + e.getMessage());
        }
    }
}
