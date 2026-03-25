package com.hospital.appointment_service.Appointment_Service.controller;

import com.hospital.appointment_service.Appointment_Service.dto.AppointmentDTO;
import com.hospital.appointment_service.Appointment_Service.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppointmentService service;

    @Test
    void testGet() throws Exception {

        when(service.get(1L)).thenReturn(new AppointmentDTO());

        mockMvc.perform(get("/appointments/1"))
                .andExpect(status().isOk());
    }
}
