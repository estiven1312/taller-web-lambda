package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.config.FileConfig;
import com.lambda.pe.lambdaapp.domain.dto.UserDTO;
import com.lambda.pe.lambdaapp.domain.model.CatalogoDetalle;
import com.lambda.pe.lambdaapp.domain.model.Role;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.repository.CatalogoDetalleRepository;
import com.lambda.pe.lambdaapp.repository.RolRepository;
import com.lambda.pe.lambdaapp.repository.UserRepository;
import com.lambda.pe.lambdaapp.service.EmailService;
import com.lambda.pe.lambdaapp.service.UserService;
import com.lambda.pe.lambdaapp.util.CatalogoEnum;
import com.lambda.pe.lambdaapp.util.CatalogoEstadosUser;
import com.lambda.pe.lambdaapp.util.Constants;
import com.lambda.pe.lambdaapp.util.PasswordUtil;
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

    private final CatalogoDetalleRepository catalogoDetalleRepository;

    private final EmailService emailService;

    private final RolRepository rolRepository;
    @Autowired
    public UserServiceImpl(FileConfig fileConfig, UserRepository userRepository, CatalogoDetalleRepository catalogoDetalleRepository, EmailService emailService, RolRepository rolRepository){
        this.fileConfig = fileConfig;
        this.userRepository = userRepository;
        this.catalogoDetalleRepository = catalogoDetalleRepository;
        this.emailService = emailService;
        this.rolRepository = rolRepository;
    }
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Override
    @Transactional
    public Boolean validateUser(String username, String password, HttpSession httpSession) {
        Optional<User> user = userRepository.findUsuarioByUsernameAndPassword(username);
        if(user.isPresent()
                && PasswordUtil.checkPassword(password, user.get().getPassword())
                && user.get().getEstadoUsuario().getAbreviatura().equals(CatalogoEstadosUser.ESTADO_ACTIVO.getLabel())){

            httpSession.setAttribute(Constants.USER_KEY_SESSION.label, user.get());
            httpSession.setAttribute(Constants.FLAG_SESSION.label, true);
            return true;
        }
        return false;
    }
    @Override
    public List<User> findActivos(){
        return userRepository.findActivosOrBlocked();
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
    public void deleteUser(Long id) {
        try{
            User user = userRepository.getReferenceById(id);
            CatalogoDetalle estado = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.ESTADO_USUARIO.getLabel(), CatalogoEstadosUser.ESTADO_ELIMINADO.getLabel());
            user.setEstadoUsuario(estado);
            userRepository.save(user);
        }catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
    }
    @Override
    public Map<String, String> registerUser(UserDTO userDTO, HttpSession httpSession) {
        HashMap<String, String> hashMap = new HashMap<>();
        Boolean isNew = true;
        try{
            User user = new User();
            if(userDTO.getId() != null){
                user = userRepository.getReferenceById(userDTO.getId());
                isNew = false;
            }
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
            if(userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()){
                user.setPassword(PasswordUtil.hashPassword(userDTO.getPassword()));
            }
            if(isNew){
                Role role = rolRepository.getReferenceById(userDTO.getRol());
                CatalogoDetalle catalogoEstadosUser = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.ESTADO_USUARIO.getLabel(), CatalogoEstadosUser.ESTADO_ACTIVO.getLabel());
                CatalogoDetalle tipoDocIdentidad = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.TIPO_DOCUMENTO.getLabel(), userDTO.getTipoIdentificacion());
                String password = PasswordUtil.generatePassword();
                user.setRol(role);
                user.setEstadoUsuario(catalogoEstadosUser);
                user.setTipoDocIdentificacion(tipoDocIdentidad);
                user.setPassword(PasswordUtil.hashPassword(password));
                emailService.send(
                        "lambdaapp@gmail.com",
                        user.getCorreo(),
                        "CREACION DE NUEVO USUARIO",
                        "SE HA CREADO UN NUEVO USUARIO CON USER: "+ user.getUsername() + " Y CONTRASEÑA: "+ password + ". \nSe recomienda cammbiar la contraseña luego de ingresar");
            }
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
