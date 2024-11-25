package pt.ua.cbd.lab3.ex4;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder().build()) {
            DatabaseDDL ddl = new DatabaseDDL(session);
            DatabaseDML dml = new DatabaseDML(session);
            DatabaseQueries queries = new DatabaseQueries(session);

            // ======================== DDL ======================== //

            // Create the keyspace
            ddl.createKeyspace("Restaurants");

            // Use the keyspace
            session.execute("USE Restaurants");

            // Create the tables
            ddl.createRestaurantTable();
            ddl.createRestaurantByCuisineTypeTable();
            ddl.createRestaurantReservationsTable();
            ddl.createRestaurantReviewsTable();
            ddl.createRestaurantMenusTable();

            // Create the indexes
            ddl.createIndexOnRestaurantCuisineType();
            ddl.createIndexOnDishKey();
            ddl.createIndexOnRestaurantAddress();

            // Create UDFs and UDA
            ddl.createUDF();
            ddl.createSumMapUDF();
            ddl.createStateFunction();
            ddl.createSumPricesUDA();

            // ======================== DML ======================== //

            // Insert data
            insertData(dml, true);

            // ======================== Queries ======================== //

            // Query1
            ResultSet query1 = queries.getLastReservation(1);
            System.out.println("\nQuery1: Last reservation for a specific restaurant - Restaurant ID: 1");
            query1.forEach(row -> {
                System.out.println("Reservation Name: " + row.getString("reservation_name"));
                System.out.println("Reservation Phone: " + row.getInt("reservation_phone"));
                System.out.println("Reservation Timestamp: " + row.getInstant("reservation_timestamp"));
                System.out.println("Reservation Date: " + row.getLocalDate("reservation_date"));
                System.out.println("Reservation Party Size: " + row.getInt("reservation_party_size"));
            });

            // Query2
            ResultSet query2 = queries.getRestaurantsReviews();
            System.out.println("\nQuery2: Reviews for each restaurant");
            query2.forEach(row -> {
                System.out.println("Restaurant ID: " + row.getInt("restaurant_id"));
                System.out.println("Review Count: " + row.getLong("review_count"));
            });

            // Query3
            ResultSet query3 = queries.getReservationsByDate(1, "2024-11-29");
            System.out.println("\nQuery3: All the reservations for a specific restaurant on a specific date - Restaurant ID: 1, Reservation Date: 2024-11-29");
            query3.forEach(row -> {
                System.out.println("Reservation Name: " + row.getString("reservation_name"));
                System.out.println("Reservation Phone: " + row.getInt("reservation_phone"));
                System.out.println("Reservation Timestamp: " + row.getInstant("reservation_timestamp"));
                System.out.println("Reservation Date: " + row.getLocalDate("reservation_date"));
                System.out.println("Reservation Party Size: " + row.getInt("reservation_party_size"));
            });

            // Query4
            ResultSet query4 = queries.getAverageRatingByRestaurant(1);
            System.out.println("\nQuery4: Average rating for a specific restaurant - Restaurant ID: 1");
            query4.forEach(row -> System.out.println("Average Rating: " + row.getDouble("average_rating")));

            // Query5
            ResultSet query5 = queries.getRestaurantWithCuisineType("Portuguese");
            System.out.println("\nQuery5: All the restaurants that serve a specific cuisine type - Portuguese");
            query5.forEach(row -> System.out.println("Restaurant Name: " + row.getString("name")));
            
            // Query6
            ResultSet query6 = queries.getCuisineTypeCount();
            System.out.println("\nQuery6: All the cuisine types and the number of restaurants that serve them");
            query6.forEach(row -> {
                System.out.println("Cuisine Type: " + row.getString("cuisine_type"));
                System.out.println("Restaurant Count: " + row.getLong("restaurant_count"));
            });

            // Query7
            ResultSet query7 = queries.getTotalReservationsByRestaurant();
            System.out.println("\nQuery7: Number of reservations for each restaurant");
            query7.forEach(row -> {
                System.out.println("Restaurant ID: " + row.getInt("restaurant_id"));
                System.out.println("Total Reservations: " + row.getLong("total_reservations"));
            });

            // Query8
            ResultSet query8 = queries.getTopPopularDishes(1);
            System.out.println("\nQuery8: Top popular dishes for a specific restaurant - Restaurant ID: 1");
            query8.forEach(row -> System.out.println("Popular Dishes: " + row.getList("popular_dishes", String.class)));

            // Query9
            ResultSet query9 = queries.getRestaurantsByAddress("Rua do Manel, 1");
            System.out.println("\nQuery9: Restaurants in a specific address - Rua do Manel, 1");
            query9.forEach(row -> {
                System.out.println("Restaurant ID: " + row.getInt("id"));
                System.out.println("Restaurant Name: " + row.getString("name"));
                System.out.println("Restaurant Phone: " + row.getInt("phone"));
                System.out.println("Cuisine Type: " + row.getSet("cuisine_type", String.class));
            });

            // Query10
            ResultSet query10 = queries.getRestaurantsByDish("Bacalhau à Brás");
            System.out.println("\nQuery10: All the restaurants that serve a specific dish - Bacalhau à Brás");
            query10.forEach(row -> System.out.println("Restaurant ID: " + row.getInt("restaurant_id")));

            // Queries to use the UDF and UDA

            // Average price of dishes for a specific restaurant - UDF
            ResultSet query11 = queries.getAveragePriceOfDishes(1);
            System.out.println("\nQuery11: (UDF) Average price of dishes for a specific restaurant - Restaurant ID: 1");
            query11.forEach(row -> System.out.println("Average Price: " + row.getDouble("average_price")));

            // Sum of prices for a specific restaurant - UDA
            ResultSet query12 = queries.getTotalPriceOfDishes(1);
            System.out.println("\nQuery12: Sum of prices for a specific restaurant - Restaurant ID: 1");
            query12.forEach(row -> System.out.println("Sum Prices: " + row.getDouble("total_price")));
        }
    }

    public static void insertData(DatabaseDML dml, Boolean insertData) {
        if (insertData) {
            // Insert data in restaurant table
            dml.insertRestaurant(1, "A Cozinha do Manel", "Rua do Manel, 1", 234556349, Set.of("Portuguese"));
            dml.insertRestaurant(2, "O Cantinho do Zé", "Rua do Zé, 2", 234556350, Set.of("Portuguese"));
            dml.insertRestaurant(3, "La Pizzaria Estufa", "Rua da Pizza, 3", 234556351, Set.of("Portuguese", "Italian"));
            dml.insertRestaurant(4, "Kebabs do António", "Rua do Kebab, 4", 234556352, Set.of("Portuguese", "Turkish", "Indian"));
            dml.insertRestaurant(5, "O Oriental", "Rua do Oriente, 5", 234556353, Set.of("Chinese", "Japanese"));
            dml.insertRestaurant(6, "Marisqueira da Costa", "Rua do Marisco, 6", 234556354, Set.of("Portuguese"));
            dml.insertRestaurant(7, "Haru Sushi", "Rua do Sushi, 7", 234556355, Set.of("Japanese"));
            dml.insertRestaurant(8, "Pasta&Tacos", "Rua da Pasta, 8", 234556356, Set.of("Italian", "Mexican"));
            dml.insertRestaurant(9, "TacoBell", "Rua do Taco, 9", 234556357, Set.of("Mexican"));
            dml.insertRestaurant(10, "O Cantinho do Bacalhau", "Rua do Bacalhau, 10", 234556358, Set.of("Portuguese"));
            dml.insertRestaurant(11, "I Love Pizza", "Rua da Pizza, 11", 234556359, Set.of("Italian"));
            dml.insertRestaurant(12, "Churrasqueira", "Rua do Churrasco, 12", 234556360, Set.of("Portuguese"));

            // Insert data in restaurant by cuisine type table
            dml.insertRestaurantByCuisineType("Portuguese", 1);
            dml.insertRestaurantByCuisineType("Portuguese", 2);
            dml.insertRestaurantByCuisineType("Portuguese", 3);
            dml.insertRestaurantByCuisineType("Italian", 3);
            dml.insertRestaurantByCuisineType("Portuguese", 4);
            dml.insertRestaurantByCuisineType("Turkish", 4);
            dml.insertRestaurantByCuisineType("Indian", 4);
            dml.insertRestaurantByCuisineType("Chinese", 5);
            dml.insertRestaurantByCuisineType("Japanese", 5);
            dml.insertRestaurantByCuisineType("Portuguese", 6);
            dml.insertRestaurantByCuisineType("Japanese", 7);
            dml.insertRestaurantByCuisineType("Italian", 8);
            dml.insertRestaurantByCuisineType("Mexican", 8);
            dml.insertRestaurantByCuisineType("Mexican", 9);
            dml.insertRestaurantByCuisineType("Portuguese", 10);
            dml.insertRestaurantByCuisineType("Italian", 11);
            dml.insertRestaurantByCuisineType("Portuguese", 12);

            // Insert data in restaurant reservations table
            dml.insertRestaurantReservations(1, "João", 234556349, LocalDateTime.now().minusDays(1), LocalDate.parse("2024-11-29"), 4);
            dml.insertRestaurantReservations(1, "Ana", 234556350, LocalDateTime.now().minusDays(2), LocalDate.parse("2024-11-28"), 2);
            dml.insertRestaurantReservations(1, "Carlos", 234556351, LocalDateTime.now().minusDays(3), LocalDate.parse("2024-11-27"), 6);
            dml.insertRestaurantReservations(1, "Maria", 234556352, LocalDateTime.now().minusDays(4), LocalDate.parse("2024-11-26"), 3);
            dml.insertRestaurantReservations(1, "Pedro", 234556353, LocalDateTime.now().minusDays(5), LocalDate.parse("2024-11-25"), 5);
            dml.insertRestaurantReservations(1, "Sofia", 234556354, LocalDateTime.now().minusDays(6), LocalDate.parse("2024-11-24"), 4);

            dml.insertRestaurantReservations(2, "João", 234556355, LocalDateTime.now().minusDays(1), LocalDate.parse("2024-11-29"), 4);
            dml.insertRestaurantReservations(2, "Marta", 234556356, LocalDateTime.now().minusDays(2), LocalDate.parse("2024-11-28"), 2);
            dml.insertRestaurantReservations(2, "Rui", 234556357, LocalDateTime.now().minusDays(3), LocalDate.parse("2024-11-27"), 6);

            dml.insertRestaurantReservations(3, "Beatriz", 234556358, LocalDateTime.now().minusDays(1), LocalDate.parse("2024-11-29"), 4);
            dml.insertRestaurantReservations(3, "Ricardo", 234556359, LocalDateTime.now().minusDays(2), LocalDate.parse("2024-11-28"), 2);

            dml.insertRestaurantReservations(4, "André", 234556361, LocalDateTime.now().minusDays(1), LocalDate.parse("2024-11-29"), 4);
            dml.insertRestaurantReservations(4, "Filipa", 234556362, LocalDateTime.now().minusDays(2), LocalDate.parse("2024-11-28"), 2);
            dml.insertRestaurantReservations(4, "Bruno", 234556363, LocalDateTime.now().minusDays(3), LocalDate.parse("2024-11-27"), 6);

            dml.insertRestaurantReservations(5, "Helena", 234556366, LocalDateTime.now().minusDays(3), LocalDate.parse("2024-11-27"), 6);

            dml.insertRestaurantReservations(6, "Vasco", 234556367, LocalDateTime.now().minusDays(1), LocalDate.parse("2024-11-29"), 4);
            dml.insertRestaurantReservations(6, "Rita", 234556368, LocalDateTime.now().minusDays(2), LocalDate.parse("2024-11-28"), 2);
            dml.insertRestaurantReservations(6, "Paulo", 234556369, LocalDateTime.now().minusDays(3), LocalDate.parse("2024-11-27"), 6);
            dml.insertRestaurantReservations(6, "Clara", 234556370, LocalDateTime.now().minusDays(4), LocalDate.parse("2024-11-26"), 3);

            dml.insertRestaurantReservations(7, "Mariana", 234556370, LocalDateTime.now().minusDays(1), LocalDate.parse("2024-11-29"), 4);
            dml.insertRestaurantReservations(7, "Eduardo", 234556371, LocalDateTime.now().minusDays(2), LocalDate.parse("2024-11-28"), 2);
            dml.insertRestaurantReservations(7, "Joana", 234556372, LocalDateTime.now().minusDays(3), LocalDate.parse("2024-11-27"), 6);

            dml.insertRestaurantReservations(8, "Raquel", 234556373, LocalDateTime.now().minusDays(1), LocalDate.parse("2024-11-29"), 4);
            dml.insertRestaurantReservations(8, "Susana", 234556375, LocalDateTime.now().minusDays(3), LocalDate.parse("2024-11-27"), 6);

            dml.insertRestaurantReservations(9, "Alexandre", 234556376, LocalDateTime.now().minusDays(1), LocalDate.parse("2024-11-29"), 4);
            dml.insertRestaurantReservations(9, "Carla", 234556377, LocalDateTime.now().minusDays(2), LocalDate.parse("2024-11-28"), 2);
            dml.insertRestaurantReservations(9, "Fernando", 234556378, LocalDateTime.now().minusDays(3), LocalDate.parse("2024-11-27"), 6);

            // No reservations for restaurant 10

            dml.insertRestaurantReservations(11, "Cristina", 234556379, LocalDateTime.now().minusDays(1), LocalDate.parse("2024-11-29"), 4);
            dml.insertRestaurantReservations(11, "Vítor", 234556380, LocalDateTime.now().minusDays(2), LocalDate.parse("2024-11-28"), 2);
            dml.insertRestaurantReservations(11, "Manuela", 234556381, LocalDateTime.now().minusDays(3), LocalDate.parse("2024-11-27"), 6);

            dml.insertRestaurantReservations(12, "Filipe", 234556382, LocalDateTime.now().minusDays(1), LocalDate.parse("2024-11-29"), 4);
            dml.insertRestaurantReservations(12, "Célia", 234556383, LocalDateTime.now().minusDays(2), LocalDate.parse("2024-11-28"), 2);
            dml.insertRestaurantReservations(12, "Gustavo", 234556384, LocalDateTime.now().minusDays(3), LocalDate.parse("2024-11-27"), 6);

            // Insert data in restaurant reviews table
            dml.insertRestaurantReviews(1, "Jorge", LocalDateTime.now().minusDays(1), "Great food and service", 5);
            dml.insertRestaurantReviews(1, "Ana", LocalDateTime.now().minusDays(2), "Good atmosphere", 4);
            dml.insertRestaurantReviews(1, "Carlos", LocalDateTime.now().minusDays(3), "Average experience", 3);
            dml.insertRestaurantReviews(1, "Maria", LocalDateTime.now().minusDays(4), "Not great", 2);
            dml.insertRestaurantReviews(1, "Pedro", LocalDateTime.now().minusDays(5), "Terrible service", 1);
            dml.insertRestaurantReviews(1, "Sofia", LocalDateTime.now().minusDays(6), "Excellent food", 5);
            dml.insertRestaurantReviews(1, "Luis", LocalDateTime.now().minusDays(7), "Will come again", 4);

            dml.insertRestaurantReviews(2, "João", LocalDateTime.now().minusDays(1), "Delicious food", 5);
            dml.insertRestaurantReviews(2, "Marta", LocalDateTime.now().minusDays(2), "Nice place", 4);
            dml.insertRestaurantReviews(2, "Rui", LocalDateTime.now().minusDays(3), "Okay experience", 3);
            dml.insertRestaurantReviews(2, "Inês", LocalDateTime.now().minusDays(4), "Could be better", 2);
            dml.insertRestaurantReviews(2, "Tiago", LocalDateTime.now().minusDays(5), "Not good", 1);

            dml.insertRestaurantReviews(3, "Beatriz", LocalDateTime.now().minusDays(1), "Loved the pizza", 5);
            dml.insertRestaurantReviews(3, "Ricardo", LocalDateTime.now().minusDays(2), "Good service", 4);
            dml.insertRestaurantReviews(3, "Sara", LocalDateTime.now().minusDays(3), "Average taste", 3);
            dml.insertRestaurantReviews(3, "Miguel", LocalDateTime.now().minusDays(4), "Not satisfied", 2);
            dml.insertRestaurantReviews(3, "Patrícia", LocalDateTime.now().minusDays(5), "Terrible experience", 1);

            dml.insertRestaurantReviews(4, "André", LocalDateTime.now().minusDays(1), "Great kebabs", 5);
            dml.insertRestaurantReviews(4, "Filipa", LocalDateTime.now().minusDays(2), "Nice flavors", 4);
            dml.insertRestaurantReviews(4, "Bruno", LocalDateTime.now().minusDays(3), "Average food", 3);
            dml.insertRestaurantReviews(4, "Catarina", LocalDateTime.now().minusDays(4), "Not impressed", 2);
            dml.insertRestaurantReviews(4, "Diogo", LocalDateTime.now().minusDays(5), "Bad service", 1);

            dml.insertRestaurantReviews(5, "Leonor", LocalDateTime.now().minusDays(1), "Amazing sushi", 5);
            dml.insertRestaurantReviews(5, "Gonçalo", LocalDateTime.now().minusDays(2), "Good quality", 4);
            dml.insertRestaurantReviews(5, "Helena", LocalDateTime.now().minusDays(3), "Average experience", 3);
            dml.insertRestaurantReviews(5, "Nuno", LocalDateTime.now().minusDays(4), "Not great", 2);
            dml.insertRestaurantReviews(5, "Teresa", LocalDateTime.now().minusDays(5), "Terrible", 1);

            dml.insertRestaurantReviews(6, "Vasco", LocalDateTime.now().minusDays(1), "Great seafood", 5);
            dml.insertRestaurantReviews(6, "Rita", LocalDateTime.now().minusDays(2), "Good service", 4);
            dml.insertRestaurantReviews(6, "Paulo", LocalDateTime.now().minusDays(3), "Average taste", 3);
            dml.insertRestaurantReviews(6, "Clara", LocalDateTime.now().minusDays(4), "Not satisfied", 2);
            dml.insertRestaurantReviews(6, "Fábio", LocalDateTime.now().minusDays(5), "Terrible experience", 1);

            dml.insertRestaurantReviews(7, "Mariana", LocalDateTime.now().minusDays(1), "Loved the sushi", 5);
            dml.insertRestaurantReviews(7, "Eduardo", LocalDateTime.now().minusDays(2), "Good quality", 4);
            dml.insertRestaurantReviews(7, "Joana", LocalDateTime.now().minusDays(3), "Average experience", 3);
            dml.insertRestaurantReviews(7, "Francisco", LocalDateTime.now().minusDays(4), "Not great", 2);
            dml.insertRestaurantReviews(7, "Isabel", LocalDateTime.now().minusDays(5), "Terrible", 1);

            dml.insertRestaurantReviews(8, "Raquel", LocalDateTime.now().minusDays(1), "Great pasta", 5);
            dml.insertRestaurantReviews(8, "Artur", LocalDateTime.now().minusDays(2), "Nice tacos", 4);
            dml.insertRestaurantReviews(8, "Susana", LocalDateTime.now().minusDays(3), "Average taste", 3);
            dml.insertRestaurantReviews(8, "Hugo", LocalDateTime.now().minusDays(4), "Not impressed", 2);
            dml.insertRestaurantReviews(8, "Daniela", LocalDateTime.now().minusDays(5), "Bad service", 1);

            dml.insertRestaurantReviews(9, "Alexandre", LocalDateTime.now().minusDays(1), "Amazing tacos", 5);
            dml.insertRestaurantReviews(9, "Carla", LocalDateTime.now().minusDays(2), "Good flavors", 4);
            dml.insertRestaurantReviews(9, "Fernando", LocalDateTime.now().minusDays(3), "Average experience", 3);
            dml.insertRestaurantReviews(9, "Diana", LocalDateTime.now().minusDays(4), "Not great", 2);
            dml.insertRestaurantReviews(9, "Henrique", LocalDateTime.now().minusDays(5), "Terrible", 1);

            dml.insertRestaurantReviews(10, "Mário", LocalDateTime.now().minusDays(1), "Loved the bacalhau", 5);
            dml.insertRestaurantReviews(10, "Lúcia", LocalDateTime.now().minusDays(2), "Good quality", 4);
            dml.insertRestaurantReviews(10, "António", LocalDateTime.now().minusDays(3), "Average taste", 3);
            dml.insertRestaurantReviews(10, "Sílvia", LocalDateTime.now().minusDays(4), "Not satisfied", 2);
            dml.insertRestaurantReviews(10, "Júlio", LocalDateTime.now().minusDays(5), "Terrible experience", 1);

            dml.insertRestaurantReviews(11, "Cristina", LocalDateTime.now().minusDays(1), "Great pizza", 5);
            dml.insertRestaurantReviews(11, "Vítor", LocalDateTime.now().minusDays(2), "Nice place", 4);
            dml.insertRestaurantReviews(11, "Manuela", LocalDateTime.now().minusDays(3), "Average experience", 3);
            dml.insertRestaurantReviews(11, "Rafael", LocalDateTime.now().minusDays(4), "Could be better", 2);
            dml.insertRestaurantReviews(11, "Adriana", LocalDateTime.now().minusDays(5), "Not good", 1);

            dml.insertRestaurantReviews(12, "Filipe", LocalDateTime.now().minusDays(1), "Delicious food", 5);
            dml.insertRestaurantReviews(12, "Célia", LocalDateTime.now().minusDays(2), "Good service", 4);
            dml.insertRestaurantReviews(12, "Gustavo", LocalDateTime.now().minusDays(3), "Okay experience", 3);
            dml.insertRestaurantReviews(12, "Helder", LocalDateTime.now().minusDays(4), "Could be better", 2);
            dml.insertRestaurantReviews(12, "Mafalda", LocalDateTime.now().minusDays(5), "Not good", 1);

            // Insert data in restaurant menus table
            dml.insertRestaurantMenus(1, Map.of("Bacalhau à Brás", 12.5, "Arroz de Marisco", 15.0, "Bife à Portuguesa", 10.0, "Francesinha", 13.5, "Caldo Verde", 5.0, "Polvo à Lagareiro", 18.0), List.of("Bacalhau à Brás", "Arroz de Marisco"));
            dml.insertRestaurantMenus(2, Map.of("Secretos de Porco Preto", 14.5, "Frango no Churrasco", 10.0, "Picanha", 15.0, "Naco de Vitela", 12.5, "Feijoada", 11.0, "Cozido à Portuguesa", 13.0), List.of("Secretos de Porco Preto"));
            dml.insertRestaurantMenus(3, Map.of("Pizza Margherita", 8.5, "Pizza Diavola", 9.0, "Pizza Quattro Stagioni", 10.0, "Pizza Quattro Formaggi", 11.0, "Calzone", 12.0, "Lasagna", 13.0), List.of("Pizza Margherita", "Pizza Diavola"));
            dml.insertRestaurantMenus(4, Map.of("Kebab de Frango", 7.5, "Kebab de Borrego", 8.0, "Kebab Vegetariano", 6.5, "Chamuças", 5.0, "Caril de Frango", 9.0, "Caril de Borrego", 10.0), List.of("Kebab de Frango", "Kebab de Borrego"));
            dml.insertRestaurantMenus(5, Map.of("Sushi de Salmão", 12.0, "Sushi de Atum", 13.0, "Sashimi", 14.0, "Tempura", 10.0, "Ramen", 11.0, "Gyoza", 8.0), List.of("Sushi de Salmão", "Sushi de Atum"));
            dml.insertRestaurantMenus(6, Map.of("Amêijoas à Bulhão Pato", 15.0, "Camarão à Guilho", 14.0, "Sapateira Recheada", 18.0, "Arroz de Marisco", 20.0, "Lavagante Grelhado", 25.0, "Ostras", 22.0), List.of("Amêijoas à Bulhão Pato", "Camarão à Guilho"));
            dml.insertRestaurantMenus(7, Map.of("Sushi de Salmão", 12.0, "Sushi de Atum", 13.0, "Sashimi", 14.0, "Tempura", 10.0, "Ramen", 11.0, "Gyoza", 8.0), List.of("Sushi de Salmão", "Sushi de Atum"));
            dml.insertRestaurantMenus(8, Map.of("Spaghetti Carbonara", 9.0, "Fettuccine Alfredo", 10.0, "Tacos de Carne", 8.0, "Tacos de Frango", 7.5, "Burritos", 9.5, "Quesadillas", 8.5), List.of("Spaghetti Carbonara", "Tacos de Carne"));
            dml.insertRestaurantMenus(9, Map.of("Taco de Carne", 3.0, "Taco de Frango", 3.5, "Taco Vegetariano", 3.0, "Burrito de Carne", 6.0, "Burrito de Frango", 6.5, "Quesadilla", 5.0), List.of("Taco de Carne", "Taco de Frango"));
            dml.insertRestaurantMenus(10, Map.of("Bacalhau à Brás", 12.5, "Bacalhau com Natas", 13.0, "Bacalhau à Lagareiro", 14.0, "Bacalhau à Gomes de Sá", 15.0, "Bacalhau Espiritual", 16.0, "Bacalhau com Broa", 17.0), List.of("Bacalhau à Brás", "Bacalhau com Natas"));
            dml.insertRestaurantMenus(11, Map.of("Pizza Margherita", 8.5, "Pizza Diavola", 9.0, "Pizza Quattro Stagioni", 10.0, "Pizza Quattro Formaggi", 11.0, "Calzone", 12.0, "Lasagna", 13.0), List.of("Pizza Margherita", "Pizza Diavola"));
            dml.insertRestaurantMenus(12, Map.of("Frango no Churrasco", 10.0, "Costeletas de Borrego", 12.0, "Entrecosto", 11.0, "Picanha", 15.0, "Alheira", 9.0, "Chouriço Assado", 8.0), List.of("Frango no Churrasco", "Costeletas de Borrego"));

            // Update data
            dml.addDishToRestaurantMenu(4, "Kebab do Chef", 9.0);
            dml.addPopularDishToRestaurantMenu(4, "Kebab de Borrego");
            dml.addCuisineTypeToRestaurant(4, "Kebabs do António", "Lebanese");
            dml.removeDishFromRestaurantMenu(4, "Chamuças");
            dml.alterDishPrice(4, "Kebab de Frango", 8.5);

            // Delete data
            dml.deleteAllPopularDishesFromRestaurantMenu(12);
            dml.deleteDishFromRestaurantMenu(12, "Alheira");
            dml.deleteReviewsByAuthor(12, "Gustavo");
            dml.deleteReservationsByDate(2, LocalDate.parse("2024-11-28"));
            dml.deleteReviewsByRestaurant(12);
        }
    }
}