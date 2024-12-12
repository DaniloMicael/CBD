package pt.ua.cbd.lab3.ex4;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.datastax.oss.driver.api.core.CqlSession;

public class DatabaseDML {
    private final CqlSession session;
    
    public DatabaseDML(CqlSession session) {
        this.session = session;
    }

    // ======================== INSERTS ======================== //

    public void insertRestaurant(int id, String name, String address, int phone, Set<String> cuisineType) {
        session.execute("INSERT INTO Restaurant (id, name, address, phone, cuisine_type) VALUES (?, ?, ?, ?, ?)", id, name, address, phone, cuisineType);
    }

    public void insertRestaurantByCuisineType(String cuisineType, int restaurantId) {
        session.execute("INSERT INTO RestaurantByCuisineType (cuisine_type, restaurant_id) VALUES (?, ?)", cuisineType, restaurantId);
    }

    public void insertRestaurantReservations(int restaurantId, String reservationName, int reservationPhone, LocalDateTime reservationTimestamp, LocalDate reservationDate, int reservationPartySize) {
        Instant reservationInstant = reservationTimestamp.atZone(ZoneId.systemDefault()).toInstant();
        session.execute("INSERT INTO RestaurantReservations (restaurant_id, reservation_name, reservation_phone, reservation_timestamp, reservation_date, reservation_party_size) VALUES (?, ?, ?, ?, ?, ?)", restaurantId, reservationName, reservationPhone, reservationInstant, reservationDate, reservationPartySize);
    }

    public void insertRestaurantReviews(int restaurantId, String reviewAuthor, LocalDateTime reviewTimestamp, String review, int rating) {
        Instant reviewInstant = reviewTimestamp.atZone(ZoneId.systemDefault()).toInstant();
        session.execute("INSERT INTO RestaurantReviews (restaurant_id, review_author, review_timestamp, review, rating) VALUES (?, ?, ?, ?, ?)", restaurantId, reviewAuthor, reviewInstant, review, rating);
    }

    public void insertRestaurantMenus(int restaurantId, Map<String, Double> dishes, List<String> popularDishes) {
        session.execute("INSERT INTO RestaurantMenus (restaurant_id, dishes, popular_dishes) VALUES (?, ?, ?)", restaurantId, dishes, popularDishes);
    }

    // ======================== UPDATES (5) ======================== //

    // Add a dish to a restaurant's menu    
    public void addDishToRestaurantMenu(int restaurantId, String dish, double price) {
        session.execute("UPDATE RestaurantMenus SET dishes[?] = ? WHERE restaurant_id = ?", dish, price, restaurantId);
    }

    // Add a popular dish to a restaurant's menu
    public void addPopularDishToRestaurantMenu(int restaurantId, String popularDish) {
        session.execute("UPDATE RestaurantMenus SET popular_dishes = popular_dishes + ['" + popularDish + "'] WHERE restaurant_id = ?", restaurantId);
    }

    // Add a new cuisine type to a restaurant
    public void addCuisineTypeToRestaurant(int restaurantId, String restaurantName, String cuisineType) {
        session.execute("UPDATE Restaurant SET cuisine_type = cuisine_type + {'" + cuisineType + "'} WHERE id = ? AND name = ?", restaurantId, restaurantName);
    }

    // Remove a popular dish from a restaurant's menu
    public void removeDishFromRestaurantMenu(int restaurantId, String dish) {
        session.execute("UPDATE RestaurantMenus SET popular_dishes = popular_dishes - ['" + dish + "'] WHERE restaurant_id = ?", restaurantId);
    }

    // Alter the price of a dish in a restaurant's menu
    public void alterDishPrice(int restaurantId, String dish, double newPrice) {
        session.execute("UPDATE RestaurantMenus SET dishes[?] = ? WHERE restaurant_id = ?", dish, newPrice, restaurantId);
    }

    // ======================== DELETES (5) ======================== //

    // Delete all the popular dishes from a restaurant's menu
    public void deleteAllPopularDishesFromRestaurantMenu(int restaurantId) {
        session.execute("DELETE popular_dishes FROM RestaurantMenus WHERE restaurant_id = ?", restaurantId);
    }
    
    // Delete a specific dish from a restaurant's menu
    public void deleteDishFromRestaurantMenu(int restaurantId, String dish) {
        session.execute("DELETE dishes[?] FROM RestaurantMenus WHERE restaurant_id = ?", dish, restaurantId);
    }

    // Delete all the reviews of a specific author
    public void deleteReviewsByAuthor(int restaurantId, String reviewAuthor) {
        session.execute("DELETE FROM RestaurantReviews WHERE restaurant_id = ? AND review_author = ?", restaurantId, reviewAuthor);
    }

    // Delete all the reservations for a specific restaurant on a specific date
    public void deleteReservationsByDate(int restaurantId, LocalDate reservationDate) {
        session.execute("DELETE FROM RestaurantReservations WHERE restaurant_id = ? AND reservation_date = ?", restaurantId, reservationDate);
    }

    // Delete all the reviews for a specific restaurant
    public void deleteReviewsByRestaurant(int restaurantId) {
        session.execute("DELETE FROM RestaurantReviews WHERE restaurant_id = ?", restaurantId);
    }
}
