package com.samueldeguio.ezschool.services;

import com.samueldeguio.ezschool.models.ContactRequest;
import com.samueldeguio.ezschool.repositories.ContactRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactRequestService {

    private final ContactRequestRepository repository;

    @Autowired
    public ContactRequestService(ContactRequestRepository repository) {
        this.repository = repository;
    }

    /**
     * process the request contact
     *
     * @param request the contact request to save
     * @return true if save was successful, false otherwise
     */
    public boolean save(ContactRequest request) {
        return this.repository.save(request);
    }
}
