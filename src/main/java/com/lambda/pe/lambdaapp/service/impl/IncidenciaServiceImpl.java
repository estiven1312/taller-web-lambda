package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.domain.model.Ambiente;
import com.lambda.pe.lambdaapp.domain.model.CatalogoDetalle;
import com.lambda.pe.lambdaapp.domain.model.IncidenciaAmbiente;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.repository.AmbienteRepository;
import com.lambda.pe.lambdaapp.repository.CatalogoDetalleRepository;
import com.lambda.pe.lambdaapp.repository.IncidenciaRepository;
import com.lambda.pe.lambdaapp.repository.UserRepository;
import com.lambda.pe.lambdaapp.service.EmailService;
import com.lambda.pe.lambdaapp.service.IncidenciaService;
import com.lambda.pe.lambdaapp.util.CatalogoEnum;
import com.lambda.pe.lambdaapp.util.CatalogoEstadosIncidenciaEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final CatalogoDetalleRepository catalogoDetalleRepository;

    private final UserRepository userRepository;
    private final AmbienteRepository ambienteRepository;
    private final EmailService emailService;

    public IncidenciaServiceImpl(IncidenciaRepository incidenciaRepository, CatalogoDetalleRepository catalogoDetalleRepository, UserRepository userRepository, AmbienteRepository ambienteRepository, EmailService emailService) {
        this.incidenciaRepository = incidenciaRepository;
        this.catalogoDetalleRepository = catalogoDetalleRepository;
        this.userRepository = userRepository;
        this.ambienteRepository = ambienteRepository;
        this.emailService = emailService;

    }

    @Override
    public void registerIncidencia(String descripcion, Long idAmbiente, User user) {
        IncidenciaAmbiente incidencia = new IncidenciaAmbiente();
        CatalogoDetalle estado = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.ESTADO_INCIDENCIA.getLabel(), CatalogoEstadosIncidenciaEnum.ESTADO_REGISTRADO.getLabel());
        Ambiente ambiente = ambienteRepository.getReferenceById(idAmbiente);
        incidencia.setAmbiente(ambiente);
        incidencia.setEstado(estado);
        incidencia.setDescripcion(descripcion);
        incidencia.setUsuarioEmisor(user);
        incidenciaRepository.save(incidencia);
        List<User> userAdmins = userRepository.findAdministrators();
        userAdmins.forEach(user1 -> {
            emailService.send("lambdaapp@gmail.com", user1.getCorreo(), "INCIDENCIA REGISTRADA",
                    "Se ha registrado una incidencia en " + ambiente.getNombreAmbiente() + " : " + incidencia.getDescripcion());
        });
    }

    @Override
    public void changeStateIncidencia(Long idIncidencia) {
        IncidenciaAmbiente incidencia = incidenciaRepository.getReferenceById(idIncidencia);
        String abreviaturaBuscar = incidencia.getEstado().getAbreviatura().equals(CatalogoEstadosIncidenciaEnum.ESTADO_REGISTRADO.getLabel()) ? CatalogoEstadosIncidenciaEnum.ESTADO_EN_PROCESO.getLabel() : CatalogoEstadosIncidenciaEnum.ESTADO_RESUELTO.getLabel();
        CatalogoDetalle catalogoDetalle = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.ESTADO_INCIDENCIA.getLabel(), abreviaturaBuscar);
        incidencia.setEstado(catalogoDetalle);
        incidenciaRepository.save(incidencia);

    }

    @Override
    public void deleteIncidencia(Long idIncidencia) {
        IncidenciaAmbiente incidencia = incidenciaRepository.getReferenceById(idIncidencia);
        CatalogoDetalle catalogoDetalle = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.ESTADO_INCIDENCIA.getLabel(), CatalogoEstadosIncidenciaEnum.ESTADO_ELIMINADO.getLabel());
        incidencia.setEstado(catalogoDetalle);
        incidenciaRepository.save(incidencia);
    }

    @Override
    public List<IncidenciaAmbiente> getReporteIncidencias() {
        return incidenciaRepository.findIncidenciasActivas();
    }
}
