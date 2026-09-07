package it.unipv.ingsfw.Uploop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unipv.ingsfw.Uploop.model.User;

public class MySqlUserDAO implements UserDAO {
    
    // I dati del mio MySQL Workbench
	private final String URL = DBConfig.getProperty("db.url");
    private final String USER = DBConfig.getProperty("db.user");
    private final String PASSWORD = DBConfig.getProperty("db.password");

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Se trova l'utente, ricrea l'oggetto User
                return new User(rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(User user) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
            System.out.println("Utente salvato nel VERO DB MySQL: " + user.getUsername());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}