package com.pgf.politicalgovernanceframeworkbackend.fto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorFto {
    private int statusCode;
    private Date timestamp;
    private String message;
}
