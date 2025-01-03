# Lab03 - Ex 3.2 d)

## 1-11 Queries

### 1. Os últimos 3 comentários introduzidos para um vídeo
```sql
SELECT * FROM comments_by_video 
WHERE video_id = 1 
ORDER BY comment_timestamp DESC
LIMIT 3;
```

Result:
```
 video_id | comment_timestamp               | author | comment_text
----------+---------------------------------+--------+-------------------------------------------
        1 | 2024-11-02 12:14:52.995000+0000 |  user2 | This tutorial is very helpful, thank you!

```

### 2. Lista das tags de determinado vídeo
```sql
SELECT tags FROM videos 
WHERE author = 'user1' 
AND video_id = 1;
```
Result:
```
 tags
---------------------------------------
 {'Programming', 'Python', 'Tutorial'}
```

### 3. Todos os vídeos com a tag Tutorial
```sql
SELECT * FROM videos 
WHERE tags 
CONTAINS 'Tutorial';
```

Note: I created an INDEX to solve this query:
```sql
CREATE INDEX ON videos (tags);
```

Result:
```
 author | video_id | description                                           | tags                                             | title                           | upload_timestamp
--------+----------+-------------------------------------------------------+--------------------------------------------------+---------------------------------+---------------------------------
 user14 |       28 |           Introduction to ethical hacking techniques. | {'Cybersecurity', 'Ethical Hacking', 'Tutorial'} |          Ethical Hacking Basics | 2024-11-07 11:41:32.668000+0000
  user8 |       15 |                           Understanding web security. |    {'Cybersecurity', 'Tutorial', 'Web Security'} |             Web Security Basics | 2024-11-07 11:41:32.640000+0000
 user12 |       23 |                   A guide to NoSQL database concepts. |                {'Database', 'NoSQL', 'Tutorial'} |   Understanding NoSQL Databases | 2024-11-07 11:41:32.658000+0000
 user12 |       24 |          Understanding the basics of cloud computing. |               {'Cloud', 'Computing', 'Tutorial'} | Introduction to Cloud Computing | 2024-11-07 11:41:32.660000+0000
  user2 |        3 |                       Learn the basics of JavaScript. |    {'JavaScript', 'Tutorial', 'Web Development'} |        JavaScript for Beginners | 2024-11-07 11:41:32.613000+0000
  user2 |        4 |                 How to write SQL queries effectively. |                  {'Database', 'SQL', 'Tutorial'} |           SQL Queries Explained | 2024-11-07 11:41:32.615000+0000
  user1 |        1 | A comprehensive guide on learning Python programming. |            {'Programming', 'Python', 'Tutorial'} |             How to Learn Python | 2024-11-07 11:41:32.595000+0000
 user10 |       20 |                A beginners guide to Java programming. |              {'Java', 'Programming', 'Tutorial'} |       Getting Started with Java | 2024-11-07 11:41:32.652000+0000
  user7 |       14 |                        Visualizing data using Python. |     {'Data Visualization', 'Python', 'Tutorial'} |    Data Visualization in Python | 2024-11-07 11:41:32.638000+0000
 user15 |       29 |               Learn how to develop games using Unity. |        {'Game Development', 'Tutorial', 'Unity'} |     Game Development with Unity | 2024-11-07 11:41:32.670000+0000
 user15 |       30 |      Understanding user experience design principles. |              {'Design', 'Tutorial', 'UX Design'} |       Introduction to UX Design | 2024-11-07 11:41:33.599000+0000

```

### 4. a. Os últimos 5 eventos de determinado vídeo realizados por um utilizador
```sql
SELECT * FROM video_events
WHERE username = 'user2' AND video_id = 1
ORDER BY event_timestamp DESC
LIMIT 5;
```

Result:
```
 video_id | username | event_timestamp                 | event_type | video_time_seconds
----------+----------+---------------------------------+------------+--------------------
        1 |    user2 | 2024-11-07 16:01:37.574000+0000 |       stop |                238
        1 |    user2 | 2024-11-07 16:01:36.689000+0000 |       play |                110
        1 |    user2 | 2024-11-07 16:01:36.688000+0000 |       stop |                380
        1 |    user2 | 2024-11-07 16:01:36.687000+0000 |       play |                 60
        1 |    user2 | 2024-11-07 16:01:36.679000+0000 |       stop |                320

```

