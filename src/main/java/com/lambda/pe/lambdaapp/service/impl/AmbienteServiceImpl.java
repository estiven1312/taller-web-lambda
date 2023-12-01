package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.config.FileConfig;
import com.lambda.pe.lambdaapp.domain.dto.AmbienteDTO;
import com.lambda.pe.lambdaapp.domain.model.Ambiente;
import com.lambda.pe.lambdaapp.domain.model.CatalogoDetalle;
import com.lambda.pe.lambdaapp.domain.util.Response;
import com.lambda.pe.lambdaapp.repository.AmbienteRepository;
import com.lambda.pe.lambdaapp.repository.CatalogoDetalleRepository;
import com.lambda.pe.lambdaapp.service.AmbienteService;
import com.lambda.pe.lambdaapp.util.CatalogoEnum;
import com.lambda.pe.lambdaapp.util.CatalogoEstadosEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class AmbienteServiceImpl implements AmbienteService {
    private final AmbienteRepository ambienteRepository;
    private final CatalogoDetalleRepository catalogoDetalleRepository;
    private final FileConfig fileConfig;

    public AmbienteServiceImpl(AmbienteRepository ambienteRepository, CatalogoDetalleRepository catalogoDetalleRepository, FileConfig fileConfig) {
        this.ambienteRepository = ambienteRepository;
        this.catalogoDetalleRepository = catalogoDetalleRepository;
        this.fileConfig = fileConfig;
    }

    @Override
    @Transactional
    public Response saveAmbiente(AmbienteDTO ambienteDTO) {
        Response response = new Response();
        try {
            CatalogoDetalle estado = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.ESTADO_AUDITORIA.getLabel(), CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel());
            CatalogoDetalle tipoAmbiente = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.TIPO_AMBIENTE.getLabel(), ambienteDTO.getTipoAmbiente());
            Ambiente ambiente = new Ambiente();
            if(ambienteDTO.getId() != null){
                ambiente = ambienteRepository.getReferenceById(ambienteDTO.getId());
            }
            if(ambienteDTO.getImagen() != null && !ambienteDTO.getImagen().isEmpty()){
                System.out.println("==================="+ambienteDTO.getImagen().getOriginalFilename());
                String uuid = UUID.randomUUID().toString();
                String filename = uuid +
                        ambienteDTO.getImagen().getOriginalFilename().substring(ambienteDTO.getImagen().getOriginalFilename().lastIndexOf("."));
                if(Files.notExists(Paths.get(fileConfig.getSTORAGEPATH()))){
                    Files.createDirectories(Paths.get(fileConfig.getSTORAGEPATH()));
                }
                Files.copy(ambienteDTO.getImagen().getInputStream(), Paths.get(fileConfig.getSTORAGEPATH()).resolve(filename));
                ambiente.setUrlImagen(filename);
            }
            ambiente.setTipoAmbiente(tipoAmbiente);
            ambiente.setEstado(estado);
            ambiente.setNumeroPiso(ambienteDTO.getNumeroPiso());
            ambiente.setIndicadorTelevisor(ambienteDTO.getIndicadorTelevisor());
            ambiente.setCantidadAsientos(ambienteDTO.getCantidadAsientos());
            ambiente.setNombreAmbiente(ambienteDTO.getNombreAmbiente());
            ambiente.setIndicadorProyector(ambienteDTO.getIndicadorProyector());
            ambiente.setComputadoraIndicador(ambienteDTO.getComputadoraIndicador());
            ambienteRepository.save(ambiente);
            response.setId(String.valueOf(ambiente.getId()));
            response.setMessage("OK");
            response.setHttpStatus(HttpStatus.OK);
            return response;
        } catch (Exception ex){
            response.setId(null);
            response.setMessage("FAIL");
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }

    }
    @Override
    public List<Ambiente> findAll(String catalogo, String tipo, String catalogoEstado, String estado){
        return ambienteRepository.findAllAmbientesByTipo(catalogo, tipo , catalogoEstado, estado );
    }

    @Override
    public List<Ambiente> findAmbientesActivos(){
        return ambienteRepository.findAmbientesActivos();
    }
    @Override
    public Response deleteAmbiente(Long id) {
        try{
            CatalogoDetalle catalogoDetalle = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.ESTADO_AUDITORIA.getLabel(), CatalogoEstadosEnum.ESTADO_ELIMINADO.getLabel());
            Ambiente ambiente = ambienteRepository.getReferenceById(id);
            ambiente.setEstado(catalogoDetalle);
            ambienteRepository.save(ambiente);
            Response response = new Response();
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("OK");
            response.setId(String.valueOf(id));
            return response;
        } catch (Exception ex){
            Response response = new Response();
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("FAIL");
            response.setId(String.valueOf(id));
            return response;
        }
    }
    @Override

    public Ambiente getById(Long id){
        return ambienteRepository.getReferenceById(id);
    }
}
