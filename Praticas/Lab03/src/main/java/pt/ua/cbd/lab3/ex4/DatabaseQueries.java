package pt.ua.cbd.lab3.ex4;

import java.time.LocalDate;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;


public class DatabaseQueries {
    private final CqlSession session;

    public DatabaseQueries(CqlSession session) {
        this.session = session;
    }

    // Last reservation for a specific restaurant - Query1
    public ResultSet getLastReservation(int restaurantId) {
        String query = "SELECT * FROM RestaurantReservations WHERE restaurant_id = ? LIMIT 1";
        return this.session.execute(query, restaurantId);
    }

    // Number of reviews for each restaurant - Query2
    public ResultSet getRestaurantsReviews() {
        String query = "SELECT restaurant_id, COUNT(restaurant_id) AS review_count FROM RestaurantReviews GROUP BY restaurant_id";
        return this.session.execute(query);
    }

    // All the reservations for a specific restaurant on a specific date - Query3
    public ResultSet getReservationsByDate(int restaurantId, String reservationDate) {
        LocalDate date = LocalDate.parse(reservationDate);
        String query = "SELECT * FROM RestaurantReservations WHERE restaurant_id = ? AND reservation_date = ?";
        return this.session.execute(query, restaurantId, date);
    }

    // Average rating for a specific restaurant - Query4
    public ResultSet getAverageRatingByRestaurant(int restaurantId) {
        String query = "SELECT CAST(AVG(rating) AS DOUBLE) AS average_rating FROM RestaurantReviews WHERE restaurant_id = ?";
        return this.session.execute(query, restaurantId);
    }
    

    // All the restaurants that serve a specific cuisine type - Query5
    public ResultSet getRestaurantWithCuisineType(String cuisineType) {
        String query = "SELECT * FROM Restaurant WHERE cuisine_type CONTAINS ?";
        return this.session.execute(query, cuisineType);
    }

    // All the cuisine types and the number of restaurants that serve them - Query6
    public ResultSet getCuisineTypeCount() {
        String query = "SELECT cuisine_type, COUNT(restaurant_id) AS restaurant_count FROM RestaurantByCuisineType GROUP BY cuisine_type";
        return this.session.execute(query);
    }

    // Number of reservations for each restaurant - Query7
    public ResultSet getTotalReservationsByRestaurant() {
        String query = "SELECT restaurant_id, COUNT(*) AS total_reservations FROM RestaurantReservations GROUP BY restaurant_id";
        return this.session.execute(query);
    }

    // Top popular dishes for a specific restaurant - Query8
    public ResultSet getTopPopularDishes(int restaurantId) {
        String query = "SELECT popular_dishes FROM RestaurantMenus WHERE restaurant_id = ?";
        return this.session.execute(query, restaurantId);
    }

    // Restaurants in a specific address - Query9
    public ResultSet getRestaurantsByAddress(String address) {
        String query = "SELECT * FROM Restaurant WHERE address = ?";
        return this.session.execute(query, address);
    }

    // All the restaurants that serve a specific dish - Query10
    public ResultSet getRestaurantsByDish(String dishName) {
        String query = "SELECT * FROM RestaurantMenus WHERE dishes CONTAINS KEY ?";
        return this.session.execute(query, dishName);
    }

    // Average price of dishes for a specific restaurant - Query11 (UDF)
    public ResultSet getAveragePriceOfDishes(int restaurantId) {
        String query = "SELECT calculate_average_price(dishes) AS average_price FROM RestaurantMenus WHERE restaurant_id = ?";
        return this.session.execute(query, restaurantId);
    }

    // Total price of all dishes for a specific restaurant - Query12
    public ResultSet getTotalPriceOfDishes(int restaurantId) {
        String query = "SELECT total_price_from_map(dishes) AS total_price FROM RestaurantMenus WHERE restaurant_id = ?";
        return this.session.execute(query, restaurantId);
    }
    
    
}
