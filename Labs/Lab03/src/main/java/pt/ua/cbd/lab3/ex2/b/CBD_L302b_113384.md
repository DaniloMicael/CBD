# Lab03 - Ex 3.2 b)

## INSERT data

### Users
```sql
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user1', 'John Doe', 'john.doe@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user2', 'Jane Smith', 'jane.smith@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user3', 'Robert Johnson', 'robert.johnson@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user4', 'Emily Davis', 'emily.davis@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user5', 'Michael Brown', 'michael.brown@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user6', 'Sarah Wilson', 'sarah.wilson@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user7', 'David Lee', 'david.lee@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user8', 'Linda Taylor', 'linda.taylor@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user9', 'James Martinez', 'james.martinez@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user10', 'Patricia Anderson', 'patricia.anderson@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user11', 'Christopher Thomas', 'christopher.thomas@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user12', 'Barbara Hernandez', 'barbara.hernandez@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user13', 'Matthew Clark', 'matthew.clark@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user14', 'Elizabeth Lewis', 'elizabeth.lewis@example.com', toTimeStamp(now()));
INSERT INTO users (username, full_name, email, registration_timestamp) VALUES ('user15', 'Joshua Robinson', 'joshua.robinson@example.com', toTimeStamp(now()));
```

### Videos
```sql
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user1', 1, 'How to Learn Python', 'A comprehensive guide on learning Python programming.', {'Python', 'Programming', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user1', 2, 'Data Science Basics', 'An introduction to the field of Data Science.', {'Data Science', 'Machine Learning', 'AI'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user2', 3, 'JavaScript for Beginners', 'Learn the basics of JavaScript.', {'JavaScript', 'Web Development', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user2', 4, 'SQL Queries Explained', 'How to write SQL queries effectively.', {'SQL', 'Database', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user3', 5, 'Building REST APIs with Node.js', 'Creating REST APIs using Node.js.', {'Node.js', 'API', 'Backend Development'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user3', 6, 'CSS Grid Layout', 'Learn how to use CSS Grid for layout design.', {'CSS', 'Web Design', 'Frontend'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user4', 7, 'Docker for Beginners', 'A beginners guide to Docker.', {'Docker', 'Containerization', 'DevOps'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user4', 8, 'React Crash Course', 'A crash course on React.js for web development.', {'React', 'Web Development', 'JavaScript'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user5', 9, 'Introduction to Machine Learning', 'Understanding the basics of machine learning.', {'Machine Learning', 'AI', 'Data Science'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user5', 10, 'HTML Fundamentals', 'Learn the fundamentals of HTML.', {'HTML', 'Web Development', 'Frontend'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user6', 11, 'Version Control with Git', 'How to use Git for version control.', {'Git', 'Version Control', 'Software Development'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user6', 12, 'Exploring APIs with Postman', 'Testing APIs with Postman.', {'API', 'Postman', 'Testing'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user7', 13, 'Mobile Development with Flutter', 'Building mobile apps with Flutter.', {'Flutter', 'Mobile Development', 'Cross-Platform'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user7', 14, 'Data Visualization in Python', 'Visualizing data using Python.', {'Python', 'Data Visualization', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user8', 15, 'Web Security Basics', 'Understanding web security.', {'Web Security', 'Cybersecurity', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user8', 16, 'Introduction to PHP', 'Learn PHP for web development.', {'PHP', 'Web Development', 'Backend'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user9', 17, 'Learning Ruby on Rails', 'Getting started with Ruby on Rails.', {'Ruby', 'Web Development', 'Backend'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user9', 18, 'Introduction to Angular', 'A guide to Angular for frontend development.', {'Angular', 'Web Development', 'JavaScript'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user10', 19, 'Data Analysis with Pandas', 'Analyzing data using the Pandas library.', {'Python', 'Data Science', 'Pandas'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user10', 20, 'Getting Started with Java', 'A beginners guide to Java programming.', {'Java', 'Programming', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user11', 21, 'Machine Learning with Scikit-Learn', 'Building ML models using Scikit-Learn.', {'Machine Learning', 'Python', 'Scikit-Learn'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user11', 22, 'Kotlin for Android Development', 'Learn Kotlin for developing Android apps.', {'Kotlin', 'Android', 'Mobile Development'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user12', 23, 'Understanding NoSQL Databases', 'A guide to NoSQL database concepts.', {'NoSQL', 'Database', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user12', 24, 'Introduction to Cloud Computing', 'Understanding the basics of cloud computing.', {'Cloud', 'Computing', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user13', 25, 'Creating RESTful Services with Flask', 'Learn how to create REST APIs with Flask.', {'Flask', 'Python', 'API'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user13', 26, 'Introduction to Swift for iOS', 'Getting started with Swift for iOS development.', {'Swift', 'iOS', 'Mobile Development'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user14', 27, 'Web Development with ASP.NET', 'Creating web applications using ASP.NET.', {'ASP.NET', 'Web Development', 'Backend'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user14', 28, 'Ethical Hacking Basics', 'Introduction to ethical hacking techniques.', {'Ethical Hacking', 'Cybersecurity', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user15', 29, 'Game Development with Unity', 'Learn how to develop games using Unity.', {'Game Development', 'Unity', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos (author, video_id, title, description, tags, upload_timestamp) VALUES ('user15', 30, 'Introduction to UX Design', 'Understanding user experience design principles.', {'UX Design', 'Design', 'Tutorial'}, toTimeStamp(now()));
```

