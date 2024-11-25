package pt.ua.cbd.lab3.ex4;

import com.datastax.oss.driver.api.core.CqlSession;

public class DatabaseDDL {
    private final CqlSession session;

    public DatabaseDDL(CqlSession session) {
        this.session = session;
    }

    // ======================== KEYSPACE ======================== //

    public void createKeyspace(String keyspaceName) {
        session.execute("CREATE KEYSPACE IF NOT EXISTS " + keyspaceName + " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }");
        session.execute("USE " + keyspaceName);
    }

    // ======================== TABLES (5) ======================== //

    public void createRestaurantTable() {
        session.execute("CREATE TABLE IF NOT EXISTS Restaurant (" +
                        "id INT," +
                        "name TEXT," + 
                        "address TEXT," +
                        "phone INT," +
                        "cuisine_type SET<TEXT>," +
                        "PRIMARY KEY (id, name)" +
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
                        "PRIMARY KEY (restaurant_id, reservation_date, reservation_name)" +
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
                        ") WITH CLUSTERING ORDER BY (review_author ASC, review_timestamp DESC)"
        );
    }

    public void createRestaurantMenusTable() {
        session.execute("CREATE TABLE IF NOT EXISTS RestaurantMenus (" +
                        "restaurant_id INT," +
                        "dishes MAP<TEXT, DOUBLE>," +
                        "popular_dishes LIST<TEXT>," +
                        "PRIMARY KEY (restaurant_id)" +
                        ")"
        );
    }

    // ======================== INDEXES (3) ======================== //

    public void createIndexOnRestaurantCuisineType() {
        session.execute("CREATE INDEX IF NOT EXISTS idx_cuisine_type ON Restaurant (cuisine_type)");
    }

    public void createIndexOnDishKey() {
        session.execute("CREATE INDEX IF NOT EXISTS idx_dish_key ON RestaurantMenus (KEYS(dishes))");
    }

    public void createIndexOnRestaurantAddress() {
        session.execute("CREATE INDEX IF NOT EXISTS idx_restaurant_address ON Restaurant (address)");
    }

    // ======================== UDF ======================== //

    // UDF to calculate the average price of dishes
    public void createUDF() {
        session.execute("CREATE FUNCTION IF NOT EXISTS calculate_average_price(dishes map<TEXT, double>) " +
                        "RETURNS NULL ON NULL INPUT " +
                        "RETURNS DOUBLE " +
                        "LANGUAGE JAVA " +
                        "AS $$ " +
                        "double sum = 0; " +
                        "int count = 0; " +
                        "for (double price : dishes.values()) { " +
                        "sum += price; " +
                        "count++; " +
                        "} " +
                        "return sum / count; " +
                        "$$"
        );
    }

    // ======================== UDA ======================== //

    // UDA to calculate the total sum of a list of prices in a restaurant
    public void createSumMapUDF() {
        session.execute("CREATE FUNCTION IF NOT EXISTS sum_map(dishes map<TEXT, double>) " +
                        "RETURNS NULL ON NULL INPUT " +
                        "RETURNS double " +
                        "LANGUAGE JAVA " +
                        "AS $$ " +
                        "double sum = 0; " +
                        "for (double price : dishes.values()) { " +
                        "    sum += price; " +
                        "} " +
                        "return sum; " +
                        "$$");
    }

    public void createStateFunction() {
        session.execute("CREATE FUNCTION IF NOT EXISTS sum_state(state double, dishes map<TEXT, double>) " +
                        "RETURNS NULL ON NULL INPUT " +
                        "RETURNS double " +
                        "LANGUAGE JAVA " +
                        "AS $$ " +
                        "double sum = state; " +
                        "for (double price : dishes.values()) { " +
                        "    sum += price; " +
                        "} " +
                        "return sum; " +
                        "$$");
    }

    public void createSumPricesUDA() {
        session.execute("CREATE AGGREGATE IF NOT EXISTS total_price_from_map(map<TEXT, double>) " +
                        "SFUNC sum_state " +
                        "STYPE double " +
                        "INITCOND 0.0");
    }
        
}
