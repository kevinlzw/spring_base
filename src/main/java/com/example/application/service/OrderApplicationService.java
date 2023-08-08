package com.example.application.service;

import com.example.infrastructure.persistence.assembler.OrderDataMapper;
import com.example.infrastructure.persistence.repository.domain.OrderDomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderApplicationService {

    private final OrderDomainRepository orderDomainRepository;
}
