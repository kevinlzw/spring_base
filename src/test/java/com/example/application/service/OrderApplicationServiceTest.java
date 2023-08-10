package com.example.application.service;

import com.example.domain.entity.Order;
import com.example.domain.entity.ProductDetail;
import com.example.domain.repository.OrderRepository;
import com.example.domain.repository.ProductRepository;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.ProductDetailsDto;
import com.example.presentation.vo.SaveOrderResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.example.application.service.common.OrderFixture.*;
import static com.example.application.service.common.ProductFixture.PRODUCT;
import static com.example.application.service.common.ProductFixture.ProductDetailBuilder;
import static com.example.application.service.common.OrderFixture.*;
import static com.example.application.service.common.ProductFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderApplicationServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private OrderApplicationService service;


    @Test
    void should_get_orders_successfully() {
        ProductDetail product1 =
                ProductDetailBuilder("product1","book",20,3).build();
        ProductDetail product2 =
                ProductDetailBuilder("product2","cat",200,2).build();


        Order order1 = orderBuilder("1",List.of(product1, product2))
                .build();

        Order order2 = orderBuilder("2",List.of(product1))
                .build();

        List<Order> orders = Arrays.asList(order1, order2);
        when(orderRepository.findOrders(anyString())).thenReturn(orders);

        List<OrderDto> orderList = service.getOrderList(anyString());
        OrderDto orderDto1 = orderList.get(0);
        OrderDto orderDto2 = orderList.get(1);

        assertEquals(2, orderList.size());
        assertEquals("1", orderDto1.getOrderId());
        assertEquals("book", orderDto1.getProducts().get(0).getName());
        assertEquals(BigDecimal.valueOf(20), orderDto1.getProducts().get(0).getPrice());
        assertEquals(3, orderDto1.getProducts().get(0).getQuantity());
        assertEquals("cat", orderDto1.getProducts().get(1).getName());
        assertEquals(BigDecimal.valueOf(200), orderDto1.getProducts().get(1).getPrice());
        assertEquals(2, orderDto1.getProducts().get(1).getQuantity());
        assertEquals("submit", orderDto1.getOrderStatus());

        assertEquals("2", orderDto2.getOrderId());
        assertEquals("book", orderDto2.getProducts().get(0).getName());
        assertEquals(BigDecimal.valueOf(20), orderDto2.getProducts().get(0).getPrice());
        assertEquals(3, orderDto2.getProducts().get(0).getQuantity());
        assertEquals("submit", orderDto2.getOrderStatus());

    }

    @Test
    public void should_get_order_details_successfully() {

        when(orderRepository.findOrderById(anyString())).thenReturn(ORDER);

        OrderDto order = service.getOrderByOrderId(ORDER_ID);
        List<ProductDetailsDto> products = order.getProducts();

        assertEquals(1,products.size());
        assertEquals(ORDER_ID, order.getOrderId());
        assertEquals(TOTAL_PRICE, order.getTotalPrice());
        assertEquals(PRODUCT_PRICE, products.get(0).getPrice());
        assertEquals(PRODUCT_NAME,products.get(0).getName());

    }

    @Test
    public void should_save_order_successfully() {
        when(productRepository.findProducts(anyList())).thenReturn(List.of(PRODUCT));

        doNothing().when(orderRepository).saveOrders(ORDER);

        SaveOrderResponseDto response = service.takeOrder(SAVE_ORDER_REQUEST_DTO);

        assertEquals(ORDER_ID, response.getOrderId());
    }
}
