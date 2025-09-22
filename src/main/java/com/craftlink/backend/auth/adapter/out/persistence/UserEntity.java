package com.craftlink.backend.auth.adapter.out.persistence;

import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import com.craftlink.backend.client.adapter.port.out.persistence.ClientEntity;
import com.craftlink.backend.shared.entities.BaseEntity;
import com.craftlink.backend.specialist.adapter.persistence.SpecialistEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"client", "specialist", "authorities"})
public class UserEntity extends BaseEntity {

  @Id
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Column(unique = true)
  private String phoneNumber;

  private String username;

  @Column(unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserType userType;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_authorities",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id")
  )
  private Set<AuthorityEntity> authorities = new HashSet<>();


  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private SpecialistEntity specialist;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private ClientEntity client;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RefreshTokenEntity> refreshToken;
}
