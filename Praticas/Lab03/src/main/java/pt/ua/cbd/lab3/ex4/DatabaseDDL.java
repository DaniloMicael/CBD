package pt.ua.cbd.lab3.ex4;

import com.datastax.oss.driver.api.core.CqlSession;

public class DatabaseDDL {
    private final CqlSession session;

    public DatabaseDDL(CqlSession session) {
        this.session = session;
    }

    public void createKeyspace(String keyspaceName) {
        session.execute("CREATE KEYSPACE IF NOT EXISTS " + keyspaceName + " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }");
        session.execute("USE " + keyspaceName);
    }

    public void createRestaurantTable() {
        session.execute("CREATE TABLE IF NOT EXISTS Restaurant (" +
                        "id INT," +
                        "name TEXT," + 
                        "address TEXT," +
                        "phone INT," +
                        "cuisine_type SET<TEXT>," +
                        "PRIMARY KEY (id)" +
                        ")"
        );
    }

    public void createRestaurantByCuisineTypeTable() {
        session.execute("CREATE TABLE IF NOT EXISTS RestaurantByCuisineType (" +
                        "cuisine_type TEXT," +
                        "restaurant_id INT," +
                        "PRIMARY KEY (cuisine_type, restaurant_id)" +
                        ")"
        );
    }

    public void createRestaurantReservationsTable() {
        session.execute("CREATE TABLE IF NOT EXISTS RestaurantReservations (" +
                        "restaurant_id INT," +
                        "reservation_name TEXT," +
                        "reservation_phone INT," +
                        "reservation_timestamp TIMESTAMP," +
                        "reservation_date DATE," +
                        "reservation_party_size INT," +
                        "PRIMARY KEY (restaurant_id, reservation_name)" +
                        ") WITH CLUSTERING ORDER BY (reservation_date ASC)"
        );
    }

    public void createRestaurantReviewsTable() {
        session.execute("CREATE TABLE IF NOT EXISTS RestaurantReviews (" +
                        "restaurant_id INT," +
                        "review_author TEXT," +
                        "review_timestamp TIMESTAMP," +
                        "review TEXT," +
                        "rating INT," +
                        "PRIMARY KEY (restaurant_id, review_author, review_timestamp)" +
                        ") WITH CLUSTERING ORDER BY (review_timestamp DESC)"
        );
    }

    public void createRestaurantMenusTable() {
        session.execute("CREATE TABLE IF NOT EXISTS RestaurantMenus (" +
                        "restaurant_id INT," +
                        "dishes MAP<TEXT, FLOAT>," +
                        "popular_dishes LIST<TEXT>," +
                        "PRIMARY KEY (restaurant_id)" +
                        ")"
        );
    }

    public void createIndexOnRestaurantCuisineType() {
        session.execute("CREATE INDEX IF NOT EXISTS idx_cuisine_type ON Restaurant (cuisine_type)");
    }

    public void createIndexOnRestaurantReservationDate() {
        session.execute("CREATE INDEX IF NOT EXISTS idx_reservation_date ON RestaurantReservations (reservation_date)");
    }
}
