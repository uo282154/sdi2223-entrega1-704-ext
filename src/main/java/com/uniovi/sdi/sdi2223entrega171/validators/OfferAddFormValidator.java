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
    @Autowired
    private OffersService offersService;
    @Override
    public boolean supports(Class<?> aClass) {
        return Offer.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Offer offer = (Offer) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");

        if (offersService.getOfferByTitle(offer.getTitle()) != null) {
            errors.rejectValue("title", "Error.offer.add.titleExists");}
        if (offer.getPrice()<0) {
            errors.rejectValue("price", "Error.offer.add.negativePrice");}

    }
}


