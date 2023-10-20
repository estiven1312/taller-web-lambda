package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.domain.model.Role;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.repository.RolRepository;
import com.lambda.pe.lambdaapp.repository.UserRepository;
import com.lambda.pe.lambdaapp.service.UserService;
import com.lambda.pe.lambdaapp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    private final RolRepository rolRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RolRepository rolRepository){
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Override
    @Transactional
    public Boolean validateUser(String username, String password, HttpSession httpSession) {
        Optional<User> user = userRepository.findUsuarioByUsernameAndPassword(username, password);
        if(user.isPresent()){
            httpSession.setAttribute(Constants.USER_KEY_SESSION.label, user.get());
            httpSession.setAttribute(Constants.FLAG_SESSION.label, true);
            return true;
        }
        return false;
    }


    @Override
    public void logout(HttpSession httpSession){
        httpSession.setAttribute(Constants.FLAG_SESSION.label, false);
        httpSession.removeAttribute(Constants.USER_KEY_SESSION.label);
        httpSession.invalidate();
    }

    @Override
    public void registerUser(String nombres, String apellidos, String telefono, String numeroDocumentoIdentificacion, String correo, String username, String password) {
        try{
            User user = new User();
            user.setNombres(nombres);
            user.setApellidos(apellidos);
            user.setTelefono(telefono);
            user.setNumeroDocumentoIdentificacion(numeroDocumentoIdentificacion);
            user.setCorreo(correo);
            user.setUsername(username);
            user.setPassword(password);
            Role role = rolRepository.findByNombre("COLABORADOR");
            user.setRoles(new HashSet<>());
            user.getRoles().add(role);
            userRepository.save(user);
        }catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
