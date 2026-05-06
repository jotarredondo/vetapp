package com.veterinaria.vetapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.veterinaria.vetapp.controller.PatientRestController;
import com.veterinaria.vetapp.model.Patient;
import com.veterinaria.vetapp.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PatientRestControllerTest {

    private final PatientService service = Mockito.mock(PatientService.class);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new PatientRestController(service)).build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(List.of(new Patient()));

        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        Patient patient = new Patient();

        Mockito.when(service.save(Mockito.any())).thenReturn(patient);

        mockMvc.perform(post("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/patients/1"))
                .andExpect(status().isOk());
    }
}
