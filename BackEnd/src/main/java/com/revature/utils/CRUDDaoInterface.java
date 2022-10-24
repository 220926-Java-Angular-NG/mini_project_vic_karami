package com.revature.utils;

import java.util.List;

public interface CRUDDaoInterface<T> {

    // DAO - Data Access Object
    //  a pattern that provides an abstract interface to some type of database
    // or other persistence mechanism

    // Returns a primary key of this newly added row
    int create(T t);

    // Retrieve a record by email
    T getByEmail(String email);

    // Update a record = UPDATE record
    T update(T t);
}
