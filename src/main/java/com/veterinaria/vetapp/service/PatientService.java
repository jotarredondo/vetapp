package com.veterinaria.vetapp.service;

import com.veterinaria.vetapp.model.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final List<Patient> patients = new ArrayList<>();
    private Long nextId = 1L;

    public List<Patient> findAll() {
        return patients;
    }

    public void save(Patient patient) {
        patient.setId(nextId++);
        patients.add(patient);
    }
}
