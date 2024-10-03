# Redis

## Pull redis image

```bash
docker pull redis:7
```

## Run redis in background

```bash
docker run -d --name redis-server -p 6379:6379 redis:7
```

## Stop and start server

```bash
docker stop redis-server
```

```bash
docker start redis-server
```

## Connect to redis 

```bash
docker exec -it redis-server redis-cli
```
Inicia um cliente que pode fazer comandos no terminal, esses comandos ficam guardados no ficheiro /.rediscli_history. Para sair -> Crtl + C

## Enter the redis container 

```bash
docker exec -it redis-server /bin/bash
```
Para sair -> exit

## Copy the /.rediscli_history to a file

```bash
docker cp redis-server:/root/.rediscli_history <file_name>.txt
```

## Run commands in a file

```bash
docker exec -i redis-server redis-cli < <file_name>.txt
```

## Explanation of ex 4b

### Creation of 2 sets:

```bash
public static String NAMES_SCORES_KEY = "names_scores";
```

```bash
public static String NAMES_KEY = "names";
```

The set NAMES_KEY is auxiliar, used to search for names with a specific prefix, the scores in this set are all 0

### Add names to the sets

```bash
jedis.zadd(NAMES_SCORES_KEY, popularity, name);
```

```bash
jedis.zadd(NAMES_KEY, 0, name);
```

### Get the names with some prefix

```bash
List<String> namesWithPrefix = jedis.zrangeByLex(NAMES_KEY, "[" + prefix, "[" + prefix + "\uFFFF");
```

### Get the scores
```bash
jedis.zscore(NAMES_SCORES_KEY, name)
```


# Maven

## Create maven project

```bash
mvn archetype:generate -DgroupId=<organization> -DartifactId=<project_name> -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## Package maven project

```bash
mvn package
```

## Execute maven project

```bash
mvn exec:java -Dexec.mainClass="<package>.<main_class_name>" -Dexec.args="<arg1> <arg2>"
```