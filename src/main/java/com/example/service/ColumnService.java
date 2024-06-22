package com.example.service;

import com.example.model.Board;
import com.example.model.Column;
import com.example.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Azizbek Toshpulatov
 */
@Service
public class ColumnService {
    @Autowired
    private ColumnRepository columnRepository;

    public Column save(Column column) {
        return columnRepository.save(column);
    }

    public Column findById(Long id) {
        return columnRepository.findById(id).orElse(null);
    }

    public List<Column> findAll() {
        List<Column> columns = new ArrayList<Column>();
        columnRepository.findAll().forEach(columns::add);
        return columns;
    }

    public void deleteById(Long id) {
        columnRepository.deleteById(id);
    }
}