### Comments (author)
```sql
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user2', 1, 'This tutorial is very helpful, thank you!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user2', 2, 'Great introduction to data science!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user3', 3, 'JavaScript is so fun to learn!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user3', 4, 'Thanks for explaining SQL queries so clearly!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user4', 5, 'This was exactly what I needed for my project!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user4', 6, 'CSS Grid is a game changer!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user5', 7, 'Docker has transformed my workflow!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user5', 8, 'React is awesome! Cant wait to dive deeper.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user6', 9, 'Very informative on machine learning basics.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user6', 10, 'HTML is easy to learn!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user7', 11, 'Git is essential for version control!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user7', 12, 'Postman makes API testing so much easier!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user8', 13, 'Flutter is fantastic for cross-platform apps.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user8', 14, 'Data visualization is key to understanding data.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user9', 15, 'Web security is so important nowadays!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user9', 16, 'PHP is great for backend development.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user10', 17, 'Ruby on Rails simplifies web development!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user10', 18, 'Angular is a powerful framework for SPAs.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user11', 19, 'Pandas is perfect for data analysis!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user11', 20, 'Java is a great language for beginners.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user12', 21, 'Scikit-Learn makes ML easy to implement!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user12', 22, 'Kotlin is a modern language for Android.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user13', 23, 'NoSQL databases are the future!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user13', 24, 'Cloud computing is a game changer.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user14', 25, 'Flask is easy to use for APIs.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user14', 26, 'Swift is intuitive for iOS development.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user15', 27, 'ASP.NET is powerful for web applications.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user15', 28, 'Ethical hacking is crucial for cybersecurity.', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user1', 29, 'Game development is so exciting!', toTimeStamp(now()));
INSERT INTO comments_by_author (author, video_id, comment_text, comment_timestamp) VALUES ('user1', 30, 'UX design is essential for user satisfaction.', toTimeStamp(now()));
```

