package com.pgf.politicalgovernanceframeworkbackend.service;

import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public interface S3Service {
    String uploadFile(MultipartFile file);
    ResponseInputStream<GetObjectResponse> downloadFile(String key);
}
