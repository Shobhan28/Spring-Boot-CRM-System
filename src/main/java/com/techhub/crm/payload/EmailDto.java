package com.techhub.crm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String emailId;
    private String from;
    private String to;
    private String subject;
    private String message;
}
