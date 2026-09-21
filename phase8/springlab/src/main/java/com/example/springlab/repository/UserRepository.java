package com.example.springlab.repository;

import com.example.springlab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   @Query("""
    select distinct u
    from User u
    join fetch u.role r
    join fetch r.authorities
    where u.username = :username
""")
   Optional<User> findByUsernameWithAuthorities(String username);
   boolean existsByEmail(String email);
}
