package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logsRepository;

    public Page<Log> getLogs(Pageable pageable) {
        Page<Log> logs = logsRepository.findAll(pageable);
        return logs;
    }
    public Log getLogs(Long id) {
        return logsRepository.findById(id).get();
    }
    public void addLog(Log log) {
        // Si en Id es null le asignamos el último + 1 de la lista
        logsRepository.save(log);
    }
}
