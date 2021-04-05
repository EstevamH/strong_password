package com.estevam.org.br.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email Obrigatório")
    private String email;

    private String password;
}
