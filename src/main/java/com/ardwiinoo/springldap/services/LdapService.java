package com.ardwiinoo.springldap.services;

public interface LdapService {
    public boolean authenticate(String username, String password);
}
