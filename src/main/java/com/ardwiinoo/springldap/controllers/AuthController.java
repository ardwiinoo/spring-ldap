package com.ardwiinoo.springldap.controllers;

import com.ardwiinoo.springldap.models.local.dto.UserLoginRequest;
import com.ardwiinoo.springldap.services.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/auth"
)
public class AuthController {

    private final LdapService ldapService;

    @Autowired
    public AuthController(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/login"
    )
    public ResponseEntity<String> loginHandler(
            @RequestBody UserLoginRequest request
    ) {
        boolean result = ldapService.authenticate(request.getUsername(), request.getPassword());

        if (result) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("FAILED");
        }
    }
}
