package com.hexagon.service.repository;

import com.hexagon.service.domain.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<EventEntity, Long> {
}
