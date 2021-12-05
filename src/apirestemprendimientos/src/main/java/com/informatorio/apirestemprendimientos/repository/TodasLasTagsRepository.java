package com.informatorio.apirestemprendimientos.repository;


import com.informatorio.apirestemprendimientos.entity.Emprendimiento;
import com.informatorio.apirestemprendimientos.entity.TodasLasTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodasLasTagsRepository extends JpaRepository<TodasLasTags, Long> {
}
