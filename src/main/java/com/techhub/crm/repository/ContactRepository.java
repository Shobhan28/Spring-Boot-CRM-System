package com.techhub.crm.repository;

import com.techhub.crm.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,String> {


}
