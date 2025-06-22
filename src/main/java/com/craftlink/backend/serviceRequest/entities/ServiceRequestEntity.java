package com.craftlink.backend.serviceRequest.entities;

import com.craftlink.backend.service.entities.ServiceEntity;
import com.craftlink.backend.shared.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "service_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"serviceType"})
public class ServiceRequestEntity extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity serviceType;

    private String description;
    private String location;
    private LocalDate preferredDate;
}
