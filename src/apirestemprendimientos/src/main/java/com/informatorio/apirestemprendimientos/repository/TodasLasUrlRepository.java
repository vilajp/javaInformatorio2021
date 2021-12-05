package com.informatorio.apirestemprendimientos.repository;

import com.informatorio.apirestemprendimientos.entity.TodasLasTags;
import com.informatorio.apirestemprendimientos.entity.TodasLasUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodasLasUrlRepository extends JpaRepository<TodasLasUrl, Long> {
}
