package com.revature.controller;

import com.revature.model.User;
import com.revature.service.Service;
import io.javalin.http.Handler;

public class Controller {

    private Service service;

    // Constructors
    public Controller() {
        service = new Service();
    }

    public Controller(Service service) {
        this.service = service;
    }

    // Methods
    // Login new User
    public Handler login = context -> {
        User user = context.bodyAsClass(User.class);
        user = service.loginUser(user);

        if (user.getId() > 0){
            context.status(200).json(user);
        } else {
            context.status(400).result("Please check username and password");
        }
    };

    public Handler logout = context -> {
        boolean loggedOut = service.logoutUser();
        if (loggedOut) {
            context.status(200).result("You are successfully logged out");
        } else {
            context.status(400).result("You are still logged in");
        }
    };


    // Create new User
    public Handler createNewUser = context -> {

        User user = context.bodyAsClass(User.class);
        int id = service.createUser(user);

        if (id > 0) {
            user.setId(id);
            context.json(user).status(200);
        } else {
            context.result("User not created").status(400);
        }
    };

    public Handler updateMood = context -> {

        User user = context.bodyAsClass(User.class);
        String mood = user.getMood();
        user = service.updateMood(user);

        if (mood.equalsIgnoreCase(user.getMood())){
            context.status(200);
        } else {
            context.status(404);
        }
    };


}
