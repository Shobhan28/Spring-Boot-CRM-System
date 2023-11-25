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
    private String lid;

    @Column(name = "first_name", nullable = false)
    @Size(max = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 20)
    private String lastName;

    @Email
    private String email;

    @Column(name = "mobile", nullable = false,unique = true)
    private long mobile;

    @Column(name = "lead_type", nullable = false)
    private String leadType;

    @Size(max = 100)
    private String address;

    @Size(max = 50)
    private String designation;

    @Size(max = 50)
    private String company;

    @Lob
    private String note;


}
