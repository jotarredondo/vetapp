package com.veterinaria.vetapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.veterinaria.vetapp.controller.AppointmentRestController;
import com.veterinaria.vetapp.model.Appointment;
import com.veterinaria.vetapp.service.AppointmentService;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AppointmentRestControllerTest  {

    private final AppointmentService service = Mockito.mock(AppointmentService.class);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new AppointmentRestController(service)).build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(List.of(new Appointment()));

        mockMvc.perform(get("/api/appointments"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        Appointment appointment = new Appointment();

        Mockito.when(service.save(Mockito.any())).thenReturn(appointment);

        mockMvc.perform(post("/api/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/appointments/1"))
                .andExpect(status().isOk());
    }

}
