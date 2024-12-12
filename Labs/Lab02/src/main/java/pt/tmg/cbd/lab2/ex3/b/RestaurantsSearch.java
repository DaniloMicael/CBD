package pt.tmg.cbd.lab2.ex3.b;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

public class RestaurantsSearch {
    private MongoCollection<Document> collection;

    public RestaurantsSearch(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void searchRestaurantByLocality(String locality) {
        long start = System.currentTimeMillis();
        Document restaurant = collection.find(new Document("localidade", locality)).first();
        if (restaurant != null) {
            long end = System.currentTimeMillis();
            System.out.println("Time to search by locality: " + (end - start) + "ms\n");
        } else {
            System.out.println("Restaurant not found");
        }
    }

    public void searchRestaurantByGastronomy(String gastronomy) {
        long start = System.currentTimeMillis();
        Document restaurant = collection.find(new Document("gastronomia", gastronomy)).first();
        if (restaurant != null) {
            long end = System.currentTimeMillis();
            System.out.println("Time to search by gastronomy: " + (end - start) + "ms\n");
        } else {
            System.out.println("Restaurant not found");
        }
    }

    public void searchRestaurantByName(String name) {
        long start = System.currentTimeMillis();
        Document restaurant = collection.find(new Document("nome", name)).first();
        if (restaurant != null) {
            long end = System.currentTimeMillis();
            System.out.println("Time to search by name: " + (end - start) + "ms\n");
        } else {
            System.out.println("Restaurant not found");
        }
    }

    public void createIndexes() {
        collection.createIndex(new Document("localidade", 1));
        collection.createIndex(new Document("gastronomia", 1));
        collection.createIndex(new Document("nome", "text"));
    }

    public void deleteIndexes() {
        collection.dropIndex("localidade_1");
        collection.dropIndex("gastronomia_1");
        collection.dropIndex("nome_text");
    }
}
