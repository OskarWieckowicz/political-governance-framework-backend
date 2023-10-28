package com.pgf.politicalgovernanceframeworkbackend.fto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DocumentFto {
    private String date;
    private float amount;
    private String type;
    private MultipartFile file;
}
