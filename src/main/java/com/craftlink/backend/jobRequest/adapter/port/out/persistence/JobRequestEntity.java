package com.craftlink.backend.jobRequest.adapter.port.out.persistence;

import com.craftlink.backend.category.adapter.out.persistance.ServiceEntity;
import com.craftlink.backend.client.adapter.port.out.persistence.ClientEntity;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.DeadlineType;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.JobRequestStatus;
import com.craftlink.backend.shared.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "job_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"service"})
public class JobRequestEntity extends BaseEntity {

  @Id
  @Column(updatable = false, nullable = false)
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "service_id", nullable = false)
  private ServiceEntity service;

  @ManyToOne(optional = false)
  @JoinColumn(name = "requester_id", nullable = false)
  private ClientEntity requester;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private JobRequestStatus status;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DeadlineType deadlineType;

  @Column(nullable = false, length = 500)
  private String description;

  @Column(nullable = false)
  private String city;

  private LocalDate deadline;
  private String district;
  private LocalDate exactDate;
}
