package com.techhub.crm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeadDto {

private String leadId;
private String firstName;
private String lastName;
private String email;
private long mobile;
private String address;
private String companyName;
private String description;
private String leadStatus;
}
