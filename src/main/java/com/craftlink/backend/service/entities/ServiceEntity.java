package com.craftlink.backend.service.entities;


import com.craftlink.backend.category.entities.CategoryEntity;
import com.craftlink.backend.serviceRequest.entities.ServiceRequestEntity;
import com.craftlink.backend.shared.entities.BaseEntity;
import com.craftlink.backend.shared.enums.EntityStatus;
import com.craftlink.backend.specialist.entities.SpecialistEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"serviceRequests", "category", "specialists"})
public class ServiceEntity extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;
    private String description;
    private String slug;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "serviceType")
    private Set<ServiceRequestEntity> serviceRequests = new HashSet<>();

    @ManyToMany(mappedBy = "offeredServices")
    private Set<SpecialistEntity> specialists = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private EntityStatus active = EntityStatus.ACTIVE;
}
