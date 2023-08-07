package com.example.application.service;

import com.example.application.assembler.CustomerDtoMapper;
import com.example.domain.entity.Customer;
import com.example.domain.repository.CustomerRepository;
import com.example.presentation.vo.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerApplicationServiceTest {

    private CustomerApplicationService service;
    private CustomerRepository repository;
    private CustomerDtoMapper mapper;

    @BeforeEach
    public void setup() {
        repository = Mockito.mock(CustomerRepository.class);
        mapper = CustomerDtoMapper.MAPPER;
        service = new CustomerApplicationService(repository);
    }

    @Test
    public void testFindById() {
        String testId = "1";
        Customer testCustomer = Customer.builder()
                .id(testId)
                .name("Test Customer")
                .createTime(LocalDateTime.now().minusDays(1))
                .updateTime(LocalDateTime.now())
                .build();

        CustomerDto expectedDto = new CustomerDto();
        expectedDto.setId(testId);
        expectedDto.setName("Test Customer");
        when(repository.findById(testId)).thenReturn(testCustomer);

        CustomerDto result = service.findById(testId);
        assertEquals(expectedDto.getId(), result.getId());
        assertEquals(expectedDto.getName(), result.getName());
    }

}
