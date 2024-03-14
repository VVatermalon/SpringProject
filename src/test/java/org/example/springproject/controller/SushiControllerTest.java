package org.example.springproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springproject.controller.dataForm.SushiCreate;
import org.example.springproject.controller.dataForm.SushiUpdate;
import org.example.springproject.service.SushiService;
import org.example.springproject.service.dto.SushiDto;
import org.example.springproject.service.impl.SushiServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class SushiControllerTest {
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    private SushiService service;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void beforeEach() {
        service = mock(SushiServiceImpl.class);
        SushiController controller = new SushiController(service);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void findByIdWhenPathVarValidAndDtoPresentedThenStatusOk() throws Exception {
        UUID id = UUID.randomUUID();
        when(service.findById(id)).thenReturn(new SushiDto());

        mockMvc.perform(get("/sushi/{id}", id.toString()))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(notNullValue())
                );

        verify(service, times(1)).findById(id);
        verifyNoMoreInteractions(service);
    }

    @Test
    void findAllWhenDtoPresentedThenStatusOk() throws Exception {
        when(service.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/sushi"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(notNullValue())
                );

        verify(service, times(1)).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void saveWhenFormValidAndNoSuchSushiInBDThenStatusOk() throws Exception {
        var saveFormJson = objectMapper.writeValueAsString(new SushiCreate("Sushi", UUID.randomUUID(), BigDecimal.ZERO, "Sushi"));
        var savedDto = new SushiDto();
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.save(any(SushiCreate.class))).thenReturn(savedDto);

        mockMvc.perform(post("/sushi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).save(any(SushiCreate.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void updateWhenFormValidThenStatusOk() throws Exception {
        var updateForm = new SushiUpdate(UUID.randomUUID(), "Sushi", UUID.randomUUID(), BigDecimal.ZERO, "Sushi");
        var savedDto = new SushiDto();
        var updateFormJson = objectMapper.writeValueAsString(updateForm);
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.update(any(SushiUpdate.class))).thenReturn(savedDto);

        mockMvc.perform(put("/sushi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).update(any(SushiUpdate.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteWhenValidPathVarThenStatusOk() throws Exception {
        UUID id = UUID.randomUUID();
        var savedDto = new SushiDto();
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.delete(id)).thenReturn(savedDto);

        mockMvc.perform(delete("/sushi/{id}", id.toString()))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).delete(id);
        verifyNoMoreInteractions(service);
    }
}
