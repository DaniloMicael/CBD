package pt.ua.cbd.lab4.ex4;

import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Record;

public class Main {
    private static String filePath = "resources/listings.csv";
    private static String uri = "bolt://localhost:7687";

    public static void main(String... args) {
        Driver driver = GraphDatabase.driver(uri);

        // Load dataset to Neo4j
        loadDataset(driver);

        // Queries
        query1(driver);
        query2(driver);
        query3(driver);
        query4(driver);
        query5(driver);
        query6(driver);
        query7(driver);
        query8(driver);
        query9(driver);
        query10(driver);

        // Close driver
        driver.close();
    }

    private static void loadDataset(Driver driver) {
        try (Session session = driver.session()) {
            session.run("LOAD CSV WITH HEADERS FROM 'file:///" + filePath + "' AS row " +
                    "MERGE (host: Host {host_id: row.host_id}) " +
                    "ON CREATE SET host.host_name = row.host_name " +
                    "MERGE (neighbourhood:Neighbourhood {name: row.neighbourhood}) " +
                    "ON CREATE SET neighbourhood.group = row.neighbourhood_group " +
                    "WITH row, host, neighbourhood " +
                    "WHERE row.license IS NOT NULL " +
                    "MERGE (license: License {license_id: row.license}) " +
                    "MERGE (listing: Listing {id: row.id}) " +
                    "ON CREATE SET " +
                    "listing.name = row.name, " +
                    "listing.room_type = row.room_type, " +
                    "listing.price = toFloat(row.price), " +
                    "listing.minimum_nights = toInteger(row.minimum_nights), " +
                    "listing.number_of_reviews = toInteger(row.number_of_reviews), " +
                    "listing.last_review = row.last_review, " +
                    "listing.reviews_per_month = toFloat(row.reviews_per_month), " +
                    "listing.availability_365 = toInteger(row.availability_365) " +
                    "MERGE (host)-[:HOSTS]->(listing) " +
                    "MERGE (listing)-[:LOCATED_IN]->(neighbourhood) " +
                    "MERGE (listing)-[:HAS_LICENSE]->(license) "
            );

            System.out.println("Dataset loaded successfully");
        } catch (Exception e) {
            System.out.println("Error loading dataset: " + e.getMessage());
        }
    }

