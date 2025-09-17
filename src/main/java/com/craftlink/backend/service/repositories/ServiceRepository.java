package com.craftlink.backend.service.repositories;

import com.craftlink.backend.category.adapter.out.persistance.ServiceEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

  List<ServiceEntity> findByNameContainingIgnoreCase(String searchPhrase);

  //TODO: jezeli istneieje to wyrzucic wyjatek buisness - to ma byc w read i write - mamy wybrac tylko acitve
  @Query("""
      SELECT DISTINCT s FROM ServiceEntity s
      LEFT JOIN FETCH s.category c
      LEFT JOIN FETCH c.image
      WHERE s.slug = :slug
      """)
  Optional<ServiceEntity> findBySlug(String slug);
}