### 4. b. Todos os eventos de determinado utilizador
```sql
SELECT * FROM video_events
WHERE username = 'user2';
```

Result:
```
 username | video_id | event_timestamp                 | event_type | video_time_seconds
----------+----------+---------------------------------+------------+--------------------
    user2 |        1 | 2024-11-07 16:09:34.503000+0000 |       play |                 30
    user2 |        1 | 2024-11-07 16:09:34.508000+0000 |       stop |                320
    user2 |        1 | 2024-11-07 16:09:34.510000+0000 |       play |                 60
    user2 |        1 | 2024-11-07 16:09:34.511000+0000 |       stop |                380
    user2 |        1 | 2024-11-07 16:09:34.512000+0000 |       play |                110
    user2 |        1 | 2024-11-07 16:09:34.514000+0000 |       stop |                238
    user2 |        8 | 2024-11-07 16:09:34.532000+0000 |       play |                 70

```

### 4. c. Todos os eventos de determinado utilizador do tipo "pause" 
```sql
SELECT * FROM video_events
WHERE username = 'user10'
AND event_type = 'pause';
```

Notes: It was created an INDEX to implement this query
```sql
CREATE INDEX ON video_events (event_type);
```

Result:
```
 username | video_id | event_timestamp                 | event_type | video_time_seconds
----------+----------+---------------------------------+------------+--------------------
   user10 |        1 | 2024-11-07 16:09:34.515000+0000 |      pause |                120

```

### 5. Vídeos partilhados por determinado utilizador (maria1987, por exemplo) num determinado período de tempo (Agosto de 2017, por exemplo)
```sql
SELECT * FROM videos_author
WHERE author = 'user1'
AND upload_timestamp >= '2024-11-13'
AND upload_timestamp < '2024-11-15';
```

Notes: It was created an auxiliar TABLE to implement this query:
```sql
CREATE TABLE videos_author (
    author TEXT,
    video_id INT,
    title TEXT,
    description TEXT,
    tags SET<TEXT>,
    upload_timestamp TIMESTAMP,
    PRIMARY KEY (author, upload_timestamp)
) WITH CLUSTERING ORDER BY (upload_timestamp ASC);
```

Result:
```
 author | upload_timestamp                | description                                           | tags                                       | title               | video_id
--------+---------------------------------+-------------------------------------------------------+--------------------------------------------+---------------------+----------
  user1 | 2024-11-14 12:52:49.354000+0000 | A comprehensive guide on learning Python programming. |      {'Programming', 'Python', 'Tutorial'} | How to Learn Python |        1
  user1 | 2024-11-14 12:52:49.357000+0000 |         An introduction to the field of Data Science. | {'AI', 'Data Science', 'Machine Learning'} | Data Science Basics |        2

```

### 6. Os últimos 10 vídeos, ordenado inversamente pela data da partilhada

This query is not possible because we need to find the videos using the partition key and then sort by upload_timestamp. It is not possible to sort the data using the partition key, in this case, the upload_timestamp

### 7. Todos os seguidores (followers) de determinado vídeo
```sql
SELECT follower_username 
FROM video_followers 
WHERE video_id = 1;
```

Result:
```
 follower_username
-------------------
            user10
            user15
             user5

```

### 8. Todos os comentários (dos vídeos) que determinado utilizador está a seguir (following)
```sql
SELECT * FROM comments_by_following_user
WHERE follower = 'user1';
```

Notes: It was created an auxiliar TABLE to implement this query:

```sql
CREATE TABLE comments_by_following_user (
    follower TEXT,
    video_id INT,
    author TEXT,
    comment TEXT,
    comment_timestamp TIMESTAMP,
    PRIMARY KEY (follower, video_id, comment_timestamp)
) WITH CLUSTERING ORDER BY (video_id ASC, comment_timestamp ASC);
```

Result:
```
 follower | video_id | comment_timestamp               | author | comment
----------+----------+---------------------------------+--------+----------------------------------------------
    user1 |        8 | 2024-11-14 15:20:26.331000+0000 | user10 |    Angular is a powerful framework for SPAs.
    user1 |        9 | 2024-11-14 15:20:26.333000+0000 |  user6 | Very informative on machine learning basics.
    user1 |       10 | 2024-11-14 15:20:28.543000+0000 |  user6 |                       HTML is easy to learn!

```

### 9. Os 5 vídeos com maior rating