### Comments (video)
```sql
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (1, 'user2', 'This tutorial is very helpful, thank you!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (2, 'user2', 'Great introduction to data science!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (3, 'user3', 'JavaScript is so fun to learn!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (4, 'user3', 'Thanks for explaining SQL queries so clearly!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (5, 'user4', 'This was exactly what I needed for my project!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (6, 'user4', 'CSS Grid is a game changer!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (7, 'user5', 'Docker has transformed my workflow!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (8, 'user5', 'React is awesome! Cant wait to dive deeper.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (9, 'user6', 'Very informative on machine learning basics.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (10, 'user6', 'HTML is easy to learn!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (11, 'user7', 'Git is essential for version control!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (12, 'user7', 'Postman makes API testing so much easier!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (13, 'user8', 'Flutter is fantastic for cross-platform apps.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (14, 'user8', 'Data visualization is key to understanding data.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (15, 'user9', 'Web security is so important nowadays!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (16, 'user9', 'PHP is great for backend development.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (17, 'user10', 'Ruby on Rails simplifies web development!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (18, 'user10', 'Angular is a powerful framework for SPAs.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (19, 'user11', 'Pandas is perfect for data analysis!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (20, 'user11', 'Java is a great language for beginners.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (21, 'user12', 'Scikit-Learn makes ML easy to implement!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (22, 'user12', 'Kotlin is a modern language for Android.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (23, 'user13', 'NoSQL databases are the future!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (24, 'user13', 'Cloud computing is a game changer.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (25, 'user14', 'Flask is easy to use for APIs.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (26, 'user14', 'Swift is intuitive for iOS development.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (27, 'user15', 'ASP.NET is powerful for web applications.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (28, 'user15', 'Ethical hacking is crucial for cybersecurity.', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (29, 'user1', 'Game development is so exciting!', toTimeStamp(now()));
INSERT INTO comments_by_video (video_id, author, comment_text, comment_timestamp) VALUES (30, 'user1', 'UX design is essential for user satisfaction.', toTimeStamp(now()));
```

### Followers
```sql
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (1, 'user15', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (1, 'user10', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (1, 'user5', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (2, 'user11', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (2, 'user5', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (3, 'user4', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (3, 'user5', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (4, 'user3', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (4, 'user14', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (5, 'user2', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (5, 'user7', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (6, 'user8', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (6, 'user1', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (7, 'user3', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (7, 'user12', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (8, 'user1', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (8, 'user10', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (9, 'user7', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (9, 'user8', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (10, 'user1', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (10, 'user3', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (10, 'user4', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (10, 'user6', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (10, 'user9', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (10, 'user10', toTimeStamp(now()));
INSERT INTO video_followers (video_id, follower_username, follow_timestamp) VALUES (10, 'user11', toTimeStamp(now()));
```

### Events
```sql
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (1, 'user2', toTimeStamp(now()), 'play', 30);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (1, 'user2', toTimeStamp(now()), 'stop', 320);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (1, 'user2', toTimeStamp(now()), 'play', 60);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (1, 'user2', toTimeStamp(now()), 'stop', 380);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (1, 'user2', toTimeStamp(now()), 'play', 110);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (1, 'user2', toTimeStamp(now()), 'stop', 238);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (1, 'user10', toTimeStamp(now()), 'pause', 120);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (1, 'user4', toTimeStamp(now()), 'stop', 150);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (2, 'user4', toTimeStamp(now()), 'play', 45);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (2, 'user5', toTimeStamp(now()), 'play', 60);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (3, 'user6', toTimeStamp(now()), 'stop', 90);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (3, 'user7', toTimeStamp(now()), 'pause', 75);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (4, 'user8', toTimeStamp(now()), 'play', 15);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (4, 'user1', toTimeStamp(now()), 'stop', 100);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (5, 'user10', toTimeStamp(now()), 'play', 180);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (5, 'user12', toTimeStamp(now()), 'pause', 200);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (6, 'user4', toTimeStamp(now()), 'stop', 45);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (6, 'user11', toTimeStamp(now()), 'play', 50);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (7, 'user14', toTimeStamp(now()), 'pause', 55);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (7, 'user1', toTimeStamp(now()), 'stop', 60);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (8, 'user2', toTimeStamp(now()), 'play', 70);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (8, 'user1', toTimeStamp(now()), 'pause', 80);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (9, 'user10', toTimeStamp(now()), 'stop', 90);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (9, 'user13', toTimeStamp(now()), 'play', 100);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (10, 'user15', toTimeStamp(now()), 'pause', 110);
INSERT INTO video_events (video_id, username, event_timestamp, event_type, video_time_seconds) VALUES (10, 'user8', toTimeStamp(now()), 'stop', 120);
```

