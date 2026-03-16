package com.veterinaria.vetapp.service;


import com.veterinaria.vetapp.model.Appointment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private final List<Appointment> appointments = new ArrayList<>();
    private Long nextId = 1L;

    public List<Appointment> findAll() {
        return appointments;
    }

    public void save(Appointment appointment) {
        appointment.setId(nextId++);
        appointments.add(appointment);
    }
}