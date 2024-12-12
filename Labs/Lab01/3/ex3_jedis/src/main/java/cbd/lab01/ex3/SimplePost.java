package cbd.lab01.ex3; 
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.UUID;
 
public class SimplePost { 
    public static String USERS_KEY_LIST = "users_list";
    public static String USERS_KEY_MAP = "users_map";
    public static void main(String[] args) { 
        Jedis jedis = new Jedis(); 
        // some users 
        String[] users = { "Ana", "Pedro", "Maria", "Luis" };
        jedis.del(USERS_KEY_LIST); // remove if exists to avoid wrong type
        jedis.del(USERS_KEY_MAP); // remove if exists to avoid wrong type

        for (String user : users) {
            jedis.lpush(USERS_KEY_LIST, user);
            jedis.hset(USERS_KEY_MAP, "user " + UUID.randomUUID(), user);
        }

        // print the elements of the list
        System.out.println("\nList elements:");
        for (String user : jedis.lrange(USERS_KEY_LIST, 0, -1)) {
            System.out.println(user);
        }

        // print the elements of the hashmap
        System.out.println("\nHashMap elements:");
        Map<String, String> userMap = jedis.hgetAll(USERS_KEY_MAP);
        for (Map.Entry<String, String> entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        jedis.close(); 
    } 
}