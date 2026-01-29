package com.samueldeguio.ezschool.repositories;

import com.samueldeguio.ezschool.models.ContactRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactRequestRepository {

    private final JdbcTemplate connection;


    @Autowired
    public ContactRequestRepository(JdbcTemplate connection) {
        this.connection = connection;
    }

    /**
     * Saves the request to the database
     *
     * @param request the contact request to save
     * @return true if save was successful, false otherwise
     */
    public boolean save(ContactRequest request) {

        int rows = this.connection.update( "INSERT INTO contact_requests (name, email, subject, message) VALUES (?,?,?,?)",
                request.getName(),
                request.getEmail(),
                request.getSubject().toString(),
                request.getMessage()
        );

        return rows > 0;
    }
}
