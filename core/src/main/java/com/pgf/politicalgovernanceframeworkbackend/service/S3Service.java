package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.exception.FileUploadException;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    private final S3Client s3Client;

    public String uploadFile(MultipartFile file) {
        log.info("Uploading file...");
        String s3Key = UUID.randomUUID() + "-" + file.getOriginalFilename();
        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(s3Key)
            .contentType(detectContentType(file))
            .build();
        try {
            PutObjectResponse putObjectResponse = s3Client.putObject(objectRequest, RequestBody.fromBytes(file.getBytes()));
            return putObjectResponse.sdkHttpResponse().isSuccessful() ? s3Key : null;
        } catch(IOException exception) {
            throw new FileUploadException("Error when reading file's bytes");
        }
    }

    public ResponseInputStream<GetObjectResponse> downloadFile(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();

        return s3Client.getObject(getObjectRequest);
    }

    private String detectContentType(MultipartFile file) {
        try {
            Tika tika = new Tika();
            return tika.detect(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Failed to detect content type: " + e.getMessage());
        }
    }
}
