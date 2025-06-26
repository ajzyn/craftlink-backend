package com.craftlink.backend.service.services;

import com.craftlink.backend.service.dtos.ServiceBasicDto;
import com.craftlink.backend.service.repositories.ServiceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;

    public List<ServiceBasicDto> getBasicServiceList(String searchPhrase) {
        var services = serviceRepository.findByNameContainingIgnoreCase(searchPhrase);

        return services.stream()
            .map(service -> modelMapper.map(service, ServiceBasicDto.class))
            .toList();
    }
}
