package com.craftlink.backend.serviceRequest.dtos;

import com.craftlink.backend.service.entities.ServiceEntity;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestDto {

    private Integer id;
    private ServiceEntity serviceType;
    private String description;
    private String location;
    private LocalDate preferredDate;
}
