package com.estevam.org.br.controller;

import com.estevam.org.br.dto.UserDTO;

import javax.validation.Valid;

import com.estevam.org.br.domain.User;
import com.estevam.org.br.exceptions.EmailExistsException;
import com.estevam.org.br.exceptions.InvalidPasswordException;
import com.estevam.org.br.exceptions.ObjectNotFoundException;
import com.estevam.org.br.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDTO dto) {

        var pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&])(?=\\S+$).{6,12}";

        if (!dto.getPassword().matches(pattern))
            throw new InvalidPasswordException(
                    "Senha deve conter entre 6 e 12 dígitos, uma letra minúscula, uma letra maiúscula, um número e um caractere especial (Ex: @#$%&)");

        if (userRepository.existsByEmail(dto.getEmail()))
            throw new EmailExistsException("Email já cadastrado");

        var user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password) {

        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário e/ou senha inválido"));

        if (!new BCryptPasswordEncoder().matches(password, user.getPassword()))
            throw new ObjectNotFoundException("Usuário e/ou senha inválido");

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
