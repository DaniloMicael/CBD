package pt.tmg.cbd.lab2.ex3.d;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Accumulators;
import org.bson.Document;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class RestaurantsDAO {
    private final MongoCollection<Document> mongoCollection;

    public RestaurantsDAO(MongoCollection<Document> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public int countLocalidades() {
        List<String> localidades = mongoCollection.distinct("localidade", String.class).into(new ArrayList<>());
        return localidades.size();
    }

    public Map<String, Integer> countRestByLocalidade() {
        Map<String, Integer> countRestByLocalidade = new HashMap<>();

        AggregateIterable<Document> aggregation = mongoCollection.aggregate(Arrays.asList(
            Aggregates.group("$localidade", Accumulators.sum("count", 1))
        ));

        for (Document doc : aggregation) {
            countRestByLocalidade.put(doc.getString("_id"), doc.getInteger("count"));
        }
        
        return countRestByLocalidade;
    }

    public List<String> getRestWithNameCloserTo(String name) {
        List<String> restaurants = new ArrayList<>();

        AggregateIterable<Document> aggregation = mongoCollection.aggregate(Arrays.asList(
            Aggregates.match(new Document("nome", new Document("$regex", name))),
            Aggregates.project(new Document("nome", 1))
        ));

        for (Document doc : aggregation) {
            restaurants.add(doc.getString("nome"));
        }

        return restaurants;
    }
}
