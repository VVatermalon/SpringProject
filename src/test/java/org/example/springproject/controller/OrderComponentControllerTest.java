package org.example.springproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springproject.controller.dataForm.OrderComponentForm;
import org.example.springproject.controller.dataForm.OrderComponentForm;
import org.example.springproject.service.OrderComponentService;
import org.example.springproject.service.dto.OrderComponentDto;
import org.example.springproject.service.impl.OrderComponentServiceImpl;
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

class OrderComponentControllerTest {
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    private OrderComponentService service;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void beforeEach() {
        service = mock(OrderComponentServiceImpl.class);
        OrderComponentController controller = new OrderComponentController(service);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void findByOrderIdWhenPathVarValidAndDtoPresentedThenStatusOk() throws Exception {
        UUID id = UUID.randomUUID();
        when(service.findByOrderId(id)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/orderComponents/{id}", id.toString()))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(notNullValue())
                );

        verify(service, times(1)).findByOrderId(id);
        verifyNoMoreInteractions(service);
    }

    @Test
    void findAllWhenDtoPresentedThenStatusOk() throws Exception {
        when(service.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/orderComponents"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(notNullValue())
                );

        verify(service, times(1)).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void saveWhenFormValidAndNoSuchOrderComponentInBDThenStatusOk() throws Exception {
        var saveFormJson = objectMapper.writeValueAsString(new OrderComponentForm(UUID.randomUUID(), UUID.randomUUID(), 10));
        var savedDto = new OrderComponentDto();
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.save(any(OrderComponentForm.class))).thenReturn(savedDto);

        mockMvc.perform(post("/orderComponents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).save(any(OrderComponentForm.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void updateWhenFormValidThenStatusOk() throws Exception {
        var updateForm = new OrderComponentForm(UUID.randomUUID(), UUID.randomUUID(), 10);
        var savedDto = new OrderComponentDto();
        var updateFormJson = objectMapper.writeValueAsString(updateForm);
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.update(any(OrderComponentForm.class))).thenReturn(savedDto);

        mockMvc.perform(put("/orderComponents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).update(any(OrderComponentForm.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteWhenValidPathVarThenStatusOk() throws Exception {
        UUID sushiId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        var savedDto = new OrderComponentDto();
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.delete(sushiId, orderId)).thenReturn(savedDto);

        mockMvc.perform(delete("/orderComponents/")
                        .param("sushiId", sushiId.toString())
                        .param("orderId", orderId.toString()))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).delete(sushiId, orderId);
        verifyNoMoreInteractions(service);
    }
}