    // Query 1: Find all listings located in the neighbourhood of Campanhã
    public static void query1(Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood {name: 'Campanhã'}) " +
                    "RETURN listing.id AS ListingID, listing.name AS ListingName, listing.price AS Price, neighbourhood.name AS Neighbourhood"
            );

            System.out.println("\nListings in Campanhã:");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println("Listing ID: " + record.get("ListingID").asString() +
                        ", Name: " + record.get("ListingName").asString() +
                        ", Price: " + record.get("Price").asDouble() +
                        ", Neighbourhood: " + record.get("Neighbourhood").asString());
            }
        } catch (Exception e) {
            System.out.println("Error executing query 1: " + e.getMessage());
        }
    }

    // Query 2: Find the 3 neighbourhoods with the highest number of listings
    public static void query2(Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood) " +
                    "RETURN neighbourhood.name AS Neighbourhood, COUNT(listing) AS NumberOfListings " +
                    "ORDER BY NumberOfListings DESC " +
                    "LIMIT 3"
            );

            System.out.println("\nTop 3 neighbourhoods with the highest number of listings:");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println("Neighbourhood: " + record.get("Neighbourhood").asString() +
                        ", Number of Listings: " + record.get("NumberOfListings").asInt());
            }
        } catch (Exception e) {
            System.out.println("Error executing query 2: " + e.getMessage());
        }
    }

    // Query 3: Find the cheapest listings in each neighbourhood
    public static void query3(Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood) " +
                    "WITH neighbourhood, MIN(listing.price) AS minPrice " +
                    "MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood) " +
                    "WHERE listing.price = minPrice " +
                    "RETURN neighbourhood.name AS Neighbourhood, listing.id AS ListingID, listing.name AS ListingName, listing.price AS Price"
            );

            System.out.println("\nCheapest listings in each neighbourhood:");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println("Neighbourhood: " + record.get("Neighbourhood").asString() +
                        ", Listing ID: " + record.get("ListingID").asString() +
                        ", Name: " + record.get("ListingName").asString() +
                        ", Price: " + record.get("Price").asDouble());
            }
        } catch (Exception e) {
            System.out.println("Error executing query 3: " + e.getMessage());
        }
    }

    // Query 4: Find the listings in the neighbourhood of Lordelo do Ouro e Massarelos that have a license and price above 100
    public static void query4(Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood {name: 'Lordelo do Ouro e Massarelos'}) " +
                    "MATCH (listing)-[:HAS_LICENSE]->(license:License) " +
                    "WHERE listing.price > 100 AND license IS NOT NULL " +
                    "RETURN listing.id AS ListingID, listing.name AS ListingName, listing.price AS Price, neighbourhood.name AS Neighbourhood, license.license_id AS LicenseID"
            );

            System.out.println("\nListings in Lordelo do Ouro e Massarelos with a license:");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println("Listing ID: " + record.get("ListingID").asString() +
                        ", Name: " + record.get("ListingName").asString() +
                        ", Price: " + record.get("Price").asDouble() +
                        ", Neighbourhood: " + record.get("Neighbourhood").asString() +
                        ", License ID: " + record.get("LicenseID").asString());
            }
        } catch (Exception e) {
            System.out.println("Error executing query 4: " + e.getMessage());
        }
    }

    // Query 5: Find the average price of listings by room type
    public static void query5(Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (listing:Listing) " +
                    "RETURN listing.room_type AS RoomType, round(avg(listing.price), 2) AS AveragePrice "
            );

            System.out.println("\nAverage price of listings by room type:");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println("Room Type: " + record.get("RoomType").asString() +
                        ", Average Price: " + record.get("AveragePrice").asDouble());
            }
        } catch (Exception e) {
            System.out.println("Error executing query 5: " + e.getMessage());
        }
    }

    // Query 6: Find the host with the highest number of listings
    public static void query6(Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (host:Host)-[:HOSTS]->(listing:Listing) " +
                    "RETURN host.host_id AS HostID, host.host_name AS HostName, COUNT(listing) AS NumberOfListings " +
                    "ORDER BY NumberOfListings DESC " +
                    "LIMIT 1"
            );

            System.out.println("\nHost with the highest number of listings:");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println("Host ID: " + record.get("HostID").asString() +
                        ", Name: " + record.get("HostName").asString() +
                        ", Number of Listings: " + record.get("NumberOfListings").asInt());
            }
        } catch (Exception e) {
            System.out.println("Error executing query 6: " + e.getMessage());
        }
    }

    // Query 7: Find the listing with the highest number of reviews that is located in the neighbourhood of Anta e Guetim and is available 365 days a year
    public static void query7(Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood {name: 'Anta e Guetim'}) " +
                    "WHERE listing.availability_365 = 365 " +
                    "RETURN listing.id AS ListingID, listing.name AS ListingName, listing.number_of_reviews AS NumberOfReviews " +
                    "ORDER BY NumberOfReviews DESC " +
                    "LIMIT 1"
            );

            System.out.println("\nListing with the highest number of reviews in Anta e Guetim and available 365 days a year:");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println("Listing ID: " + record.get("ListingID").asString() +
                        ", Name: " + record.get("ListingName").asString() +
                        ", Number of Reviews: " + record.get("NumberOfReviews").asInt());
            }
        } catch (Exception e) {
            System.out.println("Error executing query 7: " + e.getMessage());
        }
    }

    // Query 8: Find the hosts with the sum of the prices of their listings above 1000
    public static void query8(Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (host:Host)-[:HOSTS]->(listing:Listing) " +
                    "WITH host, SUM(listing.price) AS total " +
                    "WHERE total > 1000 " +
                    "RETURN host.host_id AS HostID, host.host_name AS HostName, total AS TotalPrice"
            );

            System.out.println("\nHosts with the sum of the prices of their listings above 1000:");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println("Host ID: " + record.get("HostID").asString() +
                        ", Name: " + record.get("HostName").asString() +
                        ", Total Price: " + record.get("TotalPrice").asDouble());
            }
        } catch (Exception e) {
            System.out.println("Error executing query 8: " + e.getMessage());
        }
    }

    // Query 9: Find the number of listings for each room type in each neighbourhood
    public static void query9(Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood) " +
                    "RETURN neighbourhood.name AS Neighbourhood, listing.room_type AS RoomType, COUNT(listing) AS NumberOfListings " +
                    "ORDER BY Neighbourhood, RoomType"
            );

            System.out.println("\nNumber of listings for each room type in each neighbourhood:");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println("Neighbourhood: " + record.get("Neighbourhood").asString() +
                        ", Room Type: " + record.get("RoomType").asString() +
                        ", Number of Listings: " + record.get("NumberOfListings").asInt());
            }
        } catch (Exception e) {
            System.out.println("Error executing query 9: " + e.getMessage());
        }
    }

    // Query 10: Find all neighbourhoods and its listings
    public static void query10(Driver driver) {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (listing:Listing)-[:LOCATED_IN]->(neighbourhood:Neighbourhood) " +
                    "WITH collect(listing.name) AS Listings, neighbourhood " +
                    "WHERE size(Listings) > 0 " +
                    "RETURN neighbourhood.name AS Neighbourhood, Listings " +
                    "ORDER BY Neighbourhood"
            );

            System.out.println("\nAll neighbourhoods and its listings:");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println("Neighbourhood: " + record.get("Neighbourhood").asString() +
                        ", Listings: " + record.get("Listings").asList());
            }
        } catch (Exception e) {
            System.out.println("Error executing query 10: " + e.getMessage());
        }
    }
}