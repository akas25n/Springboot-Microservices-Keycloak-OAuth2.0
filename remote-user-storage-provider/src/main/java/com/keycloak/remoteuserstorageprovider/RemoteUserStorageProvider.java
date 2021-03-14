package com.keycloak.remoteuserstorageprovider;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;


public class RemoteUserStorageProvider implements UserStorageProvider,
        UserLookupProvider, CredentialInputValidator {

    private KeycloakSession keycloakSession;
    private ComponentModel componentModel;
    private RemoteUserApiService userApiService;

    public RemoteUserStorageProvider(KeycloakSession keycloakSession, ComponentModel componentModel, RemoteUserApiService userApiService) {
        this.keycloakSession = keycloakSession;
        this.componentModel = componentModel;
        this.userApiService = userApiService;
    }

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(String s, RealmModel realmModel) {
        return null;
    }

    @Override
    public UserModel getUserByUsername(String userName, RealmModel realmModel) {
        UserModel userModel = null;
        User user = userApiService.getUserDetails(userName);
        if (user != null){
            userModel = createUserModel(userName, realmModel);
        }
        return userModel;
    }

    private UserModel createUserModel(String userName, RealmModel realmModel){
        return new AbstractUserAdapter(keycloakSession, realmModel, componentModel) {
            @Override
            public String getUsername() {
                return userName;
            }
        };
    }

    @Override
    public UserModel getUserByEmail(String s, RealmModel realmModel) {
        return null;
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String credentialType) {
        if (!supportsCredentialType(credentialType)){
            return false;
        }
        return !getUserCredentialStore()
                .getStoredCredentialsByType(realmModel, userModel, credentialType).isEmpty();
    }

    private UserCredentialStore getUserCredentialStore(){
        return keycloakSession.userCredentialManager();
    }

    @Override
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        VerifyPasswordResponse verifyPasswordResponse = userApiService.verifyPassword(userModel.getUsername(),
                credentialInput.getChallengeResponse());

        if (verifyPasswordResponse == null){
            return false;
        }
        return true;
    }
}
