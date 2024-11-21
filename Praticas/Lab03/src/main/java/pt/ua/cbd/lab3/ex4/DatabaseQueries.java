package pt.ua.cbd.lab3.ex4;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;


public class DatabaseQueries {
    private final CqlSession session;

    public DatabaseQueries(CqlSession session) {
        this.session = session;
    }

    // All the restaurants that serve a specific cuisine type
    public ResultSet getRestaurantWithCuisineType(String cuisineType) {
        String query = "SELECT * FROM Restaurant WHERE cuisine_type CONTAINS ?";
        return this.session.execute(query, cuisineType);
    }

    // All the cuisine types and the number of restaurants that serve them
    public ResultSet getCuisineTypeCount() {
        String query = "SELECT cuisine_type, COUNT(restaurant_id) AS restaurant_count FROM RestaurantByCuisineType GROUP BY cuisine_type";
        return this.session.execute(query);
    }
}
