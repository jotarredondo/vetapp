package com.veterinaria.vetapp;

import com.veterinaria.vetapp.model.Patient;
import com.veterinaria.vetapp.repository.PatientRepository;
import com.veterinaria.vetapp.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PatientServiceTest {

    private final PatientRepository repository = Mockito.mock(PatientRepository.class);
    private final PatientService service = new PatientService(repository);

    @Test
    void testFindAll() {
        Mockito.when(repository.findAll()).thenReturn(List.of(new Patient()));

        List<Patient> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testSave() {
        Patient patient = new Patient();
        Mockito.when(repository.save(patient)).thenReturn(patient);

        Patient result = service.save(patient);

        assertNotNull(result);
    }

    @Test
    void testDelete() {
        Long id = 1L;

        service.delete(id);

        Mockito.verify(repository).deleteById(id);
    }
}