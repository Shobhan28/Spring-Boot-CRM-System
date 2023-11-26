package com.techhub.crm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ContactDto {

    private String contactId;
    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
    private String mailingStreet;
    private String companyName;
    private String description;
    private String leadSource;


}
