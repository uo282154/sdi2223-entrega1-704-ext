package com.uniovi.sdi.sdi2223entrega171.services;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class LogService {

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


    public void addLog(Log log) {
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

