package com.revature.model;

/**
 * Class User allows us to model the user for our db manipulations
 * user has id, first name, last name, email, password, DOB, horoscope sign and mood
 */
public class User {

    /**
     * properties of the User class
     */
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String horoscopeSign;
    private String mood;

    /**
     * Constructors
     */
    // No-arg constructor
    public User(){
        id = 0;
        firstName = "";
        lastName = "";
        email = "";
        password = "";
        horoscopeSign = "";
        mood = "";
    }

    public User(String first_name, String last_name, String email, String password, String horoscope_sign, String mood) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.password = password;
        this.horoscopeSign = horoscope_sign;
        this.mood = mood;
    }

    public User(int id, String first_name, String last_name, String email, String password, String horoscope_sign, String mood) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.password = password;
        this.horoscopeSign = horoscope_sign;
        this.mood = mood;
    }

    /**
     * Getters and Setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoroscopeSign() {
        return horoscopeSign;
    }

    public void setHoroscopeSign(String horoscope_sign) {
        this.horoscopeSign = horoscope_sign;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }


}
