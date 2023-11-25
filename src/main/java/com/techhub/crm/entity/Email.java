package com.techhub.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "emails")
public class Email {

    @Id
    private String eid;
    @Column(name = "from_email")
    private String from;
    @Column(name = "to_email")
    private String to;
    private String subject;
    private String message;
}
