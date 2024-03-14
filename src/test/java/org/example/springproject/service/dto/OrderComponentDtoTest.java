package org.example.springproject.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderComponentDtoTest {

    @Test
    void testGettersAndSetters_Order() {
        OrderSimpleDto orderSimpleDto = new OrderSimpleDto();
        OrderComponentDto orderComponentDto = new OrderComponentDto();

        orderComponentDto.setOrder(orderSimpleDto);

        assertNotNull(orderComponentDto.getOrder());
        assertEquals(orderSimpleDto, orderComponentDto.getOrder());
    }

    @Test
    void testGettersAndSetters_Sushi() {
        SushiSimpleDto sushiSimpleDto = new SushiSimpleDto();
        OrderComponentDto orderComponentDto = new OrderComponentDto();

        orderComponentDto.setSushi(sushiSimpleDto);

        assertNotNull(orderComponentDto.getSushi());
        assertEquals(sushiSimpleDto, orderComponentDto.getSushi());
    }

    @Test
    void testGettersAndSetters_Amount() {
        int amount = 5;
        OrderComponentDto orderComponentDto = new OrderComponentDto();

        orderComponentDto.setAmount(amount);

        assertEquals(amount, orderComponentDto.getAmount());
    }
}