This query is not possible because we need to find the videos using the partition key (rating) and then order by it. It is not possible to simply sort the data using the partition key. It is the same problem og the query 6.

### 10. Uma query que retorne todos os vídeos e que mostre claramente a forma pela qual estão ordenados
```sql
SELECT * FROM videos;
```

#### Order Explanation

In Cassandra, data is organized within partitions. Each partition is identified by a unique partition key, and within each partition, data can be ordered by one or more clustering keys. In this case, the videos are ordered within each partition (author) by the clustering key (video_id) in ascending order.

When I query videos by author, Cassandra will:

- Group all the videos of that author into the same partition.
- Automatically order these videos by video_id in ascending order within that partition.

Result:
```
 author | video_id | description                                           | tags                                                | title                                | upload_timestamp
--------+----------+-------------------------------------------------------+-----------------------------------------------------+--------------------------------------+---------------------------------
 user14 |       27 |              Creating web applications using ASP.NET. |           {'ASP.NET', 'Backend', 'Web Development'} |         Web Development with ASP.NET | 2024-11-07 16:08:53.140000+0000
 user14 |       28 |           Introduction to ethical hacking techniques. |    {'Cybersecurity', 'Ethical Hacking', 'Tutorial'} |               Ethical Hacking Basics | 2024-11-07 16:08:53.142000+0000
  user8 |       15 |                           Understanding web security. |       {'Cybersecurity', 'Tutorial', 'Web Security'} |                  Web Security Basics | 2024-11-07 16:08:53.120000+0000
  user8 |       16 |                        Learn PHP for web development. |               {'Backend', 'PHP', 'Web Development'} |                  Introduction to PHP | 2024-11-07 16:08:53.122000+0000
 user12 |       23 |                   A guide to NoSQL database concepts. |                   {'Database', 'NoSQL', 'Tutorial'} |        Understanding NoSQL Databases | 2024-11-07 16:08:53.133000+0000
 user12 |       24 |          Understanding the basics of cloud computing. |                  {'Cloud', 'Computing', 'Tutorial'} |      Introduction to Cloud Computing | 2024-11-07 16:08:53.135000+0000
  user2 |        3 |                       Learn the basics of JavaScript. |       {'JavaScript', 'Tutorial', 'Web Development'} |             JavaScript for Beginners | 2024-11-07 16:08:53.098000+0000
  user2 |        4 |                 How to write SQL queries effectively. |                     {'Database', 'SQL', 'Tutorial'} |                SQL Queries Explained | 2024-11-07 16:08:53.100000+0000
  user9 |       17 |                   Getting started with Ruby on Rails. |              {'Backend', 'Ruby', 'Web Development'} |               Learning Ruby on Rails | 2024-11-07 16:08:53.123000+0000
  user9 |       18 |          A guide to Angular for frontend development. |        {'Angular', 'JavaScript', 'Web Development'} |              Introduction to Angular | 2024-11-07 16:08:53.125000+0000
 user13 |       25 |             Learn how to create REST APIs with Flask. |                          {'API', 'Flask', 'Python'} | Creating RESTful Services with Flask | 2024-11-07 16:08:53.136000+0000
 user13 |       26 |       Getting started with Swift for iOS development. |              {'Mobile Development', 'Swift', 'iOS'} |        Introduction to Swift for iOS | 2024-11-07 16:08:53.138000+0000
  user4 |        7 |                          A beginners guide to Docker. |            {'Containerization', 'DevOps', 'Docker'} |                 Docker for Beginners | 2024-11-07 16:08:53.104000+0000
  user4 |        8 |       A crash course on React.js for web development. |          {'JavaScript', 'React', 'Web Development'} |                   React Crash Course | 2024-11-07 16:08:53.107000+0000
  user1 |        1 | A comprehensive guide on learning Python programming. |               {'Programming', 'Python', 'Tutorial'} |                  How to Learn Python | 2024-11-07 16:08:53.086000+0000
  user1 |        2 |         An introduction to the field of Data Science. |          {'AI', 'Data Science', 'Machine Learning'} |                  Data Science Basics | 2024-11-07 16:08:53.095000+0000
 user10 |       19 |              Analyzing data using the Pandas library. |                {'Data Science', 'Pandas', 'Python'} |            Data Analysis with Pandas | 2024-11-07 16:08:53.126000+0000
 user10 |       20 |                A beginners guide to Java programming. |                 {'Java', 'Programming', 'Tutorial'} |            Getting Started with Java | 2024-11-07 16:08:53.128000+0000
  user7 |       13 |                    Building mobile apps with Flutter. | {'Cross-Platform', 'Flutter', 'Mobile Development'} |      Mobile Development with Flutter | 2024-11-07 16:08:53.117000+0000
  user7 |       14 |                        Visualizing data using Python. |        {'Data Visualization', 'Python', 'Tutorial'} |         Data Visualization in Python | 2024-11-07 16:08:53.119000+0000
  user3 |        5 |                     Creating REST APIs using Node.js. |           {'API', 'Backend Development', 'Node.js'} |      Building REST APIs with Node.js | 2024-11-07 16:08:53.101000+0000
  user3 |        6 |          Learn how to use CSS Grid for layout design. |                   {'CSS', 'Frontend', 'Web Design'} |                      CSS Grid Layout | 2024-11-07 16:08:53.103000+0000
  user6 |       11 |                   How to use Git for version control. |  {'Git', 'Software Development', 'Version Control'} |             Version Control with Git | 2024-11-07 16:08:53.114000+0000
  user6 |       12 |                            Testing APIs with Postman. |                       {'API', 'Postman', 'Testing'} |          Exploring APIs with Postman | 2024-11-07 16:08:53.116000+0000
 user11 |       21 |                Building ML models using Scikit-Learn. |      {'Machine Learning', 'Python', 'Scikit-Learn'} |   Machine Learning with Scikit-Learn | 2024-11-07 16:08:53.130000+0000
 user11 |       22 |             Learn Kotlin for developing Android apps. |         {'Android', 'Kotlin', 'Mobile Development'} |       Kotlin for Android Development | 2024-11-07 16:08:53.132000+0000
  user5 |        9 |         Understanding the basics of machine learning. |          {'AI', 'Data Science', 'Machine Learning'} |     Introduction to Machine Learning | 2024-11-07 16:08:53.110000+0000
  user5 |       10 |                       Learn the fundamentals of HTML. |             {'Frontend', 'HTML', 'Web Development'} |                    HTML Fundamentals | 2024-11-07 16:08:53.112000+0000
 user15 |       29 |               Learn how to develop games using Unity. |           {'Game Development', 'Tutorial', 'Unity'} |          Game Development with Unity | 2024-11-07 16:08:53.144000+0000
 user15 |       30 |      Understanding user experience design principles. |                 {'Design', 'Tutorial', 'UX Design'} |            Introduction to UX Design | 2024-11-07 16:08:53.972000+0000

```

