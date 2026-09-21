package com.example.springlab.service;

import com.example.springlab.dto.AuthorityRequest;
import com.example.springlab.dto.AuthorityResponse;
import com.example.springlab.entity.Authority;
import com.example.springlab.exception.AuthorityAlreadyExistsException;
import com.example.springlab.mapper.AuthorityMapper;
import com.example.springlab.repository.AuthorityRepository;
import com.example.springlab.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    public AuthorityResponse createAuthority(AuthorityRequest request) {

        if (authorityRepository.existsByName(request.name())) {
            throw new AuthorityAlreadyExistsException(
                    String.format(ErrorMessages.AUTHORITY_ALREADY_EXISTS, request.name())
            );
        }

        Authority authority = authorityMapper.toEntity(request);

        authorityRepository.save(authority);

        return authorityMapper.toResponse(authority);
    }
}
