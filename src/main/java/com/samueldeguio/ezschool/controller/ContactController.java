package com.samueldeguio.ezschool.controller;

import com.samueldeguio.ezschool.models.ContactRequest;
import com.samueldeguio.ezschool.services.ContactRequestService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
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
    public String sendContactRequest(@Valid @ModelAttribute("contact") ContactRequest request, Errors errors) {

        // if any error is detected return to previous page
        if(errors.hasErrors()) return "pages/contact.html";

        // save request to the database
        this.service.save(request);

        //  full redirect clearing also previous data
        return "redirect:/contact";
    }
}
