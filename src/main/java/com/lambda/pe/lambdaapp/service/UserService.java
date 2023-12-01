package com.lambda.pe.lambdaapp.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lambda.pe.lambdaapp.domain.dto.UserDTO;
import com.lambda.pe.lambdaapp.domain.model.Role;
import com.lambda.pe.lambdaapp.domain.model.User;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    List<User> findAll();

    Boolean validateUser(String username, String password, HttpSession httpSession);

    List<User> findActivos();

    void logout(HttpSession httpSession);

    void registerUser(
            String nombres,
            String apellidos,
            String telefono,
            String numeroDocumentoIdentificacion,
            String correo,
            String username,
            String password);

    void deleteUser(Long id);

    Map<String, String> registerUser(UserDTO userDTO, HttpSession httpSession);
}
