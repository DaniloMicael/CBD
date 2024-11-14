package pt.ua.cbd.lab3.ex3;

import com.datastax.oss.driver.api.core.cql.ResultSet;

import com.datastax.oss.driver.api.core.CqlSession;

public class DatabaseQueries {
    private final CqlSession session;

    public DatabaseQueries(CqlSession session) {
        this.session = session;
    }

    public void insertUser(String username, String full_name, String email) {
        String insertUserQuery = "INSERT INTO users (username, full_name, email, registration_timestamp) VALUES (?, ?, ?, toTimeStamp(now()))";
        this.session.execute(insertUserQuery, username, full_name, email);
    }

    public void editUser(String username, String full_name, String email) {
        String editUserQuery = "UPDATE users SET full_name = ?, email = ? WHERE username = ?";
        this.session.execute(editUserQuery, full_name, email, username);
    }

    public void deleteUser(String username) {
        String deleteUserQuery = "DELETE FROM users WHERE username = ?";
        this.session.execute(deleteUserQuery, username);
    }

    public ResultSet searchUser(String username) {
        String searchUserQuery = "SELECT * FROM users WHERE username = ?";
        return this.session.execute(searchUserQuery, username);
    }

    public ResultSet query2(String author, Integer video_id) {
        String query2 = "SELECT tags FROM videos WHERE author = ? AND video_id = ?";
        return this.session.execute(query2, author, video_id);
    }

    public ResultSet query4b(String username) {
        String query4b = "SELECT * FROM video_events WHERE username = ?";
        return this.session.execute(query4b, username);
    } 

    public ResultSet query7(Integer video_id) {
        String query7 = "SELECT follower_username FROM video_followers WHERE video_id = ?";
        return this.session.execute(query7, video_id);
    }

    public ResultSet query11() {
        String query11 = "SELECT tag, COUNT(video_id) AS video_count FROM video_by_tag GROUP BY tag";
        return this.session.execute(query11);
    }
}
