package com.techhub.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leads")
public class Lead {

    @Id
    private String leadId; // Lead ID in Zoho CRM

    @Column(name = "First_Name", nullable = false)
    @Size(max = 20)
    private String firstName;

    @Column(name = "Last_Name", nullable = false)
    @Size(max = 20)
    private String lastName;

    @Email
    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "PhoneNo.", nullable = false, unique = true)
    private long mobile;

    @Size(max = 100)
    @Column(name = "Address")
    private String address;

    @Size(max = 100)
    @Column(name = "Company_Name")
    private String companyName;

    @Lob
    @Column(name = "Description")
    private String description; // Description/note field in Zoho CRM

    @Column(name = "Status")
    private String leadStatus; // Current status of the lead (e.g., New, Contacted, Qualified, etc.)

}
