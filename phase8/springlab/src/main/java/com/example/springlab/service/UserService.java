package com.example.springlab.service;

import com.example.springlab.adapter.SecurityUser;
import com.example.springlab.dto.UserRequest;
import com.example.springlab.dto.UserResponse;
import com.example.springlab.entity.Authority;
import com.example.springlab.entity.Role;
import com.example.springlab.entity.User;
import com.example.springlab.exception.RoleNotFoundException;
import com.example.springlab.exception.UserAlreadyExistsException;
import com.example.springlab.mapper.UserMapper;
import com.example.springlab.repository.RoleRepository;
import com.example.springlab.repository.UserRepository;
import com.example.springlab.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
       User user = userRepository.findByUsernameWithAuthorities(username)
               .orElseThrow(()->new UsernameNotFoundException(
                       String.format(ErrorMessages.USER_NOT_FOUND,username)
               ));
       return new SecurityUser(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) {
//
//        System.out.println("========== AUTHENTICATION START ==========");
//        System.out.println("Username received: " + username);
//
//        try {
//            System.out.println("BEFORE QUERY");
//
//            User user = userRepository.findByUsernameWithAuthorities(username)
//                    .orElseThrow(() -> {
//                        System.out.println("USER NOT FOUND");
//                        return new UsernameNotFoundException(
//                                String.format(ErrorMessages.USER_NOT_FOUND, username)
//                        );
//                    });
//
//            System.out.println("AFTER QUERY");
//            System.out.println("USER FOUND: " + user.getUsername());
//
//            System.out.println("BEFORE SECURITY USER");
//
//            SecurityUser securityUser = new SecurityUser(user);
//
//            System.out.println("SECURITY USER CREATED");
//
//            return securityUser;
//
//        } catch (Exception e) {
//
//            System.out.println("========== EXCEPTION ==========");
//            System.out.println("Exception type: " + e.getClass().getName());
//            System.out.println("Exception message: " + e.getMessage());
//
//            e.printStackTrace();
//
//            throw e;
//        }
//    }


    public UserResponse createUser(UserRequest request){
        if (userRepository.existsByEmail(request.email())){
            throw new UserAlreadyExistsException(
                    String.format(ErrorMessages.USER_ALREADY_EXISt,request.email()));
        }

        Role role = roleRepository.findById(request.roleId())
                .orElseThrow(()->new RoleNotFoundException(
                        String.format(ErrorMessages.ROLE_NOT_FOUND,request.roleId())
                ));

        String encodedPassword = passwordEncoder.encode(request.password());
        User user = userMapper.toEntity(request);
        user.setPassword(encodedPassword);
        user.setRole(role);
        user.setEnabled(true);

        userRepository.save(user);
        return userMapper.toResponse(user);
    }
}
