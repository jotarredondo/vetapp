package com.veterinaria.vetapp.controller;

import com.veterinaria.vetapp.model.Appointment;
import com.veterinaria.vetapp.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.findAll());
        return "appointments";
    }

    @GetMapping("/appointments/new")
    public String showAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "appointment-form";
    }

    @PostMapping("/appointments/save")
    public String saveAppointment(Appointment appointment) {
        appointmentService.save(appointment);
        return "redirect:/appointments";
    }
}
