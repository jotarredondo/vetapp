package com.veterinaria.vetapp.repository;

import com.veterinaria.vetapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}