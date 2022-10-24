package com.revature.service;

import com.revature.model.User;
import com.revature.repo.Repo;

public class Service {

    private Repo repo;

    // Constructors
    public Service(){
        repo = new Repo();
    }

    public Service (Repo repo){
        this.repo = repo;
    }

    //Methods
    public User loginUser(User user){ return repo.login(user);}

    public boolean logoutUser(){ return repo.logout();}

    public int createUser(User user){
        return repo.create(user);
    }

    public User updateMood(User user){
        return repo.update(user);
    }

    public User getUserByEmail(String email){
        return repo.getByEmail(email);
    }
}
