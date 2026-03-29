package com.veterinaria.vetapp.repository;

import com.veterinaria.vetapp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
