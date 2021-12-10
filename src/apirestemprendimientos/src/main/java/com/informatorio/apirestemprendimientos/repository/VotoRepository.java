package com.informatorio.apirestemprendimientos.repository;

import com.informatorio.apirestemprendimientos.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
}
