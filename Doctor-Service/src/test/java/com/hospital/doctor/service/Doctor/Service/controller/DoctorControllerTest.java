package com.hospital.doctor.service.Doctor.Service.controller;

import com.hospital.doctor.service.Doctor.Service.dto.DoctorDTO;
import com.hospital.doctor.service.Doctor.Service.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DoctorService service;

    @Test
    void testGet() throws Exception {
        when(service.get(1L)).thenReturn(new DoctorDTO());

        mockMvc.perform(get("/doctors/1"))
                .andExpect(status().isOk());
    }
}
