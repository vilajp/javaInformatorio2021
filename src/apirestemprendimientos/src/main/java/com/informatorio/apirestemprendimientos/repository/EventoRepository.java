package com.informatorio.apirestemprendimientos.repository;

import com.informatorio.apirestemprendimientos.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}