### Ratings
```sql
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (1, 'user3', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (1, 'user4', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (1, 'user5', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (1, 'user6', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (2, 'user8', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (2, 'user7', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (3, 'user5', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (3, 'user12', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (4, 'user10', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (4, 'user15', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (5, 'user1', toTimeStamp(now()), 1);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (5, 'user2', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (6, 'user2', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (6, 'user1', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (7, 'user10', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (7, 'user11', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (8, 'user2', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (8, 'user1', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (9, 'user3', toTimeStamp(now()), 1);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (9, 'user1', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (10, 'user10', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (10, 'user11', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (11, 'user15', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (11, 'user14', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (12, 'user13', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (12, 'user1', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (13, 'user2', toTimeStamp(now()), 1);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (13, 'user1', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (14, 'user2', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (14, 'user3', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (15, 'user1', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (15, 'user3', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (16, 'user3', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (16, 'user2', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (17, 'user1', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (17, 'user2', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (18, 'user3', toTimeStamp(now()), 1);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (18, 'user2', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (19, 'user4', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (19, 'user3', toTimeStamp(now()), 1);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (20, 'user5', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (20, 'user3', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (21, 'user1', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (21, 'user6', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (22, 'user7', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (22, 'user8', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (23, 'user9', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (23, 'user1', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (24, 'user2', toTimeStamp(now()), 1);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (24, 'user3', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (25, 'user4', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (25, 'user5', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (26, 'user6', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (26, 'user7', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (27, 'user8', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (27, 'user9', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (28, 'user10', toTimeStamp(now()), 1);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (28, 'user11', toTimeStamp(now()), 5);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (29, 'user12', toTimeStamp(now()), 3);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (29, 'user1', toTimeStamp(now()), 2);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (30, 'user2', toTimeStamp(now()), 4);
INSERT INTO video_ratings (video_id, author, rating_timestamp, rating_value) VALUES (30, 'user3', toTimeStamp(now()), 5);
```

### video_by_tag

```sql
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Python', 1, 'user1');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Programming', 1, 'user1');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Tutorial', 1, 'user1');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Data Science', 2, 'user1');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Machine Learning', 2, 'user1');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('AI', 2, 'user1');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('JavaScript', 3, 'user2');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Web Development', 3, 'user2');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Tutorial', 3, 'user2');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('SQL', 4, 'user2');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Database', 4, 'user2');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Tutorial', 4, 'user2');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Node.js', 5, 'user3');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('API', 5, 'user3');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Backend Development', 5, 'user3');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('CSS', 6, 'user3');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Web Design', 6, 'user3');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Frontend', 6, 'user3');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Docker', 7, 'user4');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Containerization', 7, 'user4');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('DevOps', 7, 'user4');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('React', 8, 'user4');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Web Development', 8, 'user4');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('JavaScript', 8, 'user4');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Machine Learning', 9, 'user5');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('AI', 9, 'user5');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Data Science', 9, 'user5');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('HTML', 10, 'user5');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Web Development', 10, 'user5');
INSERT INTO video_by_tag (tag, video_id, author) VALUES ('Frontend', 10, 'user5');
```

