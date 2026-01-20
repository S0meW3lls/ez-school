package com.samueldeguio.ezschool.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Subject is mandatory")
    private Subject subject;

    @NotBlank(message = "Message is mandatory")
    private String message;

    public enum Subject {
        GENERAL("general"),
        COURSES("courses"),
        MENTOR("mentor"),
        BUSINESS("business"),
        SUPPORT("support"),
        OTHER("other");

        private final String subject;

        Subject(final String subject) {
            this.subject = subject;
        }

        @Override
        public String toString() {
            return this.subject;
        }
    }
}
