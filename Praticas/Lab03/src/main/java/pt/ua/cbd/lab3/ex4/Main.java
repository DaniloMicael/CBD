package pt.ua.cbd.lab3.ex4;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;

public class Main {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder().build()) {
            DatabaseDDL ddl = new DatabaseDDL(session);
            DatabaseQueries queries = new DatabaseQueries(session);

            // ======================== DDL ======================== //
            ddl.createKeyspace("Restaurants");

            
        }
    }
}