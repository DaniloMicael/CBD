package pt.tmg.cbd.lab2.ex3.c;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

import org.bson.Document;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.time.Instant;

public class RestaurantsQueries {
    private MongoCollection<Document> collection;

    public RestaurantsQueries(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    // query 11 in exercise 2.2
    public void query1() {
        List<Document> result = collection.find(Filters.and(
            Filters.eq("localidade", "Bronx"),
            Filters.in("gastronomia", Arrays.asList("American", "Chinese"))
        ))
        .projection(Projections.fields(
            Projections.include("nome", "localidade", "gastronomia")
        ))
        .into( new ArrayList<>());

        System.out.println("\nQuery 1 result:");
        System.out.println("Number of documents: \n" + result.size());
    }

    // querie 14 in exercise 2.2
    public void query2() {
        Instant instant = Instant.parse("2014-08-11T00:00:00Z");
        
        List<Document> result = collection.find(Filters.elemMatch("grades", Filters.and(
            Filters.eq("grade", "A"),
            Filters.eq("score", 10),
            Filters.eq("date", Date.from(instant))
        )))
        .projection(Projections.fields(
            Projections.include("nome", "grades")
        ))
        .into(new ArrayList<>());

        System.out.println("\nQuery 2 result:");
        System.out.println("Number of documents: \n" + result.size());
    }

    // query 18 in exercise 2.2
    public void query3() {
        List<Document> result = collection.find(Filters.and(
            Filters.eq("localidade", "Brooklyn"),
            Filters.ne("gastronomia", "American"),
            Filters.eq("grades.grade", "A")
        ))
        .projection(Projections.fields(
            Projections.include("nome", "localidade", "grades.grade", "gastronomia")
        ))
        .sort(Sorts.descending("gastronomia"))
        .into(new ArrayList<>());

        System.out.println("\nQuery 3 result:");
        System.out.println("Number of documents: \n" + result.size());
    }

    // query 20 in exercise 2.2
    public void query4() {
        List<Document> result = collection.aggregate(Arrays.asList(
            Aggregates.project(Projections.fields(
                Projections.include("nome"),
                Projections.computed("numGrades", new Document("$size", "$grades"))
            )),
            Aggregates.sort(Sorts.descending("numGrades")),
            Aggregates.limit(3)
        ))
        .into(new ArrayList<>());

        System.out.println("\nQuery 4 result:");
        result.forEach(doc -> System.out.println(doc.toJson()));
    }

    // query 24 in exercise 2.2
    public void query5() {
        List<Document> result = collection.aggregate(Arrays.asList(
            Aggregates.match(Filters.eq("address.rua", "Fifth Avenue")),
            Aggregates.group("$gastronomia"),
            Aggregates.count("numGastronomias")
        ))
        .into(new ArrayList<>());

        System.out.println("\nQuery 5 result:");
        result.forEach(doc -> System.out.println(doc.toJson()));
    }
}
