package com.ardwiinoo.springldap.services.impl;

import com.ardwiinoo.springldap.services.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class LdapServiceImpl implements LdapService {

    private final LdapTemplate ldapTemplate;

    @Autowired
    public LdapServiceImpl(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public boolean authenticate(String username, String password) {
        LdapQuery query = LdapQueryBuilder.query().where("uid").is(username);

        System.out.println("LDAP filter: " + query.filter().encode());

        return ldapTemplate.authenticate(query.base(), query.filter().encode(), password);
    }
}
