package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.config.FileConfig;
import com.lambda.pe.lambdaapp.domain.dto.ComunicadoDTO;
import com.lambda.pe.lambdaapp.domain.model.Comunicado;
import com.lambda.pe.lambdaapp.domain.util.Response;
import com.lambda.pe.lambdaapp.repository.CatalogoDetalleRepository;
import com.lambda.pe.lambdaapp.repository.ComunicadoRepository;
import com.lambda.pe.lambdaapp.service.ComunicadoService;
import com.lambda.pe.lambdaapp.util.CatalogoEnum;
import com.lambda.pe.lambdaapp.util.CatalogoEstadosEnum;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ComunicadoServiceImpl implements ComunicadoService {
    private final ComunicadoRepository comunicadoRepository;
    private final FileConfig fileConfig;
    private final CatalogoDetalleRepository catalogoDetalleRepository;

    public ComunicadoServiceImpl(ComunicadoRepository comunicadoRepository, FileConfig fileConfig, CatalogoDetalleRepository catalogoDetalleRepository) {
        this.comunicadoRepository = comunicadoRepository;
        this.fileConfig = fileConfig;
        this.catalogoDetalleRepository = catalogoDetalleRepository;
    }


    @Override
    public void saveComunicado(String mensajeComunicado, String urlImagen, Date fechaComunicado) {

    }


    @Override
    public Response deleteComunicado(Long idComunicado){
        try {
            Response response = new Response();
            comunicadoRepository.deleteById(idComunicado);
            response.setId(String.valueOf(idComunicado));
            response.setMessage("OK");
            response.setHttpStatus(HttpStatus.OK);
            return response;
        } catch (Exception ex){
            Response response = new Response();
            response.setId(String.valueOf(idComunicado));
            response.setMessage("FAIL");
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    @Override
    public Response saveComunicado(ComunicadoDTO comunicadoDTO, HttpSession httpSession) {
        HashMap<String, String> hashMap = new HashMap<>();
        try{
            Comunicado comunicado = new Comunicado();
            if(comunicadoDTO.getId() != null){
                comunicado = comunicadoRepository.getReferenceById(comunicadoDTO.getId());
            }
            if(comunicadoDTO.getMultipartFile() != null && !comunicadoDTO.getMultipartFile().isEmpty()){
                System.out.println("==================="+comunicadoDTO.getMultipartFile().getOriginalFilename());
                String uuid = UUID.randomUUID().toString();
                String filename = uuid +
                        comunicadoDTO.getMultipartFile().getOriginalFilename().substring(comunicadoDTO.getMultipartFile().getOriginalFilename().lastIndexOf("."));
                comunicado.setUrlImagen(filename);
                if(Files.notExists(Paths.get(fileConfig.getSTORAGEPATH()))){
                    Files.createDirectories(Paths.get(fileConfig.getSTORAGEPATH()));
                }
                Files.copy(comunicadoDTO.getMultipartFile().getInputStream(), Paths.get(fileConfig.getSTORAGEPATH()).resolve(filename));
            }
            comunicado.setDescripcion(comunicadoDTO.getDescripcion());
            comunicado.setFechaComunicado(new Date());
            comunicado.setEstado(catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.ESTADO_AUDITORIA.getLabel(), CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel()));
            comunicadoRepository.saveAndFlush(comunicado);
            Response response = new Response();
            response.setId(String.valueOf(comunicado.getId()));
            response.setMessage("OK");
            response.setHttpStatus(HttpStatus.OK);
            return response;
        }catch (Exception ex){
            Response response = new Response();
            response.setId(null);
            response.setMessage("OK");
            response.setHttpStatus(HttpStatus.OK);
            return response;
        }
    }
    @Override
    public List<Comunicado> listAllComunicados() {
        return comunicadoRepository.findAllComunicadosActivos(CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel());
    }
    @Override
    public List<Comunicado> listAllComunicadosSlider() {
        return comunicadoRepository.findSliderComunicados(CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel(), PageRequest.of(0,5));
    }
    @Override
    public ResponseEntity<Comunicado> getById(Long id){
        try {
            Comunicado comunicado = comunicadoRepository.getReferenceById(id);
            if(comunicado != null){
                return new ResponseEntity<>(new Comunicado(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(comunicado, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(new Comunicado(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}
