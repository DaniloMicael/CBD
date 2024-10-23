package pt.tmg.cbd.lab2.ex3;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pt.tmg.cbd.lab2.ex3.a.RestaurantsService;
import pt.tmg.cbd.lab2.ex3.b.RestaurantsSearch;
import pt.tmg.cbd.lab2.ex3.c.RestaurantsQueries;
import pt.tmg.cbd.lab2.ex3.d.RestaurantsDAO;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("cbd");
        MongoCollection<Document> collection = database.getCollection("restaurants");

        // se houver database e coleçao retorna tudo certo
        System.out.println("Database: " + database.getName());
        System.out.println("Collection: " + collection.getNamespace().getCollectionName());

        executeExerciseA(collection);
        executeExerciseB(collection);
        executeExerciseC(collection);
        executeExerciseD(collection);

        mongoClient.close();
    }

    private static void executeExerciseA(MongoCollection<Document> collection) {
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
    }

    private static void executeExerciseB(MongoCollection<Document> collection) {
        RestaurantsSearch restaurantsSearch = new RestaurantsSearch(collection);

        System.out.println("================ Searching without Indexes ================\n\n");
        // search for a restaurant by locality
        restaurantsSearch.searchRestaurantByLocality("Queens");

        // search for a restaurant by gastronomy
        restaurantsSearch.searchRestaurantByGastronomy("Portuguese");

        // search for a restaurant by name
        restaurantsSearch.searchRestaurantByName("Ho Mei Restaurant");

        // create indexes
        restaurantsSearch.createIndexes();

        System.out.println("================ Searching with Indexes ================\n\n"); 

        // search for a restaurant by locality
        restaurantsSearch.searchRestaurantByLocality("Queens");

        // search for a restaurant by gastronomy
        restaurantsSearch.searchRestaurantByGastronomy("Portuguese");

        // search for a restaurant by name
        restaurantsSearch.searchRestaurantByName("Ho Mei Restaurant");

        // delete indexes
        restaurantsSearch.deleteIndexes();
    }

    private static void executeExerciseC(MongoCollection<Document> collection) {
        RestaurantsQueries restaurantsQueries = new RestaurantsQueries(collection);

        // query 11 in exercise 2.2
        restaurantsQueries.query1();

        // query 14 in exercise 2.2
        restaurantsQueries.query2();

        // query 18 in exercise 2.2
        restaurantsQueries.query3();

        // query 20 in exercise 2.2
        restaurantsQueries.query4();

        // query 24 in exercise 2.2
        restaurantsQueries.query5();
    }

    private static void executeExerciseD(MongoCollection<Document> collection) {
        RestaurantsDAO restaurantsDAO = new RestaurantsDAO(collection);

        int countLocalidades = restaurantsDAO.countLocalidades();
        System.out.println("Numero de localidades distintas: " + countLocalidades);

        System.out.println("Numero de restaurantes por localidade:");
        Map<String, Integer> countRestByLocalidade = restaurantsDAO.countRestByLocalidade();
        for (Map.Entry<String, Integer> entry : countRestByLocalidade.entrySet()) {
            System.out.println(" -> " + entry.getKey() + " - " + entry.getValue());
        }

        System.out.println("Nome de restaurantes contendo 'Park' no nome:");
        List<String> restWithNameCloserTo = restaurantsDAO.getRestWithNameCloserTo("Park");
        for (String name : restWithNameCloserTo) {
            System.out.println(" -> " + name);
        }
    }
}