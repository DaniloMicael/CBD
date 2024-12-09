package pt.ua.cbd.lab4.ex4;

import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class Main {
    private static String filePath = "resources/git_selection.csv";
    private static String uri = "bolt://localhost:7687";

    public static void main(String... args) {
        Driver driver = GraphDatabase.driver(uri);

        // Load dataset to Neo4j
        loadDataset(driver);
    }

    private static void loadDataset(Driver driver) {
        try (Session session = driver.session()) {
            session.run("LOAD CSV WITH HEADERS FROM 'file:///" + filePath + "' AS row " +
                    "MERGE (user: User {svn_id: row.svn_id, real_name: row.real_name})" +
                    "MERGE (org: Organization {name: row.organization})" +
                    "MERGE (project: Project {name: row.project_name})" +
                    "MERGE (user)-[:WORKS_ON]->(org)" +
                    "MERGE (user)-[:CONTRIBUTES {num: toInteger(row.num), role: row.role_on_project}]->(project)"
            );

            System.out.println("Dataset loaded successfully");
        } catch (Exception e) {
            System.out.println("Error loading dataset: " + e.getMessage());
        }
    }
}