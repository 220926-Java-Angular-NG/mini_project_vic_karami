package com.revature.repo;

import com.revature.model.User;
import com.revature.utils.CRUDDaoInterface;
import com.revature.utils.ConnectionManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Repo implements CRUDDaoInterface<User> {

    public Connection conn;

    public static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    private User loggedUser = new User();

    public Repo(){


        // Note: certain methods throw errors because there are different instances of what can go wrong
        // in order to handle those errors in a way that saves the application from crashing
        // we wrap those methods or blocks of code in a "try {} catch() {}" block
        try {
            // This is code that can throw an error
            conn = ConnectionManager.getConnection();

        } catch (SQLException sqlException) {
            LOGGER.error(sqlException.getMessage());
        }
    }

    public User login(User user) {

        try {

            if (loggedUser.getId() == 0) {

                String sql = "SELECT * FROM users WHERE email = ? AND pass_word = ?";

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, user.getEmail());
                pstmt.setString(2, user.getPassword());

                ResultSet rs = pstmt.executeQuery();

                if (rs.next() && rs.getString("pass_word").equals(user.getPassword())) {

                    loggedUser.setId(rs.getInt("id"));
                    loggedUser.setFirstName(rs.getString("first_name"));
                    loggedUser.setLastName(rs.getString("last_name"));
                    loggedUser.setEmail(rs.getString("email"));
                    loggedUser.setPassword(rs.getString("pass_word"));
                    loggedUser.setHoroscopeSign(rs.getString("horoscope_sign"));
                    loggedUser.setMood(rs.getString("mood"));


                    return loggedUser;
                } else {
                    return null;
                }
            } else {
                return loggedUser;
            }
        } catch(SQLException sqlException){
            System.out.println("this is userDAO: " + sqlException.getMessage());
        }
        return null;
    }

    public boolean logout() {
        if (loggedUser.getId() > 0) {
            loggedUser.setId(0);
            loggedUser.setFirstName("");
            loggedUser.setLastName("");
            loggedUser.setEmail("");
            loggedUser.setPassword("");
            loggedUser.setHoroscopeSign("");
            loggedUser.setMood("");
            return true;
        } else return false;
    }

    @Override
    public int create(@NotNull User user) {
        try {
            // Insert a record into existing table
            String sql = "INSERT INTO users (id, first_name, last_name, email, pass_word, horoscope_sign, mood) VALUES (default, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4,user.getPassword());
            pstmt.setString(5, user.getHoroscopeSign());
            pstmt.setString(6, user.getMood());

            // this executes the sql statement above
            pstmt.executeUpdate();

            // this gives us a result set of the row that was just created
            ResultSet rs = pstmt.getGeneratedKeys();

            // the cursor is right in front of the first (or only) element in our result set
            // calling rs.next() iterates into the first row
            rs.next();

            return rs.getInt("id");

        } catch (SQLException sqlException) {

            System.out.println(sqlException.getMessage());
        }
        return -1;
    }

    @Override
    public User getByEmail(String email) {
        try {

            String sql = "SELECT * FROM users WHERE email = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            // Returns a user therefore a new instance of a user from database has to be created
            User user = new User();

            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass_word"));
                user.setHoroscopeSign(rs.getString("horoscope_sign"));
                user.setMood(rs.getString("mood"));
            }
            return user;
        }
        catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return null;
        }
    }

    @Override
    public User update(User user) {
        try {
            String sql = "UPDATE users SET mood = ? WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getMood());
            pstmt.setInt(2, user.getId());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while(rs.next()){
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass_word"));
                user.setHoroscopeSign(rs.getString("horoscope_sign"));
                user.setMood(rs.getString("mood"));
            }
            return user;

        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }
}
