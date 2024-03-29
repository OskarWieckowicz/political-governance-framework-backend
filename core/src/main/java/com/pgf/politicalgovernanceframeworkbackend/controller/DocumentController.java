package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.converter.DocumentFtoToDtoConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.DocumentDto;
import com.pgf.politicalgovernanceframeworkbackend.fto.DocumentFto;
import com.pgf.politicalgovernanceframeworkbackend.service.DocumentService;
import com.pgf.politicalgovernanceframeworkbackend.service.S3Service;
import com.pgf.politicalgovernanceframeworkbackend.service.impl.DocumentServiceImpl;
import com.pgf.politicalgovernanceframeworkbackend.service.impl.S3ServiceImpl;
import jakarta.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@RestController
@RequestMapping("/documents")
@AllArgsConstructor
class DocumentController {

    private final DocumentService service;
    private final S3Service s3Service;
    private final DocumentFtoToDtoConverter documentFtoToDtoConverter;

    @GetMapping
    List<DocumentDto> getDocuments(Principal principal) {
        return service.getAllDocuments(principal.getName());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<DocumentDto> addDocument(
        @ModelAttribute DocumentFto documentFto,
        Principal principal
    ) {
        String key = s3Service.uploadFile(documentFto.getFile());
        if (Objects.nonNull(key)) {

            DocumentDto document = this.service.createDocument(
                documentFtoToDtoConverter.convertTo(documentFto, key),
                principal.getName()
            );
            return ResponseEntity.ok(document);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/download")
    ResponseEntity<InputStreamResource> downloadFile(@NotNull(message = "Key can't be null") @RequestParam String key, Principal principal) {
        if (service.hasDocument(key, principal.getName())) {
            ResponseInputStream<GetObjectResponse> responseInputStream = s3Service.downloadFile(key);
            GetObjectResponse objectResponse = responseInputStream.response();
            InputStreamResource resource = new InputStreamResource(responseInputStream);
            HttpHeaders headers = new HttpHeaders();
            MediaType mediaType = MediaType.valueOf(objectResponse.contentType());
            headers.setContentType(mediaType);
            headers.setContentDispositionFormData("attachment", key);
            return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{key}")
    ResponseEntity<Void> deleteFile(@NotNull(message = "Key can't be null") @PathVariable String key) {
        Boolean result = s3Service.deleteFile(key);
        if(result) {
            service.deleteDocument(key);
        }
        return ResponseEntity.noContent().build();
    }
}
