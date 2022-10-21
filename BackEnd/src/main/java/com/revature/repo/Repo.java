package com.revature.repo;

import com.revature.model.User;
import com.revature.utils.CRUDDaoInterface;
import com.revature.utils.ConnectionManager;
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

    public User loginUser(User user) {

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

    @Override
    public int create(User user) {
        return 0;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
