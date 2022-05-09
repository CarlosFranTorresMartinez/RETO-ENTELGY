package com.entelgy.retotecnico.business;

import org.springframework.http.ResponseEntity;

public interface UserRepository {
    ResponseEntity<String> findUserResponse();
}
