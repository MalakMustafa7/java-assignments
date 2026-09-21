package com.example.springlab.controller;

import com.example.springlab.dto.AuthorityRequest;
import com.example.springlab.dto.AuthorityResponse;
import com.example.springlab.service.AuthorityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorities")
@RequiredArgsConstructor
public class AuthorityController {
    private final AuthorityService authorityService;

    @PostMapping
    public ResponseEntity<AuthorityResponse> createAuthority(
            @Valid @RequestBody AuthorityRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorityService.createAuthority(request));
    }
}
