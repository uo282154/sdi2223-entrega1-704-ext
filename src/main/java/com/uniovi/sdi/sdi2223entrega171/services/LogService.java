package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.repositories.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class LogService {

    /**
     * Instancia de Logger
     */
    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogRepository logsRepository;

    public Page<Log> getLogs(Pageable pageable) {
        Page<Log> logs = logsRepository.findAll(pageable);
        return logs;
    }

    public Page<Log> searchByType(Pageable pageable, String typeToFilter) {
        Page<Log> logs = new PageImpl<Log>(new LinkedList<Log>());
        logs = logsRepository.searchByType(pageable, typeToFilter);
        return logs;
    }


    public void addLog(Log.LogItemType type, String description) {
        Log log = new Log();
        log.setType(type);
        log.setDescription(description);
        log.setTimestamp(new Timestamp(new Date().getTime()));

        // Si en Id es null le asignamos el último + 1 de la lista
        logsRepository.save(log);
    }

    public void deleteAll() {
        logsRepository.deleteAll();
    }
    public List<Log.LogItemType> getTypesLog() {
        return Arrays.asList(Log.LogItemType.values());
    }
}

