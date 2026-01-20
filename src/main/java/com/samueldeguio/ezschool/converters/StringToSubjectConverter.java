package com.samueldeguio.ezschool.converters;

import com.samueldeguio.ezschool.models.ContactRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSubjectConverter implements Converter<String, ContactRequest.Subject> {

    @Override
    public ContactRequest.Subject convert(String subject) {
        try{
            return ContactRequest.Subject.valueOf(subject.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
