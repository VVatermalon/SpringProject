package org.example.springproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springproject.controller.dataForm.SushiTypeCreate;
import org.example.springproject.controller.dataForm.SushiTypeUpdate;
import org.example.springproject.service.SushiTypeService;
import org.example.springproject.service.dto.SushiTypeDto;
import org.example.springproject.service.impl.SushiTypeServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class SushiTypeControllerTest {
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    private SushiTypeService service;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void beforeEach() {
        service = mock(SushiTypeServiceImpl.class);
        SushiTypeController controller = new SushiTypeController(service);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void findByIdWhenPathVarValidAndDtoPresentedThenStatusOk() throws Exception {
        UUID id = UUID.randomUUID();
        when(service.findById(id)).thenReturn(new SushiTypeDto());

        mockMvc.perform(get("/sushiType/{id}", id.toString()))
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

        mockMvc.perform(get("/sushiType"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(notNullValue())
                );

        verify(service, times(1)).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void saveWhenFormValidAndNoSuchSushiTypeInBDThenStatusOk() throws Exception {
        var saveFormJson = objectMapper.writeValueAsString(new SushiTypeCreate("Sushi"));
        var savedDto = new SushiTypeDto();
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.save(any(SushiTypeCreate.class))).thenReturn(savedDto);

        mockMvc.perform(post("/sushiType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).save(any(SushiTypeCreate.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void updateWhenFormValidThenStatusOk() throws Exception {
        var updateForm = new SushiTypeUpdate(UUID.randomUUID(), "Sushi");
        var savedDto = new SushiTypeDto();
        var updateFormJson = objectMapper.writeValueAsString(updateForm);
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.update(any(SushiTypeUpdate.class))).thenReturn(savedDto);

        mockMvc.perform(put("/sushiType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).update(any(SushiTypeUpdate.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteWhenValidPathVarThenStatusOk() throws Exception {
        UUID id = UUID.randomUUID();
        var savedDto = new SushiTypeDto();
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.delete(id)).thenReturn(savedDto);

        mockMvc.perform(delete("/sushiType/{id}", id.toString()))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).delete(id);
        verifyNoMoreInteractions(service);
    }
}
