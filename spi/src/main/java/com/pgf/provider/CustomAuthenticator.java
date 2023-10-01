package com.pgf.provider;

import jakarta.ws.rs.core.Response;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;


public class CustomAuthenticator implements Authenticator {

    public static final String REGISTRATION_FORM = "register.ftl";

    @Override
    public void authenticate(AuthenticationFlowContext context) {
//        Response challenge = context.form()
//            .createForm(REGISTRATION_FORM);
//        context.challenge(challenge);
        context.success();
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        UserModel user = context.getUser();
        user.setSingleAttribute("taxId", "xd123");
        context.success();
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

