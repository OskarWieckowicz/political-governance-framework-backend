package com.pgf.provider;

import jakarta.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;
import org.keycloak.authentication.FormAction;
import org.keycloak.authentication.FormContext;
import org.keycloak.authentication.ValidationContext;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.FormMessage;
import org.keycloak.services.validation.Validation;

public class CustomAttributesValidator implements FormAction {
    @Override
    public void buildPage(FormContext formContext, LoginFormsProvider loginFormsProvider) {

    }

    @Override
    public void validate(ValidationContext validationContext) {
        MultivaluedMap<String, String> formData = validationContext.getHttpRequest().getDecodedFormParameters();
        List<FormMessage> errors = new ArrayList<>();

        String mobileNumber = formData.getFirst("user.attributes.mobile");
        if (Validation.isBlank(mobileNumber)) {
            errors.add(new FormMessage("mobile", "missingMobile"));
        } else if(!mobileNumber.matches("^\\+(?:[0-9] ?){6,14}[0-9]$")) {
            errors.add(new FormMessage("mobile", "invalidMobileFormat"));
        }

        if (Validation.isBlank(formData.getFirst(("user.attributes.city")))) {
            errors.add(new FormMessage("city", "missingCity"));
        }

        if (Validation.isBlank(formData.getFirst(("user.attributes.country")))) {
            errors.add(new FormMessage("country", "missingCountry"));
        }

        if (Validation.isBlank(formData.getFirst(("user.attributes.street")))) {
            errors.add(new FormMessage("street", "missingStreet"));
        }

        if (Validation.isBlank(formData.getFirst(("user.attributes.postalCode")))) {
            errors.add(new FormMessage("postalCode", "missingPostalCode"));
        }

        if (!errors.isEmpty()) {
            validationContext.validationError(formData, errors);
        } else {
            validationContext.success();
        }
    }

    @Override
    public void success(FormContext formContext) {

    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        return false;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    @Override
    public void close() {

    }
}
