package com.samueldeguio.ezschool.controller;

import com.samueldeguio.ezschool.models.ContactRequest;
import com.samueldeguio.ezschool.services.ContactRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    private final ContactRequestService service;

    public ContactController(ContactRequestService contactRequestService) {
        this.service = contactRequestService;
    }

    @GetMapping("/contact")
    public String contact() {
        return "pages/contact";
    }

    @PostMapping("/contact")
    public ModelAndView sendContactRequest(ContactRequest request) {

        // save request to the database
        this.service.save(request);

        return new ModelAndView("redirect:/contact");
    }
}
