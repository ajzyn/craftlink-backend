package com.craftlink.backend.client.adapter.port.out.persistence.write;

import com.craftlink.backend.client.adapter.port.out.persistence.ClientEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataClientRepositoryJpa extends JpaRepository<ClientEntity, UUID> {

}
