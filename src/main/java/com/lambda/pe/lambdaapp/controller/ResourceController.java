package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.config.FileConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {
    private final FileConfig fileConfig;

    public ResourceController(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
        Path path = Paths.get(fileConfig.getSTORAGEPATH()).resolve(filename);
        if(!Files.exists(path)){
            return ResponseEntity.notFound().build();
        }
        Resource resource = new UrlResource(path.toUri());
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+resource.getFilename()+"\"")
                .header(HttpHeaders.CONTENT_TYPE,
                        Files.probeContentType(Paths.get(fileConfig.getSTORAGEPATH()).resolve(filename)))
                .header(HttpHeaders.CONTENT_LENGTH,
                        String.valueOf(resource.contentLength()))
                .body(resource);

    }
}
