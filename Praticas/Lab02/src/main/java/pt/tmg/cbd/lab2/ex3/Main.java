package pt.tmg.cbd.lab2.ex3;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pt.tmg.cbd.lab2.ex3.a.RestaurantsService;

import java.util.Arrays;

import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("cbd");
        MongoCollection<Document> collection = database.getCollection("restaurants");

        // se houver database e coleçao retorna tudo certo
        System.out.println("Database: " + database.getName());
        System.out.println("Collection: " + collection.getNamespace().getCollectionName());

        // exercise a
        RestaurantsService restaurantsService = new RestaurantsService(collection);

        // insert a restaurant
        Document restaurantToInsert = new Document("nome", "Restaurante de Teste")
                .append("localidade", "Aveiro")
                .append("gastronomia", "Portuguese")
                .append("address", new Document("rua", "Rua de Teste")
                        .append("zip", "3800-000")
                        .append("coord", Arrays.asList(40.7128, 74.0060))
                .append("grades", Arrays.asList(
                        new Document("date", "2021-01-01")
                            .append("grade", "A")
                            .append("score", 10),
                        new Document("date", "2021-01-02")
                            .append("grade", "B")
                            .append("score", 8)
                        ))
                );
        
        restaurantsService.insertRestaurant(restaurantToInsert);

        // search for a restaurant by name
        restaurantsService.searchRestaurant("Restaurante de Teste");

        // update a restaurant
        String newName = "Restaurante de Teste Updated";
        restaurantsService.editRestaurant("Restaurante de Teste", newName);

        // search for a restaurant by name
        restaurantsService.searchRestaurant(newName); // returns the updated restaurant
        restaurantsService.searchRestaurant("Restaurante de Teste"); // should print "Restaurant not found"

        // delete a restaurant
        restaurantsService.deleteRestaurant(newName);

        // exercise b

        mongoClient.close();
    }
}