package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.config.FileConfig;
import com.lambda.pe.lambdaapp.domain.dto.UserDTO;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final FileConfig fileConfig;

    private final UserRepository userRepository;

    private final RolRepository rolRepository;
    @Autowired
    public UserServiceImpl(FileConfig fileConfig, UserRepository userRepository, RolRepository rolRepository){
        this.fileConfig = fileConfig;
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
            user.setRol(role);
            userRepository.save(user);
        }catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    @Override
    public Map<String, String> registerUser(UserDTO userDTO, HttpSession httpSession) {
        HashMap<String, String> hashMap = new HashMap<>();
        try{
            User user = userRepository.getReferenceById(userDTO.getId());
            if(userDTO.getMultipartFile() != null && !userDTO.getMultipartFile().isEmpty()){
                String uuid = UUID.randomUUID().toString();
                String filename = uuid +
                        userDTO.getMultipartFile().getOriginalFilename().substring(userDTO.getMultipartFile().getOriginalFilename().lastIndexOf("."));
                user.setRutaFoto(filename);
                if(Files.notExists(Paths.get(fileConfig.getSTORAGEPATH()))){
                    Files.createDirectories(Paths.get(fileConfig.getSTORAGEPATH()));
                }
                Files.copy(userDTO.getMultipartFile().getInputStream(), Paths.get(fileConfig.getSTORAGEPATH()).resolve(filename));
            }
            user.setNombres(userDTO.getNombres());
            user.setApellidos(userDTO.getApellidos());
            user.setTelefono(userDTO.getTelefono());
            user.setNumeroDocumentoIdentificacion(userDTO.getNumeroDocumentoIdentificacion());
            user.setCorreo(userDTO.getCorreo());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
            httpSession.setAttribute(Constants.USER_KEY_SESSION.label, user);
            hashMap.put("STATUS", "OK");
            return hashMap;
        }catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
            hashMap.put("STATUS", "FAIL");
            return hashMap;
        }
    }
}