### 11. Lista com as Tags existentes e o número de vídeos catalogados com cada uma delas
```sql
SELECT tag, COUNT(video_id) AS video_count 
FROM video_by_tag 
GROUP BY tag;
```

Notes: It was created an auxiliar TABLE to implement this query:
```sql
CREATE TABLE video_by_tag (
    tag TEXT,
    video_id INT,
    author TEXT,
    PRIMARY KEY (tag, video_id)
);
```

Result:
```
 tag                 | video_count
---------------------+-------------
    Containerization |           1
            Tutorial |           3
               React |           1
             Node.js |           1
              Docker |           1
          Web Design |           1
              Python |           1
     Web Development |           3
                 CSS |           1
 Backend Development |           1
        Data Science |           2
                 SQL |           1
                 API |           1
            Database |           1
    Machine Learning |           2
            Frontend |           2
                  AI |           2
              DevOps |           1
          JavaScript |           2
                HTML |           1
         Programming |           1

```

### 12. Número de seguidores de um vídeo
```sql
SELECT COUNT(*)
FROM video_followers
WHERE video_id = 1;
```

Result:
```
 count
-------
     3

```
### 13. Lista de videos que um user comentou
```sql
SELECT video_id, comment_text, comment_timestamp
FROM comments_by_author
WHERE author = 'user1';
```

Result:
```
 video_id | comment_text                                  | comment_timestamp
----------+-----------------------------------------------+---------------------------------
       30 | UX design is essential for user satisfaction. | 2024-11-25 15:20:04.893000+0000
       29 |              Game development is so exciting! | 2024-11-25 15:20:04.327000+0000

```
### 14. Numero de videos partilhados por um user
```sql
SELECT COUNT(*) 
FROM videos 
WHERE author = 'user1';
```

Result:
```
 count
-------
     2

```