package com.techhub.crm.repository;

import com.techhub.crm.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeadRepository extends JpaRepository <Lead, String> {

    // before saving the lead, i want to check whether that email exists
    Optional<Lead> findByEmail(String email);

    Optional<Lead> findByMobile(long mobile);

    boolean existsByEmail(String email);

    boolean existsByMobile(long mobile);

}
