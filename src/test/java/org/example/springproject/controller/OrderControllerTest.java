package org.example.springproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springproject.controller.dataForm.OrderCreate;
import org.example.springproject.controller.dataForm.OrderUpdate;
import org.example.springproject.model.OrderEntity;
import org.example.springproject.service.OrderService;
import org.example.springproject.service.dto.OrderDto;
import org.example.springproject.service.impl.OrderServiceImpl;
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

class OrderControllerTest {
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    private OrderService service;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void beforeEach() {
        service = mock(OrderServiceImpl.class);
        OrderController controller = new OrderController(service);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void findByIdWhenPathVarValidAndDtoPresentedThenStatusOk() throws Exception {
        UUID id = UUID.randomUUID();
        when(service.findById(id)).thenReturn(new OrderDto());

        mockMvc.perform(get("/orders/{id}", id.toString()))
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

        mockMvc.perform(get("/orders"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(notNullValue())
                );

        verify(service, times(1)).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void saveWhenFormValidAndNoSuchOrderInBDThenStatusOk() throws Exception {
        var saveFormJson = objectMapper.writeValueAsString(new OrderCreate(OrderEntity.OrderStatus.IN_PROCESS));
        var savedDto = new OrderDto();
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.save(any(OrderCreate.class))).thenReturn(savedDto);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).save(any(OrderCreate.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void updateWhenFormValidThenStatusOk() throws Exception {
        var updateForm = new OrderUpdate(UUID.randomUUID(), OrderEntity.OrderStatus.IN_PROCESS);
        var savedDto = new OrderDto();
        var updateFormJson = objectMapper.writeValueAsString(updateForm);
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.update(any(OrderUpdate.class))).thenReturn(savedDto);

        mockMvc.perform(put("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).update(any(OrderUpdate.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteWhenValidPathVarThenStatusOk() throws Exception {
        UUID id = UUID.randomUUID();
        var savedDto = new OrderDto();
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.delete(id)).thenReturn(savedDto);

        mockMvc.perform(delete("/orders/{id}", id.toString()))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).delete(id);
        verifyNoMoreInteractions(service);
    }
}
