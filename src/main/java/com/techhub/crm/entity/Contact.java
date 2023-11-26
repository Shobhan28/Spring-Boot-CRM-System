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
@Table(name = "contacts")
public class Contact {

    @Id
    private String contactId; //CRM contact ID

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
    @Column(name = "Mailing_Street")
    private String mailingStreet; // Street address for mailing

    @Size(max = 50)
    @Column(name = "Company_Name")
    private String companyName; // Account name in Zoho CRM

    @Lob
    @Column(name = "Description")
    private String description; // Description/note field in Zoho CRM

    @Column(name = "Lead_Source")
    private String leadSource; // Lead source for the contact

}
