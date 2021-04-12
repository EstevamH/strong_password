package com.estevam.org.br.repository;

import org.springframework.stereotype.Repository;

import com.estevam.org.br.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
