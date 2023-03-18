package com.uniovi.sdi.sdi2223entrega171.repositories;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
    @Query("SELECT log FROM Log log  ORDER BY log.timestamp DESC")
    Page<Log> findAll(Pageable pageable);
    @Query("SELECT log FROM Log log WHERE (lower(log.type) like lower(?1)) ORDER BY log.timestamp DESC")
    Page<Log> searchByType(Pageable pageable, String typeToFilter);
}
