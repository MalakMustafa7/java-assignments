package com.example.springlab.service;

import com.example.springlab.dto.RoleRequest;
import com.example.springlab.dto.RoleResponse;
import com.example.springlab.entity.Authority;
import com.example.springlab.entity.Role;
import com.example.springlab.exception.AuthorityNotFoundException;
import com.example.springlab.exception.RoleAlreadyExistsException;
import com.example.springlab.mapper.RoleMapper;
import com.example.springlab.repository.AuthorityRepository;
import com.example.springlab.repository.RoleRepository;
import com.example.springlab.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final RoleMapper roleMapper;

    public RoleResponse createRole(RoleRequest request) {

        if (roleRepository.existsByName(request.name())) {
            throw new RoleAlreadyExistsException(
                    String.format(ErrorMessages.ROLE_ALREADY_EXISTS, request.name())
            );
        }

        Set<Authority> authorities =
                new HashSet<>(authorityRepository.findAllById(request.authorityIds()));

        if (authorities.size() != request.authorityIds().size()) {
            throw new AuthorityNotFoundException(
                    ErrorMessages.AUTHORITY_NOT_FOUND
            );
        }

        Role role = new Role();
        role.setName(request.name());
        role.setAuthorities(authorities);

        roleRepository.save(role);

        return roleMapper.toResponse(role);
    }
}
