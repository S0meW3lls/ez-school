package com.samueldeguio.ezschool.models;

public class ContactRequest {

    private String name;
    private String email;
    private String subject;
    private String message;

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubject() { return this.subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
        return "ContactRequest{" +
                "name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", subject='" + this.subject + '\'' +
                ", message='" + this.message + '\'' +
                '}';
    }
}
