package com.example.batchprocessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, timeout = 2400)
public class PersonService {

    @Autowired
    private PersonDao personDAO;

    public Person getPerson(Long id) {
        return personDAO.getPersonById(id);
    }

    public Person findByLastName(String lastname) {
        return personDAO.findByLastName(lastname);
    }

}