### videos_author
```sql
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user1', 1, 'How to Learn Python', 'A comprehensive guide on learning Python programming.', {'Python', 'Programming', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user1', 2, 'Data Science Basics', 'An introduction to the field of Data Science.', {'Data Science', 'Machine Learning', 'AI'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user2', 3, 'JavaScript for Beginners', 'Learn the basics of JavaScript.', {'JavaScript', 'Web Development', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user2', 4, 'SQL Queries Explained', 'How to write SQL queries effectively.', {'SQL', 'Database', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user3', 5, 'Building REST APIs with Node.js', 'Creating REST APIs using Node.js.', {'Node.js', 'API', 'Backend Development'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user3', 6, 'CSS Grid Layout', 'Learn how to use CSS Grid for layout design.', {'CSS', 'Web Design', 'Frontend'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user4', 7, 'Docker for Beginners', 'A beginners guide to Docker.', {'Docker', 'Containerization', 'DevOps'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user4', 8, 'React Crash Course', 'A crash course on React.js for web development.', {'React', 'Web Development', 'JavaScript'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user5', 9, 'Introduction to Machine Learning', 'Understanding the basics of machine learning.', {'Machine Learning', 'AI', 'Data Science'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user5', 10, 'HTML Fundamentals', 'Learn the fundamentals of HTML.', {'HTML', 'Web Development', 'Frontend'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user6', 11, 'Version Control with Git', 'How to use Git for version control.', {'Git', 'Version Control', 'Software Development'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user6', 12, 'Exploring APIs with Postman', 'Testing APIs with Postman.', {'API', 'Postman', 'Testing'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user7', 13, 'Mobile Development with Flutter', 'Building mobile apps with Flutter.', {'Flutter', 'Mobile Development', 'Cross-Platform'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user7', 14, 'Data Visualization in Python', 'Visualizing data using Python.', {'Python', 'Data Visualization', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user8', 15, 'Web Security Basics', 'Understanding web security.', {'Web Security', 'Cybersecurity', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user8', 16, 'Introduction to PHP', 'Learn PHP for web development.', {'PHP', 'Web Development', 'Backend'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user9', 17, 'Learning Ruby on Rails', 'Getting started with Ruby on Rails.', {'Ruby', 'Web Development', 'Backend'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user9', 18, 'Introduction to Angular', 'A guide to Angular for frontend development.', {'Angular', 'Web Development', 'JavaScript'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user10', 19, 'Data Analysis with Pandas', 'Analyzing data using the Pandas library.', {'Python', 'Data Science', 'Pandas'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user10', 20, 'Getting Started with Java', 'A beginners guide to Java programming.', {'Java', 'Programming', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user11', 21, 'Machine Learning with Scikit-Learn', 'Building ML models using Scikit-Learn.', {'Machine Learning', 'Python', 'Scikit-Learn'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user11', 22, 'Kotlin for Android Development', 'Learn Kotlin for developing Android apps.', {'Kotlin', 'Android', 'Mobile Development'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user12', 23, 'Understanding NoSQL Databases', 'A guide to NoSQL database concepts.', {'NoSQL', 'Database', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user12', 24, 'Introduction to Cloud Computing', 'Understanding the basics of cloud computing.', {'Cloud', 'Computing', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user13', 25, 'Creating RESTful Services with Flask', 'Learn how to create REST APIs with Flask.', {'Flask', 'Python', 'API'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user13', 26, 'Introduction to Swift for iOS', 'Getting started with Swift for iOS development.', {'Swift', 'iOS', 'Mobile Development'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user14', 27, 'Web Development with ASP.NET', 'Creating web applications using ASP.NET.', {'ASP.NET', 'Web Development', 'Backend'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user14', 28, 'Ethical Hacking Basics', 'Introduction to ethical hacking techniques.', {'Ethical Hacking', 'Cybersecurity', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user15', 29, 'Game Development with Unity', 'Learn how to develop games using Unity.', {'Game Development', 'Unity', 'Tutorial'}, toTimeStamp(now()));
INSERT INTO videos_author (author, video_id, title, description, tags, upload_timestamp) VALUES ('user15', 30, 'Introduction to UX Design', 'Understanding user experience design principles.', {'UX Design', 'Design', 'Tutorial'}, toTimeStamp(now()));
```

### comments_by_following_user
```sql
INSERT INTO comments_by_following_user (follower, video_id, author, comment, comment_timestamp)
VALUES ('user1', 8, 'user10', 'Angular is a powerful framework for SPAs.', toTimeStamp(now()));
INSERT INTO comments_by_following_user (follower, video_id, author, comment, comment_timestamp)
VALUES ('user1', 9, 'user6', 'Very informative on machine learning basics.', toTimeStamp(now()));
INSERT INTO comments_by_following_user (follower, video_id, author, comment, comment_timestamp)
VALUES ('user1', 10, 'user6', 'HTML is easy to learn!', toTimeStamp(now()));
```