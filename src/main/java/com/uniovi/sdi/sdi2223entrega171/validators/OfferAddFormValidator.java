package com.uniovi.sdi.sdi2223entrega171.validators;

import com.uniovi.sdi.sdi2223entrega171.entities.Offer;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import com.uniovi.sdi.sdi2223entrega171.services.OffersService;
import com.uniovi.sdi.sdi2223entrega171.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OfferAddFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Offer.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Offer offer = (Offer) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");

        if (offer.getTitle().length()<5 || offer.getTitle().length()>20) {
            errors.rejectValue("title", "Error.offer.add.title.length");}
        if (offer.getDescription().length()<10 || offer.getDescription().length()>100) {
            errors.rejectValue("description", "Error.offer.add.description.length");}
        if (offer.getPrice()<0) {
            errors.rejectValue("price", "Error.offer.add.negativePrice");}

    }
}


