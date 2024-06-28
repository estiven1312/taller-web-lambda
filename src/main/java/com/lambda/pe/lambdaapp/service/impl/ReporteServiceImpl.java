package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.config.FileConfig;
import com.lambda.pe.lambdaapp.domain.model.Reporte;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.remote.ServiceGenerator;
import com.lambda.pe.lambdaapp.remote.ValidateImageRequest;
import com.lambda.pe.lambdaapp.remote.response.ResponseValidation;
import com.lambda.pe.lambdaapp.repository.ReporteRepository;
import com.lambda.pe.lambdaapp.service.ReporteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReporteServiceImpl implements ReporteService {
    private final ReporteRepository reporteRepository;
    private final ServiceGenerator serviceGenerator;
    private final FileConfig fileConfig;
    @Override
    public void registerReport(MultipartFile multipartFile, String referencia, String comentario, User user) throws IOException {
        ValidateImageRequest validateImageRequest = serviceGenerator.createService(ValidateImageRequest.class);
        File file = convertMultiPartFileToFile(multipartFile);
        RequestBody requestFile = RequestBody.create(MediaType.parse(multipartFile.getContentType()), file);
        MultipartBody.Part imageBody = MultipartBody.Part.createFormData("file", multipartFile.getName(), requestFile);
        Call<ResponseValidation> call = validateImageRequest.validateImage(imageBody);
        Response<ResponseValidation> responseRetrofit = call.execute();
        ResponseValidation validationResult = responseRetrofit.body();
        log.info(responseRetrofit.toString());
        if(!validateImage(multipartFile)){
            throw new RuntimeException("Invalid image");
        }

        if(!validationResult.getPrediction().equals("trash")){
            throw new RuntimeException("Not trash");
        }
        Reporte reporte = createReport(multipartFile, comentario, referencia, user.getUsername());
        reporteRepository.save(reporte);
    }
    @Override
    public List<Reporte> listReports(){
        return reporteRepository.findAll();
    }
    private Reporte createReport(MultipartFile file, String comentario, String referencia, String username) throws IOException {
        Reporte reporte = new Reporte();
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        reporte.setRutaImagen(filename);

        if(Files.notExists(Paths.get(fileConfig.getSTORAGEPATH()))){
            Files.createDirectories(Paths.get(fileConfig.getSTORAGEPATH()));
        }
        Files.copy(file.getInputStream(), Paths.get(fileConfig.getSTORAGEPATH()).resolve(filename));

        reporte.setComentario(comentario);
        reporte.setReferencia(referencia);
        reporte.setUsuario(username);

        return reporte;
    }
    private   File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    private boolean validateImage(MultipartFile imagen) {
        String contentType = imagen.getContentType();
        return contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/jpg");
    }
}
