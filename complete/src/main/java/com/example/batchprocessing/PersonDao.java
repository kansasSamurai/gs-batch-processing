package com.example.batchprocessing;

public interface PersonDao {
    Person getPersonById(Long id);
    Person findByLastName(String lastname);
}
