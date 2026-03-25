package com.hospital.patient_service.Controller;

import com.hospital.patient_service.controller.PatientController;
import com.hospital.patient_service.dto.PatientDTO;
import com.hospital.patient_service.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientService service;

    @Test
    void testGet() throws Exception {
        when(service.getById(1L)).thenReturn(new PatientDTO());

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk());
    }
}