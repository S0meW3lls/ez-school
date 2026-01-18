package com.samueldeguio.ezschool.services;

import com.samueldeguio.ezschool.models.ContactRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactRequestService {

    private static final Logger logger = LoggerFactory.getLogger(ContactRequestService.class);

    /**
     * Saves the request to the database
     *
     * @param request the contact request to save
     * @return true if save was successful, false otherwise
     */
    public boolean save(ContactRequest request) {
        // TODO: actually store the request, for now just log it
        logger.info(request.toString());

        return true;
    }
}
